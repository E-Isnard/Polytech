import java.util.HashMap;
import evenements.*;
import intervallestemps.*;

/**
 * Test
 */
public class Test {

    public static void main(String[] args) {
        try {
            String[] langues = { "Francais", "Anglais" };
            VisiteGuidee e = new VisiteGuidee(1, 2, "Visite", "Palazzo Reale", 3, langues);
            System.out.println(e);

            Intervalle<Integer> I = new Intervalle<Integer>(1, 0);
            System.out.println(I);

            Heure h1 = new Heure(12.25, 14.25);

            System.out.println(h1);

            HashMap<String, Heure> planing = new HashMap<String, Heure>();
            Heure h2 = new Heure(20.5, 21.25);
            planing.put("Jethro Tull", h1);
            planing.put("Gentle Giant", h2);

            Festival f = new Festival(3, 15, "Festival", "Turin", 30, planing);

            System.out.println(f);

            Seminaire s = new Seminaire(12, 13, "Oui", "Turin", "Andrea Pezzati", 2, -5);

            System.out.println(s);

            Intervalle<Double> I2 = new Intervalle<Double>(2.0, 2.0);
            System.out.println(I2);

            String[] oeuvres = new String[] { "Joconde", "Le Voyageur contemplant une mer de nuages" };

            Exposition expo = new Exposition(1, 3, "Expo", "Turin", oeuvres, 12);

            System.out.println(expo);

            

            


        } catch (DateException de) {
            System.err.println(de);
        } catch (HeureException he) {
            System.err.println(he);
        }

    }
}