package chaipa;

@SuppressWarnings("serial")
public class PasLibreException extends Exception {
	
	private Document document;


	public PasLibreException(String message){
		super(message);
	}


	public String getMessage(){
		return "Le document numero "+this.document.numero()+" n'est pas disponible";
	}
}
