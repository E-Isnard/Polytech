package evenements;
import intervallestemps.*;
public class EscapeGame extends Evenement {

    private int difficulte, prix;

    private Duree duree;

    public EscapeGame(int d, int f, String nom, String lieu, int difficulte, int prix, Duree duree)
            throws DateException {

        super(d, f, nom, lieu);

        if (difficulte > 10) {
            this.difficulte = 10;
        } else if (difficulte < 0) {
            this.difficulte = 0;
        } else {
            this.difficulte = difficulte;
        }

        this.prix = 0;
        if (prix > 0) {
            this.prix = prix;

        }

        this.duree = duree;

    }
    
    public String toString() {
        
        return super.toString() + " | difficulte: " + difficulte + " | prix: " + prix + " | duree: " + duree;

    }
    

}