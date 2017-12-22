import java.util.Timer;
import java.util.TimerTask;

public class Livre implements Document{
	private int numero;
	private EtatLivre etat;
	private Abonne abonne;
	private MonTimer timer = new MonTimer();
	private Timer montimer = new Timer();
	
	private Livre(){
		this.numero = 1;
		this.etat = EtatLivre.Disponible;
				
	}
	
	public Abonne getAbonne() {
		return abonne;
	}

	public void setAbonne(Abonne abonne) {
		this.abonne = abonne;
	}
	
	@Override
	public int numero() {
		return numero;
	}

	@Override
	public void reserver(Abonne ab) throws PasLibreException {
		if (this.etat==EtatLivre.Emprunte || this.etat==EtatLivre.Reserve){
			throw new PasLibreException("Le livre "+this.numero+ " n'est pas disponible"); 
		}
		else{ 
			montimer.schedule(timer, 7200000);
			setEtat(EtatLivre.Reserve);
			this.abonne= ab;
		}
	}

	@Override
	public void emprunter(Abonne ab) throws PasLibreException {
		if (etat == EtatLivre.Emprunte /* ||!bonnePersonne(ab, this)*/){
			throw new PasLibreException("Le livre "+this.numero+ " n'est pas disponible");
		}
		else setEtat(EtatLivre.Emprunte);
	}

	
	@Override
	public void retour(){
		setEtat(EtatLivre.Disponible);
	}
	
	public EtatLivre getEtat() {
		return etat;
	}

	public void setEtat(EtatLivre etat) {
		this.etat = etat;
	}

	
}
