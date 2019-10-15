public class Rectangle extends Figure {
    protected double longueur;
    protected double largeur;

    public Rectangle(double L, double l, double x, double y) {
        super(new Point(x, y));
        this.longueur = L;
        this.largeur = l;
    }

    public Rectangle() {
        super(new Point(0.0, 0.0));
        this.longueur = 0.0;
        this.largeur = 0.0;
    }

    public double perimetre() {
        return (2 * this.longueur + 2 * this.largeur);
    }

    public double aire() {
        return (this.longueur * this.largeur);
    }

    public String toString() {
        String out = "";
        out += "+";
        for (int i = 0; i < this.longueur; i++) {
            out += "--";
        }
        out += "+\n";
        for (int j = 0; j < this.largeur; j++) {
            out += "|";
            for (int i = 0; i < this.longueur; i++) {
                out += "  ";
            }
            out += "|\n";
        }
        out += "+";
        for (int i = 0; i < this.longueur; i++) {
            out += "--";
        }
        out += "+";
        return (out);

    }

    public void changerLongueur(double L) {
        this.longueur = L;
    }

    public void changerLargeur(double l) {
        this.largeur = l;
    }
}