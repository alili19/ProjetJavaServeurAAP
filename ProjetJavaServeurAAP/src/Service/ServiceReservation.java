package Service;

import java.net.Socket;

import chaipa.Bibliotheque;
import chaipa.PasAutoriseException;

public class ServiceReservation extends Service{

	public ServiceReservation(Socket accept, Bibliotheque bibli, int port) {
		super(accept, bibli, port);
	}
	private static final int PORT=2500;
	
	try {
		bibli.reserver(numLivre, numAbonne);
	} catch (PasAutoriseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	ecriture.println("Vous venez de vous connecté au service de réservation");


}
