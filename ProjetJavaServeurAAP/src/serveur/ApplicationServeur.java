package serveur;

import java.io.IOException;
import java.util.Scanner;

import chaipa.Abonne;
import chaipa.Bibliotheque;
import chaipa.Livre;

public class ApplicationServeur {
	
	private static int PORT;
	private static Bibliotheque bibli=new Bibliotheque();
	
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
		
		
		Scanner sc = new Scanner(System.in);
			System.out.println("Bonjour, que souhaitez-vous faire ? "
					+ "\n 1.Reserver un livre "
					+ "\n 2.Emprunter un livre "
					+ "\n 3. Retourner un livre");
			int str = sc.nextInt();
			switch (str) {
				case 1:
					PORT = 2500;
					break;
				case 2:
					PORT = 2600;
					break;
				case 3:
					PORT = 2700;
					break;
				default:
					break;
			}

			try {
				new Thread(new Serveur(PORT,bibli)).start();
				System.out.println("Serveur lancé sur le port "+PORT);
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println("Pb lors de la création du serveur :"+e);
			}
			sc.close();
	}
	
}
