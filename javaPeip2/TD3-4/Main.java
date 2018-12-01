import PaD.*;
// Cette classe est une copie de la classe Joueur sauf que le tableau de cartes comporte
// 5 cartes au lieu de 13 et qu'elle comporte les méthodes copie ordonnerCouleur, ordonnerValeur
// ordonner en plus. 
// Toutes les méthodes étant dans la classe Joueur ne sont pas commentées.
public class Main {
    private String nom;
    private Carte mesCartes[] = new Carte[5];

    Main(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return (this.nom);
    }

    public Carte[] getMain() {
        return (this.mesCartes);
    }

    public void prendreMesCartes(Jeu52 jeu, int de, int à) {
        int j = 0;
        for (int i = de; i <= à; i++) {
            this.mesCartes[j] = jeu.getJeu()[i];
            j++;

        }
    }

    public void montrerCartes(PlancheADessin pad, double h) {
        int x = 0;
        for (int i = 0; i < 5; i++) {
            Dessinable titre = new Texte(320, h + 25, this.nom);
            pad.ajouter(titre);
            this.mesCartes[i].dessin(pad, x, h);
            x += 40;
        }
    }

    public void copie(Main main) {
        // Cette méthode assigne à la main sur laquelle la méthode s'applique le tableau de cartes
        // de la main passée en argument.
        // On utilise dans cette méthode la méthode copieCarte pour bien crée un tableau de nouvelles
        // cartes et non pas de pointeurs pointant vers les cartes de la main copiée.
        for (int i = 0; i < 5; i++) {

            this.mesCartes[i] = main.getMain()[i].copieCarte();

        }

    }

    public void ordonnerCouleur() {
        // Il s'agit de la meme méthode que celle dans jeu52 sauf qu'elle commence à 4 au lieu de 52.
        for (int i = 4; i >= 0; i--) {
            for (int j = 1; j <= i; j++) {
                if (this.mesCartes[j - 1].getCouleur().ordinal() > this.mesCartes[j].getCouleur().ordinal()) {
                    Carte tempCarte = this.mesCartes[j - 1];
                    this.mesCartes[j - 1] = this.mesCartes[j];
                    this.mesCartes[j] = tempCarte;

                }
            }
        }

    }

    public void ordonnerValeur() {
        // Idem que pour ordonnerCouleur
        for (int i = 4; i >= 0; i--) {
            for (int j = 1; j <= i; j++) {
                if (this.mesCartes[j - 1].getValeur().ordinal() > this.mesCartes[j].getValeur().ordinal()) {
                    Carte tempCarte = this.mesCartes[j - 1];
                    this.mesCartes[j - 1] = this.mesCartes[j];
                    this.mesCartes[j] = tempCarte;

                }
            }
        }

    }

    public void ordonner() {
        // On combine les deux tris.
        this.ordonnerValeur();
        this.ordonnerCouleur();
    }

}