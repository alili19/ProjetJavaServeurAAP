package service;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

import abonne.PasAutoriseException;
import bibliotheque.Bibliotheque;
import document.EtatDocument;
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
		int numDocument= Integer.parseInt(words[0]);
		int numAbonne=Integer.parseInt(words[1]);
		try {
			bibli.reserver(numDocument, numAbonne);
	        
			long time =30000; //7200000; // délai defini avant d'effectuer la tache
		    TimerTask rendreDocumentDispo = new TimerTask() {     // création et spécification de la tache à effectuer
		        @Override
		            public void run() {
		        		if(bibli.retrouverDocument(numDocument).getEtatDocument()!= EtatDocument.Emprunte){
		                    bibli.retrouverDocument(numDocument).setEtatDocument(EtatDocument.Disponible);
		                    bibli.retrouverDocument(numDocument).setAbonne(null);
		                    
		                    try {
		                    	PrintWriter outReservation = new PrintWriter(getClient().getOutputStream ( ), true);
		                    	outReservation.write("Le Document n'est plus disponible car vous ne l'avez pas emprunté dans le temps imparti.\n");
		                    	outReservation.flush();
		                    } 
		                    catch (IOException e) {
								e.printStackTrace();
							}
		                    
		                    this.cancel();
		                }
		            }
		    };
		    
		  /*mais si le temps est écoulé le Document est disponible 
		   * si il n'est toujours pas emprunté*/
		    ExpirationReservation.schedule(rendreDocumentDispo,time); 

		} catch (PasAutoriseException|PasLibreException e) {
			e.printStackTrace();
		}
		return numDocument;
	}


}
