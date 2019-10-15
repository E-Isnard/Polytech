public class Carre extends Rectangle {
    public Carre(double cote, double x, double y) {
        super(cote, cote, x, y);
    }

    public void changerLongueur(double L) {
        this.largeur = L;
        this.longueur = L;
    }

    public void changerLargeur(double l) {
        this.largeur = l;
        this.longueur = l;
    }
}