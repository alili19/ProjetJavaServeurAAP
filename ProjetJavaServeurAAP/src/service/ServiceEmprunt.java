package service;

import java.net.Socket;
import java.util.TimerTask;

import abonne.EtatAbonne;
import abonne.PasAutoriseException;
import bibliotheque.Bibliotheque;
import document.EtatLivre;
import document.PasLibreException;

public class ServiceEmprunt extends Service{
	
	public ServiceEmprunt(Socket accept, Bibliotheque bibli) {
		super(accept, bibli);
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
		            	if(bibli.retrouverLivre(numLivre).getEtat()!= EtatLivre.Disponible){
		            		bibli.retrouverLivre(numLivre).getAbonne().setEtat(EtatAbonne.Interdit);
			                 this.cancel();
			                 
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
