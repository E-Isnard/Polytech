import java.util.Random;

/**
 * Ordinateur
 */
public class Ordinateur {

    public int propositionOrdi(){
        Random r = new Random();
        int n = r.nextInt(3) + 1;
        System.out.println("L'ordinateur a choisi de prendre "+n+" alumette(s)");
        return n;

    }
}