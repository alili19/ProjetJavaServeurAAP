package serveur;

import java.io.IOException;
import java.net.ServerSocket;

import bibliotheque.Bibliotheque;
import service.Service;
import service.ServiceFactory;

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
				Service service = ServiceFactory.creerService(listen_socket.accept(), bibli, PORT);
				service.lancer();
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
