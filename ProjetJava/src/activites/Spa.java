package src.activites;
/**
 * Spa: Classe permettant de commander des massages
 * 
 * @author Theo Picke et Enzo Isnard
 */
public class Spa {

    /*
     * Tableau decrivant le nombres de massages pris.Le premier element
     * correspondant au massage relaxant, le deuxieme au massage aux pierres chaudes
     * et le troisème au massage a l'elixir de bougie
     */
    private int[] nbMassages;
    
    /**
     * Contructeur qui initialise nbMassages avec un tableau remplie de 0
     */
    public Spa() {

        nbMassages = new int[] { 0, 0, 0 };
    }
    
    /**
     * Fonction toString de spa qui renvoie une description des massages
     * @return la description des massages
     */
    public String toString() {

        

        String out = "\n1:Massage Relaxant: 50 euros\n";
        out += "A la fois relaxant et profond, il vous plongera dans une totale détente musculaire, physique et psychique.\n\n";
        out += "2:Massage aux pierres chauges: 70 euros\n";
        out += "Ce massage, par la douce chaleur des pierres volcaniques, harmonise les flux d’energie et vous transporte dans une profonde relaxation.\n\n";
        out += "3:Massage a l'elixir de bougie: 80 euros\n";
        out += "Laissez-vous envelopper par la douce chaleur d'une bougie devenue huile de massage qui libere un veritable elixir aromatique hydratant pour un massage d'une infinie douceur sur l'ensemble du corps.\n";
        return out;

    }

    /**
     * Fonction qui ajoute 1 au massage sélectioné
     * @param i numero du massage selectioné
     */
    public void addMassage(int i) {
        
        if (i <= 3 && i >= 1) {
            nbMassages[i - 1]++;//On décale l'indice
        }
        else{
            System.out.println("Le massage "+i+" n'existe pas !");
        }
    }
    /**
     * Methode qui affiche la facture du Spa
     * 
     */
    public void afficherFacture() {
        /*
         * Fonction qui renvoie un resume de ce qu'on a pris au Spa
         */
        String out = "** Spa \"Yuma Massage\" ** \n\n";

        int prix1 = nbMassages[0] * 50;
        int prix2 = nbMassages[1] * 70;
        int prix3 = nbMassages[2] * 80;

        out += "Nombre Massages relaxants : " + nbMassages[0] + "x50 euros = " + (prix1) + " euros\n";
        out += "Nombre Massages aux pierres chaudes : " + nbMassages[1] + "x70 euros = " + (prix2) + " euros\n";
        out += "Nombre Massages a l'elixir de bougie : " + nbMassages[2] + "x80 euros = " + (prix3) + " euros\n\n";
        out += "Total a payer : " + (prix1 + prix2 + prix3) + " euros";

        System.out.println(out);
    }

    /**
     * Fonction qui renvoie le prix total des massages
     * @return le prix total des massages
     */
    public int getPrixSpa(){
        
        return nbMassages[0]*50+nbMassages[1]*70+nbMassages[2]*80;
        
    }

    

}