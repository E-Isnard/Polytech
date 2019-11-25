import java.util.Random;
import java.util.Scanner;

/**
 * Jeu simple en Java
 * 
 */
public class Jeu {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random r = new Random();
        int n = r.nextInt(100);
        int k = sc.nextInt();
        System.out.println("Le but est de trouver le nombre caché (entre 0 et 100)");
        while (k != n) {
            if (k > n) {
                System.out.println("Trop grand");
            }
            else{
                System.out.println("Trop petit");
            }
            k=sc.nextInt();

        }

        sc.close();
        System.out.println("Bien joué =)");
    }

}
