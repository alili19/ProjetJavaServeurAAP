package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

import chaipa.Bibliotheque;
import chaipa.EtatAbonne;
import chaipa.EtatLivre;
import chaipa.PasAutoriseException;
import chaipa.PasLibreException;

public class Service implements Runnable{
	private int PORT;
	private Socket client;
	private Bibliotheque bibli;
	private Timer timer=new Timer();
	public Service(Socket accept,Bibliotheque bibli, int port){
		this.PORT=port;
		this.client=accept;
		this.bibli=bibli;
	}
	
	@Override
	public void run() {
		
		try {
			BufferedReader lecture = new BufferedReader(new InputStreamReader(client.getInputStream()));
			PrintWriter ecriture = new PrintWriter(client.getOutputStream ( ), true);
			PrintWriter out = new PrintWriter(client.getOutputStream ( ), true);

			String line = lecture.readLine();
			String[] words= line.split(";");
			int numLivre= Integer.parseInt(words[0]);
			int numAbonne=Integer.parseInt(words[1]);	
				if( PORT == 2500){
					try {
						bibli.reserver(numLivre, numAbonne);
					} catch (PasAutoriseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ecriture.println("Vous venez de vous connecté au service de réservation");

				}
				if( PORT == 2600){
					try {
						bibli.emprunter(numLivre, numAbonne);
						
						long time = (long) (1.21*Math.pow(10, 9));
		        		//7200000; // délai defini avant d'effectuer la tache
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
						
						
					} catch (PasAutoriseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ecriture.println("Vous venez de vous connecté au service d'emprunt");

				}
				if( PORT == 2700){
					if(numAbonne==0){
						bibli.retour(numLivre, false);
					}
					else if(numAbonne==1){
						bibli.retour(numLivre, true);
					}
					timer.cancel();
					ecriture.println("Vous venez de vous connecté au service de retour");
				}			
				
				out.println("Le livre est "+ bibli.retrouverLivre(numLivre).getEtat());
			

		} catch (IOException | PasLibreException e) {
				e.printStackTrace();
		}
		try {
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public void lancer() {
		new Thread(this).start();		
	}

}
