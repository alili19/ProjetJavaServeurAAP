package bibliotheque;
import java.util.ArrayList;
import java.util.Iterator;

import abonne.Abonne;
import abonne.EtatAbonne;
import abonne.PasAutoriseException;
import document.EtatLivre;
import document.Livre;
import document.PasLibreException;

public class Bibliotheque {

	private ArrayList<Livre> listeLivres;
	private ArrayList<Abonne> listeAbonnes;
	private static Bibliotheque _instance;
	
	private Bibliotheque(){
		this.listeAbonnes= new ArrayList<Abonne>();
		this.listeLivres= new ArrayList<Livre>();
	}
	
	public static Bibliotheque getInstance(){
		if(_instance==null){
			_instance=new Bibliotheque();
		}
		return _instance;
	}
	
	/*Le service ne sera ouvert qu’aux abonnés référencés de la bibliothèque 
	 * (la création de nouveaux abonnés n’est pas au programme de ce projet)*/
	
	public void inscrireAbonne(Abonne abonne){
		listeAbonnes.add(abonne);
	}
	
	public void ajouterLivre(Livre livre){
		listeLivres.add(livre);
	}
	
	
	public Abonne retrouverAbonne(int numeroAbonnne){
		Iterator<Abonne> it= listeAbonnes.iterator();
		while(it.hasNext()){
			Abonne a = it.next();
			if(a.getNumero()==numeroAbonnne){
				return a;
			}
		}
		return null;
	}
	
	public Livre retrouverLivre(int numeroLivre){
		Iterator<Livre> it= listeLivres.iterator();
		while(it.hasNext()){
			Livre l = it.next();
			if(l.numero()==numeroLivre){
				return l;
			}
		}
		return null;
	}
	
	public boolean bonnePersonne(Abonne ab,Livre l){
		boolean reserver;
		if(l.getAbonne()==ab && l.getEtat()==EtatLivre.Reserve){
			reserver=true;
		}
		else{
			reserver=false;
		}
		return reserver;
	}
	
	
	public synchronized void retour(int numLivre, boolean degradation){
		Livre l = retrouverLivre(numLivre);
		Iterator<Abonne> it= listeAbonnes.iterator();
		while(it.hasNext()){
			Abonne a = it.next();
			if(a.getNumero()==l.getAbonne().getNumero()){
				l.retour();
				if (degradation){
					this.retrouverLivre(numLivre).getAbonne().setEtat(EtatAbonne.Interdit);
				}
				
			}
		}
	}
	
	public synchronized  void emprunter(int numLivre, int numAbonne) throws PasLibreException, PasAutoriseException{
		Livre l = retrouverLivre(numLivre);
		if(bonnePersonne(retrouverAbonne(numAbonne), retrouverLivre(numLivre)) || l.getEtat()==EtatLivre.Disponible && this.retrouverAbonne(numAbonne)!= null){ // si numAbonne est l'abonne qui a bien reservé ce livre
			l.emprunter(retrouverAbonne(numAbonne));
		}
	}
	
	public synchronized void reserver(int numLivre, int numAbonne) throws PasLibreException, PasAutoriseException{
		Livre l = retrouverLivre(numLivre);
			l.reserver(retrouverAbonne(numAbonne));
	}
}
