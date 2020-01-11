package src.activites.casino;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.lang.Thread;

/**
 * Casino: Classe permettant de jouer a la roulette
 * 
 * @author Theo Picke et Enzo Isnard
 */
public class Casino {

    private int nbJetons;

    /**
     * Contructeur de Casino Fixe le nombres de jetons qu'on a en entrant dans le
     * caisno
     * 
     * @param nbJetons Nombre de jetons que possède initialement le joueur quand il
     *                 rentre dans le casino
     */

    public Casino(int nbJetons) {
        this.nbJetons = nbJetons;
    }

    /**
     * Methode permettant de jouer a la roulette On choisit un nombre entre 200 et
     * 400 puis on incremente un compteur jusqu'à atteindre ce nombre.Comme les
     * nombres d'une roulettes vont de 0 à 35 on prend le reste par 36 à chaque fois
     * qu'on affiche le compteur.On efface a chaque fois le compteur grace au
     * caractère \b et on fait des poses a chaque incrementation avec la classe
     * Thread.
     * 
     * 
     * @param m
     * @throws InterruptedException
     * 
     * 
     * 
     */

    public void roulette(Mise m) throws InterruptedException {

        if (m.getMontant() <= nbJetons) {
            System.out.println("C'est parti !");
            nbJetons -= m.getMontant();
            Random r = new Random();
            int n = r.nextInt(200) + 200;
            int k = 0;
            for (int i = 0; i <= n; i++) {
                k = i % 36;
                System.out.print(k);
                if (n - i >= 50) {
                    Thread.sleep(50);
                } else {
                    Thread.sleep((i / 2) + 50);
                }
                if (i % 36 == 35) {
                    // Cette partie sert à gérer le moment où on passe de 35 en 0
                    System.out.print("\b");
                    System.out.print(" ");

                }
                System.out.print("\b\b");

            }
            System.out.println(k);
            String paris = m.getParis();
            if (String.valueOf(k).equals(paris)) {
                affichageGainPerte(true, 35, m);

            } else if (paris.equals("pair") && k % 2 == 0 && k != 0) {

                affichageGainPerte(true, 2, m);

            } else if (paris.equals("impair") && k % 2 == 1) {
                affichageGainPerte(true, 2, m);
            } else if (couleurNombre(k).equals(paris) && k != 0) {
                affichageGainPerte(true, 2, m);
            } else if (manquePasse(k).equals(paris)) {
                affichageGainPerte(true, 2, m);
            } else {
                affichageGainPerte(false, 0, m);
            }

        } else {
            System.out.println("Vous n'avez pas assez de jetons !");
        }
    }

    /**
     * Methode qui affiche un message apres avoir jouer a la roulette.
     * 
     * @param g              g est vraie si c'est un gain et faux sinon
     * 
     * @param multiplicateur multiplicateur de la mise
     * @param m              mise
     */

    public void affichageGainPerte(boolean g, int multiplicateur, Mise m) {

        if (g) {
            System.out.println("Bravo !");
            int gain = m.getMontant() * (multiplicateur-1); // on gagne le montant de la mise fois le multiplicateur - la mise
            System.out.println("Vous avez gagne " + gain + " jetons !");
            nbJetons += gain;
        } else {
            System.out.println("Vous avez perdu =(");
            System.out.println("Vous perdez votre mise (" + m.getMontant() + " jetons)");

        }
    }

    /**
     * Methode qui renvoie la couleur du nombre n
     * 
     * @param n nombre de la roulette
     * @return "rouge" ou "noir"
     */
    public static String couleurNombre(int n) {
        String couleur = "";
        Integer[] nombresRouge = { 1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36 };
        List<Integer> nombresRougeList = Arrays.asList(nombresRouge);
        if (nombresRougeList.contains(n)) {
            couleur = "rouge";
            return couleur;
        } else {
            couleur = "noir";
            return couleur;
        }
    }

    /**
     * 
     * Methode qui nous dit si n est entre 1 et 18 ou non.
     * 
     * 
     * @param n
     * @return "manque" ou "passe"
     */
    public static String manquePasse(int n) {

        if (n >= 1 && n <= 18) {
            return "manque";
        } else {
            return "passe";
        }
    }

    /**
     * Rajoute des jetons au Casino
     * 
     * @param jetonsEnPlus
     */
    public void rajouterJetons(int jetonsEnPlus) {
        nbJetons += jetonsEnPlus;

    }

    /**
     * getter pour recuperer le nombre de jetons
     * 
     * @return nbJetons
     */
    public int getNbJetons() {
        return nbJetons;
    }

}