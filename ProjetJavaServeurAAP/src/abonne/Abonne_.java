package abonne;

import document.Abonne;

public class Abonne_ implements Abonne{
	
	private int numero;
	private static int cpt=0;
	EtatAbonne etat;

	public Abonne_(){
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
