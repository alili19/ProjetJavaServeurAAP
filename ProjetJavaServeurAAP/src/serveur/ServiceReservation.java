package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import chaipa.Bibliotheque;
import chaipa.PasLibreException;

public class ServiceReservation implements Runnable{

	private final Socket client;
	private Bibliotheque bibli;
	
	public ServiceReservation(Socket socketClient, Bibliotheque bibliotheque) {
		this.client= socketClient;
		this.bibli=bibliotheque;
	}

	
	@Override
	public void run() {
		System.out.println("-----Conexion démarrée-----");
		try {
			BufferedReader lecture = new BufferedReader(new InputStreamReader(client.getInputStream()));
			PrintWriter ecriture= new PrintWriter(client.getOutputStream ( ), true);
			String line = lecture.readLine();
			String[] words = line.split(";");
			int numLivre=Integer.parseUnsignedInt(words[0]);
			int numConnect=Integer.parseUnsignedInt(words[1]);
			String invLine = new String (new StringBuffer(line).reverse());
			bibli.reserver(numLivre, numConnect);
			System.out.println("alison "+ "--"+numLivre+"--" + bibli.retrouverLivre(numLivre).getEtat());
			//ecriture.println(invLine);
			ecriture.println("Le livre "+ numLivre + " a bien été reservé");
		} catch (PasLibreException | IOException e) {
			e.printStackTrace();
		}
	}

	public void lancer() {
		new Thread(this).start();			
	}
	
	protected void finalize() throws Throwable {
		 client.close(); 
	}

}
