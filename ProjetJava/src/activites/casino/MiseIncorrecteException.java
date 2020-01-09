package src.activites.casino;
/**
 * MiseIncorrecteException: Elle est renvoyee si une mise est incorrecte,c'est à dire si le paris demandé n'existe pas
 * @author Enzo Isnard et Théo Picke
 */
public class MiseIncorrecteException extends Exception {

    /**
     * Contructeur de MiseIncorrecteException
     * @param msg
     */
    MiseIncorrecteException(String msg) {

        super(msg);

    }

    /**
     * methode toString de MiseIncorrecteException
     * @return message d'erreurs
     */
    public String toString() {
        return "Mise incorrecte: Vous devez parier sur rouge,noir,pair,impair,manque,passe ou un nombre";
    }



    
    
}