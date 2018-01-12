package Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import chaipa.Bibliotheque;

public class ServiceRetour extends Service {
	
	public ServiceRetour(Socket accept, Bibliotheque bibli) {
		super(accept, bibli);
	}
	@Override
	public int methodeALaCon(Bibliotheque bibli, String line) {

		String[] words= line.split(";");
		int numLivre= Integer.parseInt(words[0]);
		String degrade=words[1];
		
		try {
			PrintWriter ecriture = new PrintWriter(super.getClient().getOutputStream ( ), true);
			ecriture.println("Vous venez de vous connecté au service de retour. Entrez le numero du livre."
					+ "Entrez OUI si le livre est dégradé et NON si il n'est pas dégradé.");

			if(degrade.equals("NON")){
				bibli.retour(numLivre, false);
			}
			else if(degrade.equals("OUI")){
				bibli.retour(numLivre, true);
			}
		
			super.getTimer().cancel();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		return numLivre;
	}
}			


