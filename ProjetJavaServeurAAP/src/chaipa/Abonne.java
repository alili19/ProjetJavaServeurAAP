package chaipa;

public class Abonne {
	private int numero;
	private static int cpt=0;

	public Abonne(){
		this.numero=cpt++;
	}
	
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	
}
