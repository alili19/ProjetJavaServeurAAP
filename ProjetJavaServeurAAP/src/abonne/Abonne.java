package abonne;

public class Abonne {
	private int numero;
	private static int cpt=0;
	EtatAbonne etat;

	public Abonne(){
		this.numero=cpt++;
		this.etat = EtatAbonne.Autorise;
	}
	
	public EtatAbonne getEtat() {
		return etat;
	}

	public void setEtat(EtatAbonne etat) {
		this.etat = etat;
	}


	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	
}
