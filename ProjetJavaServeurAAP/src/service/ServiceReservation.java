package service;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

import abonne.PasAutoriseException;
import bibliotheque.Bibliotheque;
import document.EtatLivre;
import document.PasLibreException;

public class ServiceReservation extends Service{
	private Timer ExpirationReservation;
	public ServiceReservation(Socket accept, Bibliotheque bibli) {
		super(accept, bibli);
		ExpirationReservation=new Timer();
	}	

	@Override
	public int appelAuService(Bibliotheque bibli, String line) {
		String[] words= line.split(";");
		int numLivre= Integer.parseInt(words[0]);
		int numAbonne=Integer.parseInt(words[1]);
		try {
			bibli.reserver(numLivre, numAbonne);
	        
			long time =30000; //7200000; // délai defini avant d'effectuer la tache
		    TimerTask rendrelivreDispo = new TimerTask() {     // création et spécification de la tache à effectuer
		        @Override
		            public void run() {
		        		if(bibli.retrouverLivre(numLivre).getEtat()!= EtatLivre.Emprunte){
		                    bibli.retrouverLivre(numLivre).setEtat(EtatLivre.Disponible);
		                    bibli.retrouverLivre(numLivre).setAbonne(null);
		                    
		                    try {
		                    	PrintWriter outReservation = new PrintWriter(getClient().getOutputStream ( ), true);
		                    	outReservation.write("Le livre n'est plus disponible car vous ne l'avez pas emprunté dans le temps imparti.\n");
		                    	outReservation.flush();
		                    } 
		                    catch (IOException e) {
								e.printStackTrace();
							}
		                    
		                    this.cancel();
		                }
		            }
		    };
		    
		  /*mais si le temps est écoulé le livre est disponible 
		   * si il n'est toujours pas emprunté*/
		    ExpirationReservation.schedule(rendrelivreDispo,time); 

		} catch (PasAutoriseException|PasLibreException e) {
			e.printStackTrace();
		}
		return numLivre;
	}


}
