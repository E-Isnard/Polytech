import PaD.*;

public class Ellisse extends Figure {
    protected double a;
    protected double b;

    public Ellisse(double a, double b, double x, double y) {
        super(new Point(x, y));
        this.a = a;
        this.b = b;
    }

    public Ellisse() {
        super(new Point(0, 0));
        this.a = 0;
        this.b = 0;
    }

    public double aire() {
        return (Math.PI * this.a * this.b);
    }

    public double perimetre() {
        return (Math.PI * Math.sqrt(2 * (this.a * this.a + this.b * this.b)));
    }

    public void dessiner(PlancheADessin pad) {
        double x = this.origine.getX();
        double y = this.origine.getY();
        Dessinable tete = new Cercle(0.0, 80.0, 60.0);
        pad.ajouter(tete);
    }

}