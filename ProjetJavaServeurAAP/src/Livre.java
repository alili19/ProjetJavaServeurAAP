
public class Livre implements Document{
	private int numero;
	/*private String titre;
	private String auteur;*/
	private EtatLivre etat;
	private Abonne abonne;
	

	private Livre(){
		this.numero = 1;
		this.etat = EtatLivre.Disponible;
				
	}
	
	@Override
	public int numero() {
		return numero;
	}

	@Override
	public void reserver(Abonne ab) throws PasLibreException {
		setEtat(EtatLivre.Reserve);
	}

	@Override
	public void emprunter(Abonne ab) throws PasLibreException {
		if (etat == EtatLivre.Emprunte || etat == EtatLivre.Reserve){
			throw new PasLibreException("le livre n'est pas disponible");
		}
		setEtat(EtatLivre.Emprunte);
		this.abonne= ab;
	}

	@Override
	public void retour() {
		setEtat(EtatLivre.Disponible);
	}
	
	public EtatLivre getEtat() {
		return etat;
	}

	public void setEtat(EtatLivre etat) {
		this.etat = etat;
	}

}
