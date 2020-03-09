package abr;

class TestABR {

	private ABRInteger s;

	private TestABR() {
		// création de l'arbre
		s = new ABRInteger();
		s.ajouter(45);
		s.ajouter(1236);
		s.ajouter(2);
		s.ajouter(5);
		s.ajouter(563);
		s.ajouter(2);
		s.ajouter(70);
		s.ajouter(36);
	}

	private void testDeBase() {
		System.out.println("tests de base");

		System.out.println("\narbre de depart");
		System.out.println(s);

		// les tests 

		// recherche d'un élément
		System.out.println("Cherche 45 : " + s.existe(45));
		System.out.println("Cherche 12 : " + s.existe(12));

		// on attend 7
		System.out.println("Taille de l'arbre : " + s.size());

		// affichage par ordre croissant
		System.out.println("affichage par ordre croissant");
		s.afficheParOrdre();
		System.out.println();
	}

	private void testSuppression(int x){
		// suppression d'un élément
		System.out.println("suppression de " + x );
		s.delete(x);
		System.out.println("Arbre APRES suppression");
		System.out.println(s);
		System.out.println("**************\n");
	}

	private void testAjout(int x){
		// suppression d'un élément
		System.out.println("ajout de " + x );
		s.ajouter(x);
		System.out.println("Arbre APRES ajout");
		System.out.println(s);
		System.out.println("**************\n");
	}
	
	public static void main(String args[]){
		TestABR ta = new TestABR();
		
		ta.testDeBase();
		ta.testSuppression(45);
		ta.testAjout(18);

		

  }

}
