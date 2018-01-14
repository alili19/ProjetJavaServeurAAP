package service;

import java.net.Socket;
import java.util.TimerTask;

import abonne.EtatAbonne;
import bibliotheque.Bibliotheque;

public class ServiceRetour extends Service {
	
	public ServiceRetour(Socket accept, Bibliotheque bibli) {
		super(accept, bibli);
	}
	
	@Override
	public int appelAuService(Bibliotheque bibli, String line) {

		String[] words= line.split(";");
		int numLivre= Integer.parseInt(words[0]);
		String degrade=words[1];

		if(degrade.equals("NON")){
			bibli.retour(numLivre, false);
		}
		else if(degrade.equals("OUI")){
			bibli.retour(numLivre, true);
			long timeBloque = (long) (2.628*Math.pow(10, 9)); //1 mois de délai defini avant d'effectuer la tache
 	        TimerTask rendreAbonneDisponible = new TimerTask() { // création et spécification de la tache à effectuer
 	            @Override
 	                public void run() {
 	            			bibli.retrouverDocument(numLivre).getAbonne().setEtat(EtatAbonne.Autorise);
 			                this.cancel();

 			               System.err.println("JE SUIS LA");
 			                 
 			             }
 	                }; 
 	           super.getTimer().schedule(rendreAbonneDisponible, timeBloque);
		}

		super.getTimer().cancel();

		return numLivre;
	}
}			


