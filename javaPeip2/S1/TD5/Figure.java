import PaD.*;

public abstract class Figure {
    protected Point origine = new Point();

    public Figure(Point origine) {
        this.origine = origine;
    }

    public abstract double perimetre();

    public abstract double aire();

    //public abstract void dessiner(PlancheADessin pad);
}