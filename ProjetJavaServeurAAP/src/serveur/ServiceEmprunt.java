package serveur;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import chaipa.Bibliotheque;

public class ServiceEmprunt implements Runnable{

	private static final int PORT=2600;
	private Socket client;
	
	public ServiceEmprunt(Socket accept) {
		this.client=accept;
	}

	@Override
	public void run() {
		
		
		/*je pense que nous devons par un fichier afin de pouvoir conserv� les donn�es
		 *  entr�es par le client (le numLivre et le numAbonn�e)
	
		bibli.emprunter(numLivre, numAbonne);*/
		
		
		
	}

	public void lancer() {
		new Thread(this).start();		
	}

}
