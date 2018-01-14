package service;

import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

import abonne.EtatAbonne;
import abonne.PasAutoriseException;
import bibliotheque.Bibliotheque;
import document.EtatDocument;
import document.PasLibreException;

public class ServiceEmprunt extends Service{
	private Timer timerService;
	public ServiceEmprunt(Socket accept, Bibliotheque bibli) {
		super(accept, bibli);
		timerService = new Timer();
	}
	
	@Override
	public int appelAuService(Bibliotheque bibli, String line) {
		String[] words= line.split(";");
		int numLivre= Integer.parseInt(words[0]);
		int numAbonne=Integer.parseInt(words[1]);
		try {

			bibli.emprunter(numLivre, numAbonne);

			long time = (long) (1.21*Math.pow(10, 9)); //2 semaines de délai defini avant d'effectuer la tache
	        TimerTask rendreAbonneInterdit = new TimerTask() { // création et spécification de la tache à effectuer
	            @Override
	                public void run() {
		            	if(bibli.retrouverDocument(numLivre).getEtatDocument()!= EtatDocument.Disponible){
		            		bibli.retrouverDocument(numLivre).getAbonne().setEtat(EtatAbonne.Interdit);
			                 this.cancel();
			                 System.err.println("JE PASSE PAR LA ");
				                long timeBloque = (long) (2.628*Math.pow(10, 9)); //2 semaines de délai defini avant d'effectuer la tache
				     	        TimerTask rendreAbonneDisponible = new TimerTask() { // création et spécification de la tache à effectuer
				     	            @Override
				     	                public void run() {
				     	            			bibli.retrouverDocument(numLivre).getAbonne().setEtat(EtatAbonne.Autorise);
				     			                this.cancel();

				     			               System.err.println("JE SUIS LA");
				     			                 
				     			             }
				     	                }; 
				     	                timerService.schedule(rendreAbonneDisponible, timeBloque);
			             }
	                }
	        };
	       super.getTimer().schedule(rendreAbonneInterdit,time);  
			
		} catch (PasAutoriseException|PasLibreException e) {
			e.printStackTrace();
		}
		return numLivre;
	}

}
