package serveur;

import java.io.IOException;

import abonne.Abonne_;
import bibliotheque.Bibliotheque;
import bibliotheque.Document;
import document.Abonne;
import document.Livre;

public class ApplicationServeur {
	private static Bibliotheque bibli=Bibliotheque.getInstance();
	
	public static void main(String[] args) {
	//creation de la bibliotheque
	Document l1 = new Livre();
	Document l2 = new Livre();
	Abonne a1 = new Abonne_();
	Abonne a2 = new Abonne_();
	
	bibli.ajouterDocument(l1);
	bibli.ajouterDocument(l2);
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
