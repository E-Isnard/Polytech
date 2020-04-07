package evenements;
import intervallestemps.*;
public class CompetitionSportive extends Evenement {
    private int prix;

    public CompetitionSportive(int d, int f, String nom, String lieu, int prix) throws DateException {

        super(d, f, nom, lieu);
        this.prix = prix;

    }
    
    public String toString() {
        return super.toString() + " | prix: " + prix;
    }
}