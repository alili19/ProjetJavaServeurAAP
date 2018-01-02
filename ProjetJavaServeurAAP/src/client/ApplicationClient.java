package client;

import java.io.IOException;
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
		socket.close();
	}
	catch (IOException e) { System.err.println(e); }
	// Refermer dans tous les cas la socket
	try { if (socket != null) socket.close(); } 
	catch (IOException e2) { ; }		
}
}
