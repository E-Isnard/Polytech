public class Carte {
    private Couleur couleur;
    private Valeur valeur;

    public Carte(Valeur v, Couleur c) {
        this.valeur = v;
        this.couleur = c;

    }

    public String toString() {
        return ("[" + this.valeur + "(" + this.valeur.valeur() + ")," + this.couleur + "]");
    }

    public int compareTo(Carte c) {
        if (this.valeur.valeur() > c.valeur.valeur()) {
            return (1);

        }

        else if (this.valeur.valeur() == c.valeur.valeur()) {
            return (0);
        }

        else {
            return (-1);
        }

    }
}