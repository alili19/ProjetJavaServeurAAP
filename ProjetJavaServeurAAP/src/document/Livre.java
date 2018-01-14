package document;
import abonne.PasAutoriseException;
import bibliotheque.Document;
import abonne.EtatAbonne;

public class Livre implements Document{
	private int numero;
	private static int cpt=0;
	private EtatDocument etat;
	private Abonne Abonne;
	
	public Livre(){
		this.numero = cpt++;
		this.etat = EtatDocument.Disponible;
	}
	
	@Override
	public int numero() {
		return numero;
	}

	@Override
	public void reserver(Abonne ab) throws PasLibreException, PasAutoriseException {
		if (this.etat==EtatDocument.Emprunte || this.etat==EtatDocument.Reserve){
			throw new PasLibreException("Le livre "+this.numero+ " n'est pas disponible"); 
		}
		else if(ab.getEtat()== EtatAbonne.Interdit){
			throw new PasAutoriseException("L'Abonne "+ab.getNumero()+ " est interdit"); 
		}
		else{
			/*dans tous les cas le livre est reserve*/
			setEtatDocument(EtatDocument.Reserve);
			this.Abonne=ab; 
		}
	}
	
	@Override
	public void emprunter(Abonne ab) throws PasLibreException, PasAutoriseException {
		if(Abonne==null){
			Abonne=ab;
		}
		else if(ab.getEtat()== EtatAbonne.Interdit){
			throw new PasAutoriseException("L'Abonne "+ab.getNumero()+ " est interdit"); 
		}
		setEtatDocument(EtatDocument.Emprunte);
	}

	
	@Override
	public void retour(){
		if(etat==EtatDocument.Emprunte){
			setEtatDocument(EtatDocument.Disponible);
		}
	}

	@Override
	public Abonne getAbonne() {
		return Abonne;
	}

	public void setAbonne(Abonne Abonne) {
		this.Abonne = Abonne;
	}
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	@Override
	public EtatDocument getEtatDocument() {
		return etat;
	}

	@Override
	public void setEtatDocument(EtatDocument e) {
		this.etat = e;
	}
	
	
}
