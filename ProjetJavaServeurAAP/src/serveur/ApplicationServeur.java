package serveur;

import java.io.IOException;
import java.util.Scanner;

import chaipa.Abonne;
import chaipa.Bibliotheque;
import chaipa.Livre;

public class ApplicationServeur {
	private static Bibliotheque bibli=Bibliotheque.getInstance();
	
	public static void main(String[] args) {
	//creation de la bibliotheque
	Livre l1 = new Livre();
	Livre l2 = new Livre();
	Abonne a1 = new Abonne();
	Abonne a2 = new Abonne();
	
	bibli.ajouterLivre(l1);
	bibli.ajouterLivre(l2);
	bibli.inscrireAbonne(a1);
	bibli.inscrireAbonne(a2);
			try {
				new Thread(new Serveur(2500,bibli)).start();
				new Thread(new Serveur(2600,bibli)).start();
				new Thread(new Serveur(2700,bibli)).start();
				System.out.println("Serveurs lancés");
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println("Pb lors de la création du serveur :"+e);
			}
			
	}
	
}
