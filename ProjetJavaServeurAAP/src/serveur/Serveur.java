package serveur;

import java.io.IOException;
import java.net.ServerSocket;

import Service.Service;
import chaipa.Bibliotheque;

public class Serveur implements Runnable{

	private ServerSocket listen_socket;
	private Bibliotheque bibli;
	private int PORT;
	
	// Cree un serveur TCP - objet de la classe ServerSocket
		public Serveur(int port, Bibliotheque bibliotheque) throws IOException {
			this.PORT=port;
			listen_socket = new ServerSocket(port);
			this.bibli=bibliotheque;
		}
	
	// Le serveur ecoute et accepte les connections.
	// pour chaque connection, il cree un ServiceInversion, 
	// qui va la traiter.
	
	@Override
	public void run() {
		try{
			while(true){
				new Service(listen_socket.accept(),bibli,PORT).lancer();
			/*	
				if( PORT == 2500){
					new ServiceReservation(listen_socket.accept(),bibli).lancer();
				}
				if( PORT == 2600){
					new ServiceEmprunt(listen_socket.accept(),bibli).lancer();
				}
				if( PORT == 2700){
					new ServiceRetour(listen_socket.accept(),bibli).lancer();
				}*/
			}
		} catch(IOException e){
				try{
					this.listen_socket.close();
				}
				catch(IOException a){}
				
			System.err.println("Probleme sur le port"+e);}
	
		}
	
	// restituer les ressources --> finalize
	protected void finalize() throws Throwable{
		try{this.listen_socket.close();}catch(IOException e){}

	}

}
