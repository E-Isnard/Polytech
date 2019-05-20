import java.io.*;
import java.util.*;

public class Exec {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws ArbreVideException {
        ArbreBinaire<String> b = new ArbreChaine<String>("x", new ArbreChaine<String>("y"), ArbreChaine.emptyTree);

        ArbreBinaire<String> a = new ArbreChaine<String>("a", new ArbreChaine<String>("b"), b);

        ArbreBinaire<String> c = new ArbreChaine<String>("i", a, new ArbreChaine<String>("j"));

        ArbreBinaire<String> d = new ArbreChaine<String>("i", new ArbreChaine<String>("j"), a);

        if (!b.isEmpty()) {
            // System.out.println(b.getValue());
            //ArbreChaine.affichage(c, "");
            
            ArrayList<Character> machin = new ArrayList();
            machin.add('2');
            machin.add('4');
            machin.add('9');
            machin.add('*');
            machin.add('+');
            ArbreChaine e = ArbreChaine.opArbre(machin);
            ArbreChaine deux = new ArbreChaine("2");
            ArbreChaine trois = new ArbreChaine("3");
            ArbreChaine douze = new ArbreChaine("12");
            ArbreChaine f = new ArbreChaine("*",deux,trois);
            ArbreChaine g = new ArbreChaine("+",douze,f);
            ArbreChaine.affichage(e,"");
            System.out.println(ArbreChaine.evalArbre(g));
            System.out.println(ArbreChaine.resArbre(f));

            
            // System.out.println(ArbreChaine.hauteur(c));
        }

    }

}