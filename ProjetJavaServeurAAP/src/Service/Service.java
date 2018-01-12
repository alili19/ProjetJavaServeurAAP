package Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Timer;

import chaipa.Bibliotheque;

public abstract class Service implements Runnable{
	private Socket client;
	private Bibliotheque bibli;
	private Timer timer;
	
	protected Timer getTimer() {
		return timer;
	}

	protected Socket getClient() {
		return client;
	}
	
	protected Bibliotheque getBibli() {
		return bibli;
	}
	
	public Service(Socket accept,Bibliotheque bibli){
		this.client=accept;
		this.bibli=bibli;
	}
	
	@Override
	public void run() {
		
		try {
			BufferedReader lecture = new BufferedReader(new InputStreamReader(client.getInputStream()));
			PrintWriter out = new PrintWriter(client.getOutputStream ( ), true);

			String line = lecture.readLine();
			
			int numLivre = methodeALaCon(bibli, line); //A RENOMMER

			out.println("Le livre est "+ bibli.retrouverLivre(numLivre).getEtat());
		} catch (IOException e) {
				e.printStackTrace();
		}
		try {
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public abstract int methodeALaCon(Bibliotheque bibli, String line);

	public void lancer() {
		new Thread(this).start();		
	}

}
