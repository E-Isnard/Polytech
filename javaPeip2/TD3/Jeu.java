import PaD.*;

public class Jeu {
    public static void main(String[] args) {
        PlancheADessin pad = new PlancheADessin();
        Jeu52 jeu1 = new Jeu52();
        System.out.println(jeu1);
        jeu1.melanger();
        jeu1.dessinJeu(pad);
    }
}