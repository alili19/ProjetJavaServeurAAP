
public class Livre implements Document{
	private int numero;
	private String titre;
	private String auteur;
	private boolean emprunt�;
	
	private Livre(){
		this.emprunt�=false;
	}
	
	@Override
	public int numero() {
		return numero;
	}

	@Override
	public void reserver(Abonne ab) throws PasLibreException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void emprunter(Abonne ab) throws PasLibreException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void retour() {
		// TODO Auto-generated method stub
		
	}

}
