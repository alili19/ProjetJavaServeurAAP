package bibliotheque;
import java.util.ArrayList;
import java.util.Iterator;

import abonne.EtatAbonne;
import abonne.PasAutoriseException;
import document.Abonne;
import document.EtatDocument;
import document.PasLibreException;

public class Bibliotheque {

	private ArrayList<Document> listeDocuments;
	private ArrayList<Abonne> listeAbonnes;
	private static Bibliotheque _instance;
	
	private Bibliotheque(){
		this.listeAbonnes= new ArrayList<Abonne>();
		this.listeDocuments= new ArrayList<Document>();
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
	
	public void ajouterDocument(Document Document){
		listeDocuments.add(Document);
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
	
	public Document retrouverDocument(int numeroDocument){
		Iterator<Document> it= listeDocuments.iterator();
		while(it.hasNext()){
			Document l = it.next();
			if(l.numero()==numeroDocument){
				return l;
			}
		}
		return null;
	}
	
	public boolean bonnePersonne(Abonne ab,Document l){
		boolean reserver;
		if(l.getAbonne()==ab && l.getEtatDocument()==EtatDocument.Reserve){
			reserver=true;
		}
		else{
			reserver=false;
		}
		return reserver;
	}
	
	
	public synchronized void retour(int numDocument, boolean degradation){
		Document l = retrouverDocument(numDocument);
		Iterator<Abonne> it= listeAbonnes.iterator();
		while(it.hasNext()){
			Abonne a = it.next();
			if(a.getNumero()==l.getAbonne().getNumero()){
				l.retour();
				if (degradation){
					this.retrouverDocument(numDocument).getAbonne().setEtat(EtatAbonne.Interdit);
				}
				
			}
		}
	}
	
	public synchronized  void emprunter(int numDocument, int numAbonne) throws PasLibreException, PasAutoriseException{
		Document l = retrouverDocument(numDocument);
		if(bonnePersonne(retrouverAbonne(numAbonne), retrouverDocument(numDocument)) || l.getEtatDocument()==EtatDocument.Disponible && this.retrouverAbonne(numAbonne)!= null){ // si numAbonne est l'abonne qui a bien reservé ce Document
			l.emprunter(retrouverAbonne(numAbonne));
		}
	}
	
	public synchronized void reserver(int numDocument, int numAbonne) throws PasLibreException, PasAutoriseException{
		Document l = retrouverDocument(numDocument);
			l.reserver(retrouverAbonne(numAbonne));
	}
}
