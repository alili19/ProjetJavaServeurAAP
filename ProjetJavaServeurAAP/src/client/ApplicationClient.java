package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ApplicationClient {
	private static int PORT;
	private final static String HOST = "localhost"; 

	public static void main(String[] args) {
		Socket socket = null;
		int str=0;
		Scanner sc = new Scanner(System.in);
		while(str!=4){
		System.out.println("Bonjour, que souhaitez-vous faire ? "
				+ "\n 1.Reserver un livre "
				+ "\n 2.Emprunter un livre "
				+ "\n 3. Retourner un livre"
				+ "\n 4. Quitter");

		str = sc.nextInt();
		try {
			switch (str) {
			case 1:
				PORT = 2500;
				break;
			case 2:
				PORT = 2600;
				break;
			case 3:
				PORT = 2700;
				break;
			case 4 :
				return;
			default:
				break;
			}
	
			socket = new Socket(HOST, PORT);
			
			// Informe l'utilisateur de la connexion
			System.out.println("Connecté au serveur " + socket.getInetAddress() + " : "+ socket.getPort());
			
			BufferedReader lecture = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter sout = new PrintWriter (socket.getOutputStream ( ), true);
			
			// Cree le stream pour lire du texte a partir du clavier 
			// (on pourrait aussi utiliser Scanner)
			BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));	
			
			String line;
			System.out.println("Entrez votre numero de livre ; Entrez votre numéro d'abonné");
			line = clavier.readLine();
			
			// envoie au serveur
			sout.println(line);
			
			// lit la réponse provenant du serveur
			line = lecture.readLine();
		
			// Verifie si la connexion est fermee.
			// Si oui on sort de la boucle
				if (line == null) { 
					System.out.println("connexion fermee par le serveur."); 
				} else{
					// Ecrit la ligne envoyee par le serveur
					System.out.println(line);
				}
			} catch (IOException e) {
				System.err.println(e); 
			}
			// Refermer dans tous les cas la socket
			try { 
				if (socket != null) socket.close(); 
			} catch (IOException e2) { ; }	
	
	}

		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sc.close();	
	}
}
