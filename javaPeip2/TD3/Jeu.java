import PaD.*;

public class Jeu {

    public static void main(String[] args) {
        PlancheADessin pad = new PlancheADessin();
        
        Jeu52 jeu1 = new Jeu52();
        jeu1.melanger();

        Joueur raimu = new Joueur("Raimu");
        Joueur wanda = new Joueur("Wanda");
        Joueur martin = new Joueur("Martin");
        Joueur nicolas = new Joueur("Nicolas");

        raimu.prendreMesCartes(jeu1, 0, 12);
        raimu.montrerCartes(pad, 30);

        wanda.prendreMesCartes(jeu1, 12, 25);
        wanda.montrerCartes(pad, 100);

        martin.prendreMesCartes(jeu1, 25, 38);
        martin.montrerCartes(pad, 170);

        nicolas.prendreMesCartes(jeu1, 38, 51);
        nicolas.montrerCartes(pad, 240);

    }
}