public class Equ2degre {

    private Complexe r1;
    private Complexe r2;

    public Equ2degre(double a, double b, double c) {

        this.résoudre(a, b, c);

    }

    private void résoudre(double a, double b, double c) {

        if (a == 0) {
            System.err.println("Erreur: a doit être non nul");
        }

        double delta = b * b - 4 * a * c;
        if (delta >= 0) {
            this.r1 = new Complexe(((-b + Math.sqrt(delta)) / 2 * a), 0);
            this.r2 = new Complexe(((-b - Math.sqrt(delta)) / 2 * a), 0);
        } else if (delta < 0) {
            this.r1 = new Complexe(-b / 2 * a, Math.sqrt(-delta) / 2 * a);
            this.r2 = this.r1.conj();
        }

    }

    public Complexe premiereRacine() {
        return (this.r1);
    }

    public Complexe deuxiemeRacine() {
        return (this.r2);
    }

    public String toString() {
        return ("(X-"+this.r1+")(X-"+this.r2+")");
    }

}