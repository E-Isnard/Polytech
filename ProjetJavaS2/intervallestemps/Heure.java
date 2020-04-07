package intervallestemps;

/**
 * Date
 */
public class Heure extends Intervalle<Double> {

    public Heure(Double d, Double f) throws HeureException {

        super(d, f);
        if (d < 0 || d > 23 || f < 0 || f > 23) {
            throw new HeureException();
        }

    }

    public Heure(Double h) throws HeureException {
        super(h, h);
        if (h < 0 || h > 23) {
            throw new HeureException();
        }
    }
    
    public String toString() {

        int heureD = (int) Math.floor(a);
        int heureF = (int) Math.floor(b);

        int minuteD = (int) Math.round((a - heureD)*60);
        int minuteF = (int) Math.round((b - heureF) * 60);
        
        if (a.compareTo(b) != 0) {
            return heureD + "h:" + minuteD + " a " + heureF + ":" + minuteF;
        }

        return heureD + "h :" + minuteD;

        

    }
}
