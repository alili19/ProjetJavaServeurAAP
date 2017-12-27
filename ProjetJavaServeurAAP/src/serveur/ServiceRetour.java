package serveur;

import java.net.Socket;

import chaipa.Bibliotheque;

public class ServiceRetour implements Runnable{
	
	private static final int PORT=2700;
	private Bibliotheque bibli;
	private Socket client;
	
	public ServiceRetour(Socket accept) {
		this.client=accept;
	}


	@Override
	public void run() {
		
		/*bibli.retour(numLivre);*/
	}


	public void lancer() {
		new Thread(this).start();
	}

}
