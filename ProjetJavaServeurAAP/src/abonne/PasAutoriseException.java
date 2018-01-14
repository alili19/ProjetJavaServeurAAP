package abonne;

@SuppressWarnings("serial")
public class PasAutoriseException extends Exception{

	public PasAutoriseException(String message) {
		super(message);
	}

	public String getMessage(){
		return "BUG";
	}
}
