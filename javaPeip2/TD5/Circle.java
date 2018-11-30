import PaD.*;

public class Circle extends Ellisse {
    public Circle(double r, double x, double y) {
        super(r, r, x, y);
    }

    public void dessiner(PlancheADessin pad) {
        double x = this.origine.getX();
        double y = this.origine.getY();
        Dessinable Circle = new Cercle(this.a);
        pad.ajouter(Circle);

    }
}