package evenements;

import intervallestemps.*;

/**
 * Evenement
 */
public class Evenement {

    protected Date date;
    protected String nom, lieu;
    
    public Evenement(int d, int f, String nom, String lieu) throws DateException {

        

        this.date = new Date(d, f);
        this.lieu = lieu;
        this.nom = nom;
    }

    
    public String toString() {


        return "Nom: " + nom + " | Lieu: " + lieu + " | debut: " + date.getA() + " | fin: " + date.getB();
        
        
    }
    
    

    
}