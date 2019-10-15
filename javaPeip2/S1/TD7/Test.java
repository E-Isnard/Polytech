import java.io.IOException;
import java.util.Vector;

public class Test {
    public static void main(String[] args) {
        Fichier f = new Fichier("Coucou");
        Fichier f2 = new Fichier("Coucou2");
        Fichier f3 = null;
        Vector v = new Vector<Integer>();
        f.aleatoire(5);
        f2.aleatoire(8);
        try {
            f3 = f2.fusionner(f);
        } catch (IOException e) {
            System.err.println(e);
        }
        System.out.println(f);
        System.out.println(f2);
        System.out.println();
        System.out.println(f3);

    }
}