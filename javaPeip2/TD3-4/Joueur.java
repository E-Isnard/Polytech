import PaD.*;
// Classe servant à créer un jouer avec un nom et un main (qui est un tableau de 13 cartes)
public class Joueur {
    private String nom;// nom du joueur
    private Carte mesCartes[] = new Carte[13];// main du joueur

    Joueur(String nom) {
        // On assigne au joueur le nom qu'on lui a donné
        this.nom = nom;
    }

    public Carte[] getMain() {
        // Retounr la main du joueur
        return(this.mesCartes);
    }

    public void prendreMesCartes(Jeu52 jeu, int de, int à) {
        // On prend les cartes d'un jeu à partir de la "de-ème" carte jusqu'à
        // la "à-ème"
        int j = 0;
        for (int i = de; i <= à; i++) {
            this.mesCartes[j] = jeu.getJeu()[i];
            j++;

        }
    }

    public void montrerCartes(PlancheADessin pad, double h) {
        // On affiche la main du joueur à la hauteur h et on affiche son nom à côté
        int x = 0;
        for (int i = 0; i < 13; i++) {
            Dessinable titre = new Texte(620, h + 25, this.nom);
            pad.ajouter(titre);
            this.mesCartes[i].dessin(pad, x, h);
            x += 40;
        }
    }

}