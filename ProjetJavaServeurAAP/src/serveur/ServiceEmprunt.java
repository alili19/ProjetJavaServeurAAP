package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import chaipa.Bibliotheque;
import chaipa.PasLibreException;

public class ServiceEmprunt implements Runnable{

	private Socket client;
	private Bibliotheque bibli;
	
	public ServiceEmprunt(Socket accept, Bibliotheque bibliotheque) {
		this.client=accept;
		this.bibli=bibliotheque;
	}

	@Override
	public void run() {
		
		try {

			BufferedReader lecture = new BufferedReader(new InputStreamReader(client.getInputStream()));
			PrintWriter ecriture= new PrintWriter(client.getOutputStream ( ), true);
			
			String line = lecture.readLine();
			String[] words = line.split(";");
			int numLivre=Integer.parseUnsignedInt(words[0]);
			int numConnect=Integer.parseUnsignedInt(words[1]);
			bibli.emprunter(numLivre, numConnect);
			
			ecriture.print("Le livre "+ numLivre + " a bien été emprunté");

		} catch (PasLibreException | IOException e) {
			e.printStackTrace();
		}
	}

	public void lancer() {
		new Thread(this).start();		
	}

}
