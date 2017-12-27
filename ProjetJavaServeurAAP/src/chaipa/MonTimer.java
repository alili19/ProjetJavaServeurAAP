package chaipa;
import java.util.TimerTask;

public class MonTimer extends TimerTask{

	@Override
	public void run() {
		/*Contenir les traitements à exécuter donc peut 
		 * etre rendre le livre non dispo ici*/
		System.out.println("Le temps est écoulé, "
				+ "le livre que vous avez réservé ne peut plus être emprunté");
				
		this.cancel();
	}

}
