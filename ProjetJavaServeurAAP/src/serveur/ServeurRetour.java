package serveur;
import java.io.IOException;
import java.net.ServerSocket;

import chaipa.Bibliotheque;

public class ServeurRetour implements Runnable{

			private ServerSocket listen_socket_retour;
			private Bibliotheque bibli;

			
			// Cree un serveur TCP - objet de la classe ServerSocket
			ServeurRetour(int port, Bibliotheque bibliotheque) throws IOException {
				listen_socket_retour = new ServerSocket(port);
				this.bibli=bibliotheque;
			}
			// Le serveur ecoute et accepte les connections.
			// pour chaque connection, il cree un ServiceInversion, 
			// qui va la traiter.
			@Override
			public void run() {
				try{
					while(true){
						new ServiceRetour(listen_socket_retour.accept(),bibli).lancer();
					}
				}catch(IOException e){
					try{this.listen_socket_retour.close();}catch(IOException a){}
					System.err.println("Probleme sur le port"+e);
				}
			}
			
			// restituer les ressources --> finalize
			protected void finalize() throws Throwable{
				try{this.listen_socket_retour.close();}catch(IOException e){}

			}

}
