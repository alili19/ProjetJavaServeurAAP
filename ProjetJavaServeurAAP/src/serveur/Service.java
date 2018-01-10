package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import chaipa.Bibliotheque;
import chaipa.PasLibreException;

public class Service implements Runnable{
	private int PORT;
	private Socket client;
	private Bibliotheque bibli;
	
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
			String line = lecture.readLine();
			String[] words= line.split(";");
			int numLivre= Integer.parseInt(words[0]);
			int numAbonne=Integer.parseInt(words[1]);					
				if( PORT == 2500){
					bibli.reserver(numLivre, numAbonne);
				}
				if( PORT == 2600){
					bibli.emprunter(numLivre, numAbonne);
				}
				if( PORT == 2700){
					bibli.retour(numLivre);
				}			
				
				ecriture.println("Le livre est "+ bibli.retrouverLivre(numLivre).getEtat());
			

		} catch (IOException | PasLibreException e) {
				e.printStackTrace();
		}
		
	}
	

	public void lancer() {
		new Thread(this).start();		
	}

}
