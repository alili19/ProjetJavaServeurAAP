package serveur;
import java.io.IOException;
import java.net.ServerSocket;

public class ServeurRetour implements Runnable{

			private ServerSocket listen_socket_retour;
			
			// Le serveur ecoute et accepte les connections.
			// pour chaque connection, il cree un ServiceInversion, 
			// qui va la traiter.
			@Override
			public void run() {
				try{
					while(true){
						new ServiceRetour(listen_socket_retour.accept()).lancer();
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
