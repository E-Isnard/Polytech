package intervallestemps;

/**
 * DateException
 */
public class DateException extends Exception {

    private static final long serialVersionUID = 4633311933310085213L;

    public DateException() {
        super();
    }

    public String toString() {
        return "Erreur: date incorrecte";
    }
    
}