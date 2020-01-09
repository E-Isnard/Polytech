package src.activites.casino;
/**
 * Classe permettant de faire une mise
 */
public class Mise {

    private int montant; //Le monrant qu'on veut parier
    private String paris; //Type de paris qu'on veut faire

    /**
     * Contructeur de Mise
     * Il vérifie que le paris soit correct et renvoie une exception si elle ne l'est pas
     * @param montant
     * @param paris
     * @throws MiseIncorrecteException
     */
    public Mise(int montant, String paris) throws MiseIncorrecteException {

        String regex = "^rouge|noir|pair|impair|manque|passe|[1-2]?\\d|3[0-6]$";
        //regex qui teste si le paris est correct.
        if (paris.matches(regex)) {
            this.paris = paris;
        } else {
            throw new MiseIncorrecteException(
                    "Mise incorrecte: Vous devez parier sur rouge,noir,pair,impair,manque,passe ou un nombre");
        }

        this.montant = montant;

    }
    /**
     * gettter de montant
     * 
     * @return montant
     */
    public int getMontant() {
        return montant;
    }
    /**
     * getter de Paris
     * @return Paris
     */
    public String getParis() {
        return paris;
    }
    /**
     * setter de montant
     * @param montant
     */
    public void setMontant(int montant) {
        this.montant = montant;
    }


}