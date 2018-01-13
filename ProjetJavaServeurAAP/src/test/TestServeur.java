package test;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import abonne.Abonne;
import abonne.PasAutoriseException;
import bibliotheque.Bibliotheque;
import document.EtatLivre;
import document.Livre;
import document.PasLibreException;

public class TestServeur {
	
	Livre l1,l2;
	Abonne a1,a2;
	Bibliotheque bibli; 
	
	@Before
	public void init(){
		bibli=Bibliotheque.getInstance();
		l1 = new Livre();
		l2 = new Livre();
		a1 = new Abonne();
		a2 = new Abonne();
	}

	@Test
	public void test() {
		bibli.ajouterLivre(l1);
		bibli.ajouterLivre(l2);
		bibli.inscrireAbonne(a1);
		bibli.inscrireAbonne(a2);
		try {
			try {
				bibli.reserver(l1.getNumero(), a1.getNumero());
			} catch (PasAutoriseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (PasLibreException e) {
			e.printStackTrace();
		}
		assertEquals(EtatLivre.Reserve,l1.getEtat());
		//assertEquals(EtatLivre.Disponible,l2.getEtat());

		try {
			try {
				bibli.emprunter(l1.getNumero(),0);
			} catch (PasAutoriseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (PasLibreException e) {
			e.printStackTrace();
		}
		assertEquals(EtatLivre.Emprunte,l1.getEtat());	
		assertEquals(l1.getAbonne(),0);
		

		try {
			try {
				bibli.emprunter(l2.getNumero(), a1.getNumero());
			} catch (PasAutoriseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (PasLibreException e) {
			e.printStackTrace();
		}

		assertEquals(EtatLivre.Emprunte,l2.getEtat());
	
		try {
			try {
				bibli.emprunter(l2.getNumero(), a2.getNumero());
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



