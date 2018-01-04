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
	Scanner sc = new Scanner(System.in);
	System.out.println("Bonjour, que souhaitez-vous faire ? \n 1.Reserver un livre \n 2.Emprunter un livre \n 3. Retourner un livre");
	int str = sc.nextInt();
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
		default:
			break;
		}

		socket = new Socket(HOST, PORT);
		BufferedReader sin = new BufferedReader (new InputStreamReader(socket.getInputStream ( )));
		PrintWriter sout = new PrintWriter (socket.getOutputStream ( ), true);
		// Cree le stream pour lire du texte a partir du clavier 
		// (on pourrait aussi utiliser Scanner)
		BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));			
		// Informe l'utilisateur de la connection
		System.out.println("Connecté au serveur " + socket.getInetAddress() + ":"+ socket.getPort());
		
		String line;
		
		System.out.println(sin.readLine());
		line = clavier.readLine();
		// envoie au serveur
		sout.println(line);
		// lit la réponse provenant du serveur
		line = sin.readLine();
		// Verifie si la connection est fermee.
		// Si oui on sort de la boucle
		if (line == null) { 
			System.out.println("Connection fermee par le serveur."); 
		} else
			// Ecrit la ligne envoyee par le serveur
			System.out.println(line);
		
		socket.close();
	}
	catch (IOException e) { System.err.println(e); }
	// Refermer dans tous les cas la socket
	try { if (socket != null) socket.close(); } 
	catch (IOException e2) { ; }		
}
}
