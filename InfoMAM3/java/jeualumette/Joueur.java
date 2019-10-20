import java.util.Scanner;

/**
 * Joueur
 */
public class Joueur {

    public int propositionJoueur(){
        Scanner sc1 = new Scanner(System.in);
        int n = 4;
        while(n<1 || n>3){
            n = sc1.nextInt();
        }
        //sc1.close();
        return n;
    }

}