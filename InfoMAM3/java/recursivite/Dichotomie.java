package recursivite;

import java.util.Scanner;

/**
 * Dichotomie
 */
public class Dichotomie {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean trouver = false;
        int min = 0;
        int max = 100;
        System.out.println("Penser a un nombre entre 0 et 99");

        while (!trouver) {
            int c = (max + min) / 2;
            System.out.println("Est-il sup,inf,egal a " + c);
            String choix = sc.nextLine();

            if (choix.equals("egal")) {
                trouver = true;
            } else if (choix.equals("inf")) {

                max = c;

            } else if (choix.equals("sup")) {

                min = c;

            }
            
        }
        sc.close();

    }
}