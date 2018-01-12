package Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import chaipa.Bibliotheque;
import chaipa.PasAutoriseException;
import chaipa.PasLibreException;

public class ServiceReservation extends Service{

	public ServiceReservation(Socket accept, Bibliotheque bibli) {
		super(accept, bibli);
	}	

	@Override
	public int methodeALaCon(Bibliotheque bibli, String line) {
		String[] words= line.split(";");
		int numLivre= Integer.parseInt(words[0]);
		int numAbonne=Integer.parseInt(words[1]);
		
		try {
			
		PrintWriter ecriture = new PrintWriter(super.getClient().getOutputStream ( ), true);
		ecriture.println("Vous venez de vous connecté au service de retour. Entrez le numero du livre."
				+ "Entrez votre numero d'abonne.");

			bibli.reserver(numLivre, numAbonne);

		} catch (PasAutoriseException|PasLibreException | IOException e) {
			e.printStackTrace();
		}
		
		return numLivre;
	}


}
