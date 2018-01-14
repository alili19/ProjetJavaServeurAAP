package test;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import abonne.Abonne_;
import abonne.PasAutoriseException;
import bibliotheque.Bibliotheque;
import bibliotheque.Document;
import document.Abonne;
import document.EtatDocument;
import document.Livre;
import document.PasLibreException;

public class TestServeur {
	
	Document l1,l2;
	Abonne a1,a2;
	Bibliotheque bibli; 
	
	@Before
	public void init(){
		bibli=Bibliotheque.getInstance();
		l1 = new Livre();
		l2 = new Livre();
		a1 = new Abonne_();
		a2 = new Abonne_();
	}

	@Test
	public void test() {
		bibli.ajouterDocument(l1);
		bibli.ajouterDocument(l2);
		bibli.inscrireAbonne(a1);
		bibli.inscrireAbonne(a2);
		try {
			try {
				bibli.reserver(l1.numero(), a1.getNumero());
			} catch (PasAutoriseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (PasLibreException e) {
			e.printStackTrace();
		}
		assertEquals(EtatDocument.Reserve,l1.getEtatDocument());
		//assertEquals(EtatLivre.Disponible,l2.getEtatDocument());

		try {
			try {
				bibli.emprunter(l1.numero(),0);
			} catch (PasAutoriseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (PasLibreException e) {
			e.printStackTrace();
		}
		assertEquals(EtatDocument.Emprunte,l1.getEtatDocument());	
		assertEquals(l1.getAbonne(),0);
		

		try {
			try {
				bibli.emprunter(l2.numero(), a1.getNumero());
			} catch (PasAutoriseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (PasLibreException e) {
			e.printStackTrace();
		}

		assertEquals(EtatDocument.Emprunte,l2.getEtatDocument());
	
		try {
			try {
				bibli.emprunter(l2.numero(), a2.getNumero());
			} catch (PasAutoriseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (PasLibreException e) {
			e.printStackTrace();
		}
		assertEquals(l2.getAbonne(),a1);

	}

}



