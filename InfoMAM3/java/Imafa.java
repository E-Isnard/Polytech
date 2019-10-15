import java.util.Scanner;

/**
 * Imafa (vive l'argent)
 */
public class Imafa {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in); // Create a Scanner object
        System.out.println("Montant du paiement:");
        int paiement = scan.nextInt();
        scan.close();
        int franchise = paiement / 10;
        if (franchise > 4000) {
            franchise = 4000;
        }
        ;
        System.out.println("Votre franchise est de " + franchise + " €");

    }
}