package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Timer;

import bibliotheque.Bibliotheque;

public abstract class Service implements Runnable{
	private Socket client;
	private Bibliotheque bibli;
	private Timer timer;
	
	public Service(Socket accept,Bibliotheque bibli){
		this.client=accept;
		this.bibli=bibli;
		timer=new Timer();
	}
	
	@Override
	public void run() {
		
		try {
			BufferedReader lecture = new BufferedReader(new InputStreamReader(client.getInputStream()));
			PrintWriter out = new PrintWriter(client.getOutputStream ( ), true);

			String line = lecture.readLine();
			
			int numLivre = appelAuService(bibli, line);
			
			out.println("Le livre est "+ bibli.retrouverDocument(numLivre).getEtatDocument()+"\n \n");
		} catch (IOException e) {
				e.printStackTrace();
		}
		try {
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void lancer() {
		new Thread(this).start();		
	}
	
	public abstract int appelAuService(Bibliotheque bibli, String line);
	
	protected Timer getTimer() {
		return timer;
	}

	protected Socket getClient() {
		return client;
	}
	
	protected Bibliotheque getBibli() {
		return bibli;
	}
	

}
