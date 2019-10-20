/**
 * CD
 */
public class CD {
    private int n;
    private String nomCd;
    private String interprete;
    private Morceau[] morceaux;

    CD(String nomCD, String interprete, Morceau[] morceaux) {
        this.n = morceaux.length;
        this.nomCd = nomCD;
        this.interprete = interprete;
        for (int i = 0; i < n; i++) {
            this.morceaux[i] = morceaux[i];
        }

    }

    CD(int n) {
        this.n = n;
        this.nomCd = "";
        this.interprete = "";
        this.morceaux = new Morceau[n];

    }

    public void ajouterMorceau(Morceau m) {
        int i = 0;
        while (morceaux[i] != null && i < n) {
            i++;
        }
        if (i != n) {
            morceaux[i] = m;
        }

    }

    public void supprimerMorceau(int i) {
        assert i >= 0 && i < n;
        morceaux[i] = null;
    }

    public String prix() {
        int s = 0;
        for (Morceau m : morceaux) {
            s += m.getPrix();
        }
        int euros = s / 100;
        int c = s % 100;
        return (euros + "€" + c + "c");

    }

    public String duree() {

        int s = 0;
        for (Morceau m : morceaux) {
            s += m.getDuree();
        }
        int euros = s / 60;
        int c = s % 60;
        return (euros + "mn" + c + "s");

    }

    public static void main(String[] args) {
        Morceau m1 = new Morceau("Echoes", "Camel", "copyright", 300, 900);
        Morceau m2 = new Morceau("ComfortablyNumb", "Pink", "copyright", 400, 800);
        Morceau[] morceaux = { m1, m2 };
        CD c = new CD("Bestof", "Oui", morceaux);

    }

}