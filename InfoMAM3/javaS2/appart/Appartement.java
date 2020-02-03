package appart;
/**
 * Appartement
 */
public class Appartement {

    Piece[] pieces;
    String description;

    public Appartement(Piece[] pieces, String description) {
        this.pieces = pieces;
        this.description = description;
    }

    public Double nbPots(Peinture peinture) {
        
        return Math.ceil(getSurface()/peinture.getPvCouvrant());

    }

    public Double getSurface() {
        Double s = 0.0;
        for (Piece piece : pieces) {
            s += piece.getSurface();
        }
        return s;
    }

    public static void main(String[] args) {
        Piece p1 = new Piece("Salon", 10, 10, 10);
        Piece p2 = new Piece("Cuisine", 5, 5, 5);
        Piece p3 = new Piece("Salle de bain", 5, 5, 5);

        Piece[] pieces = { p1, p2, p3 };

        Appartement a = new Appartement(pieces, "Joli appartement");

        Peinture peinture = new Peinture(120.0, 13.0);

        System.out.println(a.getSurface() + " m2");
        System.out.println(a.nbPots(peinture) + " pots");
    }

}