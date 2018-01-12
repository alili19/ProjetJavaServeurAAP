package Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

import chaipa.Bibliotheque;
import chaipa.EtatAbonne;
import chaipa.EtatLivre;
import chaipa.PasAutoriseException;
import chaipa.PasLibreException;

public class ServiceEmprunt extends Service{
	
	public ServiceEmprunt(Socket accept, Bibliotheque bibli) {
		super(accept, bibli);
	}
	
	@Override
	public int methodeALaCon(Bibliotheque bibli, String line) {
		String[] words= line.split(";");
		int numLivre= Integer.parseInt(words[0]);
		int numAbonne=Integer.parseInt(words[1]);
		try {
			PrintWriter ecriture = new PrintWriter(super.getClient().getOutputStream ( ), true);
				
			bibli.emprunter(numLivre, numAbonne);
			Timer timer=new Timer();

			long time = (long) (1.21*Math.pow(10, 9)); //2 semaines de délai defini avant d'effectuer la tache
	        TimerTask rendreAbonneInterdit = new TimerTask() {     // création et spécification de la tache à effectuer
	            @Override
	                public void run() {
		            	if(bibli.retrouverLivre(numLivre).getEtat()!= EtatLivre.Disponible){
		            		bibli.retrouverLivre(numLivre).getAbonne().setEtat(EtatAbonne.Interdit);
			                 this.cancel();
			             }
	                }
	        };
	        timer.schedule(rendreAbonneInterdit,time);  
			
	        ecriture.println("Vous venez de vous connecté au service d'emprunt");
		} catch (PasAutoriseException|PasLibreException | IOException e) {
			e.printStackTrace();
		}
		
		
		
		return numLivre;
	}

}
