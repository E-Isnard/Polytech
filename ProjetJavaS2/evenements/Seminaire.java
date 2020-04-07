package evenements;
import intervallestemps.*;
public class Seminaire extends Evenement {

    private String nomIntervenant;
    private int prix, nbPersonnes;

    public Seminaire(int d, int f, String nom, String lieu, String nomIntervenant, int nbPersonnes,
            int prix) throws DateException {

        super(d, f, nom, lieu);

        this.nbPersonnes = 0;
        if (nbPersonnes > 0) {
            this.nbPersonnes = nbPersonnes;
        }

        this.prix = 0;
        if (prix > 0) {
            this.prix = prix;
        }

        
        this.nomIntervenant = nomIntervenant;

    }
    
    public String toString() {
        
        return super.toString() + " | nomIntervenant: "+nomIntervenant+" | nbPersonnes: "+nbPersonnes+" | prix: "+prix;

    }

}
