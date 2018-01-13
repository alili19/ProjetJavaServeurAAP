package service;

import java.net.Socket;

import bibliotheque.Bibliotheque;

public class ServiceRetour extends Service {
	
	public ServiceRetour(Socket accept, Bibliotheque bibli) {
		super(accept, bibli);
	}
	
	@Override
	public int appelAuService(Bibliotheque bibli, String line) {

		String[] words= line.split(";");
		int numLivre= Integer.parseInt(words[0]);
		String degrade=words[1];

		if(degrade.equals("NON")){
			bibli.retour(numLivre, false);
		}
		else if(degrade.equals("OUI")){
			bibli.retour(numLivre, true);
		}

		super.getTimer().cancel();

		return numLivre;
	}
}			


