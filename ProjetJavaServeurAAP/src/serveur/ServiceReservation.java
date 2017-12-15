package serveur;

import java.net.Socket;

public class ServiceReservation implements Runnable{

	private static final int PORT=2500;
	private final Socket client;

	
	public ServiceReservation(Socket socketClient) {
		this.client= socketClient;
	}

	
	@Override
	public void run() {
		
	}


	public void lancer() {
		// TODO Auto-generated method stub
		
	}

}
