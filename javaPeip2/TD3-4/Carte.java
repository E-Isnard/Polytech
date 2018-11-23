import PaD.*;

public class Carte {
    private Couleur couleur;
    private Valeur valeur;
    private PaD.Image img;

    public Carte(Valeur v, Couleur c) {
        this.valeur = v;
        this.couleur = c;
        this.img = new Image("Cartes/" + this.valeur + "-" + this.couleur + ".gif");

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

    public void dessin(PlancheADessin pad, double x, double y) {
        Dessinable c = this.img;
        c.setOrig(x, y);
        pad.ajouter(c);
    }

    public Couleur getCouleur(){
        return(this.couleur);
    }

    public Valeur getValeur(){
        return(this.valeur);
    }
}