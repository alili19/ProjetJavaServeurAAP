package chaipa;
import java.util.TimerTask;

public class MonTimer extends TimerTask{

	@Override
	public void run() {
		/*Contenir les traitements � ex�cuter donc peut 
		 * etre rendre le livre non dispo ici*/
		System.out.println("Le temps est �coul�, "
				+ "le livre que vous avez r�serv� ne peut plus �tre emprunt�");
				
		this.cancel();
	}

}
