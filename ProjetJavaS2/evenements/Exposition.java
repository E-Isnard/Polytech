package evenements;
import intervallestemps.*;
public class Exposition extends Evenement {

    private String[] tableaux;
    private int prix;

    public Exposition(int d, int f, String nom, String lieu, String[] tableaux, int prix) throws DateException {

        super(d, f, nom, lieu);
        this.prix = prix;
        this.tableaux = tableaux;

    }
    
    public String toString() {

        String tableauxStr = String.join(",", tableaux);

        tableauxStr = "{" + tableauxStr + "}";

        return super.toString() + " | prix: " + prix + "| tableaux: " + tableauxStr;
        

    }


}