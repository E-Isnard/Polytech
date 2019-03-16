import java.util.Random;
import PaD.*;

public class Jeu52 {
    private Carte jeu[] = new Carte[52]; // Tableau de cartes

    Jeu52() {
        // on remplie le tableau de cartes en parcourant toutes les valeurs et toutes les couleurs
        // possibles
        int i = 0;
        for (Valeur v : Valeur.values()) {
            for (Couleur c : Couleur.values()) {
                this.jeu[i] = new Carte(v, c);
                i++;
            }
        }

    }

    public Carte[] getJeu() {
        // Retoune le tableau de cartes du jeu
        return (this.jeu);
    }

    public String toString() {
        // On crée une chaîne de caracères ayant sur chaque ligne la chaîne de caractères
        // de chacune des cartes du tableau.
        String str = new String();
        for (int i = 0; i < 52; i++) {
            str += this.jeu[i] + "\n";
        }
        return (str);
    }

    public void melanger() {
        // On mélange les cartes du jeu en échangeant 52 fois deux cartes du tableaux prises au hasard.
        Random r = new Random();
        for (int i = 0; i < 52; i++) {
            int j = r.nextInt(52);
            int k = r.nextInt(52);
            Carte tempCarte = this.jeu[j];
            this.jeu[j] = this.jeu[k];
            this.jeu[k] = tempCarte;
        }

    }

    public void ordonnerCouleur() {
        // Ordonne les cartes du jeu en faisant un tri à bulle.
        // Ce tri consiste à parcourir le tableau par paire et d'échanger les deux cartes
        // si la premières a un ordinal plus grand que la deuxième.
        // Au bout d'une boucle on est sûr que la plus grand élément sera à la fin donc on 
        // aura une case de moin à parcourir.
        for (int i = 51; i >= 0; i--) {
            for (int j = 1; j <= i; j++) {
                if (this.jeu[j - 1].getCouleur().ordinal() > this.jeu[j].getCouleur().ordinal()) {
                    Carte tempCarte = this.jeu[j - 1]; // Carte servant à retenir la j-1 ème carte après l'avoir échangée
                    this.jeu[j - 1] = this.jeu[j];
                    this.jeu[j] = tempCarte;

                }
            }
        }

    }

    public void ordonnerValeur() {
        //idem que ordonnerCouleur mais avec les valeurs
        for (int i = 51; i >= 0; i--) {
            for (int j = 1; j <= i; j++) {
                if (this.jeu[j - 1].getValeur().ordinal() > this.jeu[j].getValeur().ordinal()) {
                    Carte tempCarte = this.jeu[j - 1];
                    this.jeu[j - 1] = this.jeu[j];
                    this.jeu[j] = tempCarte;

                }
            }
        }

    }

    public void ordonner() {
        //On combine les deux tris.
        this.ordonnerValeur();
        this.ordonnerCouleur();
    }

    public void dessinJeu(PlancheADessin pad) {
        // Affiche toutes les cartes du jeu
        // On affiche les cartes à la meme hauteur et quand on atteint un certain x on revient
        // à la ligne.
        int x = 10;
        int y = 30;
        for (int i = 0; i < 52; i++) {
            if (x > 630) {
                x = 10;
                y += 100;
            }
            this.jeu[i].dessin(pad, x, y);
            x += 50;

        }
    }
}
