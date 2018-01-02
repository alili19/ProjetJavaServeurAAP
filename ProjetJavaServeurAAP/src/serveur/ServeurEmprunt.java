package serveur;
import java.io.IOException;
import java.net.ServerSocket;

public class ServeurEmprunt implements Runnable {

		private ServerSocket listen_socket_emprunt;
		
		
		// Cree un serveur TCP - objet de la classe ServerSocket
		ServeurEmprunt(int port) throws IOException {
			listen_socket_emprunt = new ServerSocket(port);
		}
		
		// Le serveur ecoute et accepte les connections.
		// pour chaque connection, il cree un ServiceInversion, 
		// qui va la traiter.
		@Override
		public void run() {
			try{
				while(true){
					new ServiceEmprunt(listen_socket_emprunt.accept()).lancer();
				}
			}catch(IOException e){
				try{this.listen_socket_emprunt.close();}catch(IOException a){}
				System.err.println("Probleme sur le port"+e);
			}
		}
		
		// restituer les ressources --> finalize
		protected void finalize() throws Throwable{
			try{this.listen_socket_emprunt.close();}catch(IOException e){}
		}

}



