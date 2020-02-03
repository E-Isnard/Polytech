package lab1vide.recursionretroparcours;

public class Test {

	public static void main(String[] args) {
		// test de la somme
		int[] t = {3,12,2,5,18};
    	Somme so = new Somme(t);
    	System.out.println(so.possible(20));// true
       	System.out.println(so.possible(16));// false      	       	
       	
       	// test des voleurs
       	int[] poids = {12,16,3};
    	int[] prix = {2,6,28};
    	Voleur v = new Voleur(poids,prix);
       	System.out.println("Exemple 1");
    	System.out.println("butin poids 5 : "+ v.valeur(5));  // 28
    	System.out.println("butin poids 40 : "+ v.valeur(40));  // 36
    	System.out.println("butin poids 15 : "+ v.valeur(15));   // 30



	}

}
