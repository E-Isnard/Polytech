package appart;
/**
 * Piece
 */
public class Piece {

    String nom;
    int largeur,longueur,hauteur;

    public int getSurface(){
        /*
        Retourne la surface a peindre(on ne peint pas le sol hein)
        */
        return 2*largeur*hauteur+2*longueur*hauteur+largeur*longueur;
    }

    public Piece(String nom, int largeur, int longueur, int hauteur) {
        this.nom = nom;
        this.largeur = largeur;
        this.longueur = longueur;
        this.hauteur = hauteur;
    }
}