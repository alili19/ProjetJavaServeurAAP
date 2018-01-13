package document;
import abonne.Abonne;
import abonne.EtatAbonne;
import abonne.PasAutoriseException;

public class Livre implements Document{
	private int numero;
	private static int cpt=0;
	private EtatLivre etat;
	private Abonne abonne;
	
	public Livre(){
		this.numero = cpt++;
		this.etat = EtatLivre.Disponible;
	}
	
	@Override
	public int numero() {
		return numero;
	}

	@Override
	public synchronized void reserver(Abonne ab) throws PasLibreException, PasAutoriseException {
		if (this.etat==EtatLivre.Emprunte || this.etat==EtatLivre.Reserve){
			throw new PasLibreException("Le livre "+this.numero+ " n'est pas disponible"); 
		}
		else if(ab.getEtat()== EtatAbonne.Interdit){
			throw new PasAutoriseException("L'abonne "+ab.getNumero()+ " est interdit"); 
		}
		else{
			/*dans tous les cas le livre est reserve*/
			setEtat(EtatLivre.Reserve);
			this.abonne=ab; 
	     }
	}
	
	@Override
	public synchronized void emprunter(Abonne ab) throws PasLibreException, PasAutoriseException {
		if (etat == EtatLivre.Emprunte){
			throw new PasLibreException("Le livre "+this.numero+ " n'est pas disponible");
		}
		else if(abonne==null){
			abonne=ab;
		}
		else if(ab.getEtat()== EtatAbonne.Interdit){
			throw new PasAutoriseException("L'abonne "+ab.getNumero()+ " est interdit"); 
		}
		setEtat(EtatLivre.Emprunte);
	}

	
	@Override
	public synchronized void retour(){
		if(etat==EtatLivre.Emprunte){
			setEtat(EtatLivre.Disponible);
		}
	}
	
	public EtatLivre getEtat() {
		return etat;
	}

	public void setEtat(EtatLivre etat) {
		this.etat = etat;
	}

	public Abonne getAbonne() {
		return abonne;
	}

	public void setAbonne(Abonne abonne) {
		this.abonne = abonne;
	}
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	
}
