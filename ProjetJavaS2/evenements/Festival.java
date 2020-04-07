package evenements;
import intervallestemps.*;
import java.util.HashMap;


/**
 * Festival
 */
public class Festival extends Evenement {

    private int prix;

    private HashMap<String, Heure> planing;

    public Festival(int d, int f, String nom, String lieu, int prix, HashMap<String, Heure> planing)
            throws DateException {

        super(d, f, nom, lieu);

        this.prix = prix;

        this.planing = planing;

    }
    
    public String toString() {
        

        String planingStr = "\n\n";

        for (String artiste : planing.keySet()) {
            
            planingStr += artiste + ": " + planing.get(artiste)+"\n";

        }

        return super.toString()+" | prix: "+prix+"E"+planingStr;


    }


}
