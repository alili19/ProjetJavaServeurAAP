package serveur;

import java.io.IOException;
import java.net.Socket;

public class ServiceReservation implements Runnable{

	private static final int PORT=2500;
	private final Socket client;

	
	public ServiceReservation(Socket socketClient) {
		this.client= socketClient;
	}

	
	@Override
	public void run() {
		
	/*	try{
			
		}catch(IOException e){
			
		}
		*/
		
		//Fin du service d'inversion
		System.out.println("*********Connexion terminée");
		try {client.close();} catch (IOException e2) {}
	}

	public void lancer() {
		new Thread(this).start();			
	}
	
	protected void finalize() throws Throwable {
		 client.close(); 
	}

}
