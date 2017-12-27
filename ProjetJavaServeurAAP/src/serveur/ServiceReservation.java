package serveur;

import java.io.IOException;
import java.net.Socket;

import chaipa.Bibliotheque;

public class ServiceReservation implements Runnable{

	private static final int PORT=2500;
	private final Socket client;
	private Bibliotheque bibli;
	
	public ServiceReservation(Socket socketClient) {
		this.client= socketClient;
	}

	
	@Override
	public void run() {
		/*bibli.reserver(numLivre, numAbonne);*/
	}

	public void lancer() {
		new Thread(this).start();			
	}
	
	protected void finalize() throws Throwable {
		 client.close(); 
	}

}
