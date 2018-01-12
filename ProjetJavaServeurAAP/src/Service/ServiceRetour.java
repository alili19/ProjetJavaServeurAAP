package Service;

import java.net.Socket;

import chaipa.Bibliotheque;

public class ServiceRetour extends Service {
	
	public ServiceRetour(Socket accept, Bibliotheque bibli, int port) {
		super(accept, bibli, port);
	}
	private int PORT;

	if(numAbonne==0){
		bibli.retour(numLivre, false);
	}
	else if(numAbonne==1){
		bibli.retour(numLivre, true);
	}
	timer.cancel();
	ecriture.println("Vous venez de vous connecté au service de retour");
}			


}
