package bibliotheque;

import abonne.PasAutoriseException;
import document.Abonne;
import document.EtatDocument;
import document.PasLibreException;

public interface Document {
	int numero();
	Abonne getAbonne();
	void setAbonne(Abonne ab);
	void setEtatDocument(EtatDocument e);
	EtatDocument getEtatDocument();
	void reserver(Abonne ab) throws PasLibreException, PasAutoriseException ;
	void emprunter(Abonne ab) throws PasLibreException, PasAutoriseException;
	void retour() ; // document rendu ou annulation réservation
}