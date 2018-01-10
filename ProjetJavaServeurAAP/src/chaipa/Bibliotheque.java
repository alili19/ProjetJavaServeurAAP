package chaipa;
import java.util.ArrayList;
import java.util.Iterator;

public class Bibliotheque {

	private ArrayList<Livre> listeLivres;
	private ArrayList<Abonne> listeAbonnes;
	
	public Bibliotheque(){
		this.listeAbonnes= new ArrayList<Abonne>();
		this.listeLivres= new ArrayList<Livre>();
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
	
	
	public void retour(int numLivre){
		Livre l = retrouverLivre(numLivre);
		Iterator<Abonne> it= listeAbonnes.iterator();
		while(it.hasNext()){
			Abonne a = it.next();
			if(a.getNumero()==l.getAbonne().getNumero()){
				l.retour();
			}
		}
	}
	
	public void emprunter(int numLivre, int numAbonne) throws PasLibreException{
		Livre l = retrouverLivre(numLivre);
		if(bonnePersonne(retrouverAbonne(numAbonne), retrouverLivre(numLivre)) || l.getEtat()==EtatLivre.Disponible){ // si numAbonne est l'abonne qui a bien reservé ce livre
			l.emprunter(retrouverAbonne(numAbonne));
		}
	}
	
	public void reserver(int numLivre, int numAbonne) throws PasLibreException{
		Livre l = retrouverLivre(numLivre);
		//if(bonnePersonne(retrouverAbonne(numAbonne), retrouverLivre(numLivre))){ 
			l.reserver(retrouverAbonne(numAbonne));
		//}
	}
	
	public void retourner(int numLivre){
		Livre l = retrouverLivre(numLivre);
		l.retour();
	}
}
