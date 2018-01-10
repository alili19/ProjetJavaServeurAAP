package chaipa;
import java.util.Timer;
import java.util.TimerTask;

public class Livre implements Document{
	private int numero;
	private static int cpt=0;
	private EtatLivre etat;
	private Abonne abonne;
	private Timer timer;
	
	public Livre(){
		this.numero = cpt++;
		this.etat = EtatLivre.Disponible;
		this.timer= new Timer();
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
			
			/*dans tous les cas le livre est reserve*/
			setEtat(EtatLivre.Reserve);
			this.abonne=ab;
				
			
	        long time = 2*60*1000;
	        		//7200000; // d�lai defini avant d'effectuer la tache
	        TimerTask rendrelivreDispo = new TimerTask() {     // cr�ation et sp�cification de la tache � effectuer
	            @Override
	                public void run() {
	            		if(etat!= EtatLivre.Emprunte){
		                    setEtat(EtatLivre.Disponible);
		                    abonne=null;
		                	System.out.println("Le temps est �coul�, "
		            				+ "le livre que vous avez r�serv� ne peut "
		            				+ "plus �tre emprunt�");
		                    this.cancel();	
	                    }
	                }
	        };
	        
	      /*mais si le temps est �coul� le livre est disponible 
	       * si il n'est toujours pas emprunt�*/
	        timer.schedule(rendrelivreDispo,time);  
	     }
	}
	

	@Override
	public void emprunter(Abonne ab) throws PasLibreException {
		if (etat == EtatLivre.Emprunte){
			throw new PasLibreException("Le livre "+this.numero+ " n'est pas disponible");
		}
		else setEtat(EtatLivre.Emprunte);
	}

	
	@Override
	public void retour(){
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

}
