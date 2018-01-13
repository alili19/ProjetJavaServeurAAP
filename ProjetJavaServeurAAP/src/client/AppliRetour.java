package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class AppliRetour {
	
	private static int PORT=2700;
	private final static String HOST = "localhost"; 

	public static void main(String[] args) {
		
		Socket socket = null;
				try {
					socket = new Socket(HOST, PORT);
					// Informe l'utilisateur de la connexion
					System.out.println("Connecté au serveur " + socket.getInetAddress() + " : "+ socket.getPort());		
					
					System.out.println("Bonjour\n Pour retourner un livre veuillez entrer le numero du livre de l'adhérent");
				
					// Creation du stream pour lire les entrées clavier 
					BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));
					String line = clavier.readLine();
					System.out.println("Le livre est-il dégradé ? Entrez OUI ou NON");
					String degradation =clavier.readLine();
				
					PrintWriter sout = new PrintWriter (socket.getOutputStream ( ), true);
					String ligneEnvoyee = line+";"+degradation;
					sout.println(ligneEnvoyee);
					
					// lit la réponse provenant du serveur
					BufferedReader lecture = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					String retour = lecture.readLine();
				
					// Verifie si la connexion est fermee.
					if (retour == null) { 
						System.out.println("connexion fermee par le serveur."); 
					} else{
						System.out.println(retour);
					}
				
					if (socket != null) socket.close(); 
			
					socket.close();
				} catch (IOException e) {e.printStackTrace();}
		}			
}
