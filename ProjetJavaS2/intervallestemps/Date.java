package intervallestemps;

/**
 * Date
 */
public class Date extends Intervalle<Integer> {

    public Date(Integer d, Integer f) throws DateException {

        super(d, f);
        if (d < 1 || d > 365 || f < 1 || f > 365) {
            throw new DateException();
        }

    }

    public Date(Integer d) throws DateException {

        super(d, d);

        if (d < 1 || d > 365) {
            throw new DateException();
        }

    }

    public String toString() {
        
        if (a.compareTo(b) != 0) {
            return super.toString();
        }

        return a.toString();

    }

    
}
