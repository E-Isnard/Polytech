package evenements;
import intervallestemps.*;
/**
 * VisiteGuidee
 */
public class VisiteGuidee extends Evenement {

    private int nbPersonnes;
    private String[] langues;

    public VisiteGuidee(int d, int f, String nom, String lieu, int nbPersonnes, String[] langues) throws DateException {

        super(d, f, nom, lieu);

        this.nbPersonnes = 0;
        if (nbPersonnes > 0) {
            this.nbPersonnes = nbPersonnes;
        }

        this.langues = langues;

    }
    
    
    public String toString() {
        

        String languesStr = String.join(",", langues);
        
        languesStr = "{" + languesStr + "}";
        

        return super.toString()+" | nbPersonnes: "+nbPersonnes+" | langues: "+languesStr;
    }
    
}