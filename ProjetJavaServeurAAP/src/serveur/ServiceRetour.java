package serveur;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import chaipa.Bibliotheque;

public class ServiceRetour implements Runnable{
	
	private static final int PORT=2700;
	private Bibliotheque bibli;
	private Socket client;
	
	public ServiceRetour(Socket accept, Bibliotheque bibliotheque) {
		this.client=accept;
		this.bibli=bibliotheque;
	}


	@Override
	public void run() {
			try {
				BufferedReader lecture = new BufferedReader(new InputStreamReader(client.getInputStream()));
				PrintWriter ecriture= new PrintWriter(client.getOutputStream ( ), true);
				int numLivre=Integer.parseUnsignedInt(lecture.readLine());
				
				bibli.retour(numLivre);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	public void lancer() {
		new Thread(this).start();
	}

}
