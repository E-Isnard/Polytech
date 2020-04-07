package intervallestemps;

/**
 * Intervalle
 */
public class Intervalle<C extends Comparable<C>> {

    protected C a;
    protected C b;

    public Intervalle(C a, C b) {

        if (b.compareTo(a) < 0) {
            this.a = b;
            this.b = a;
        } else {
            this.a = a;
            this.b = b;
        }

    }

    public C getA() {
        return a;
    }

    public C getB() {
        return b;
    }

    public String toString() {

        return "[" + a + "," + b + "]";
    }
}