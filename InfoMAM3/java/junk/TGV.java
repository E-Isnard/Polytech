/**
 * TGV
 */
public class TGV {

    private int numero;
    private String[] gares;
    private int[] horaires;

    TGV(int numero, String[] gares, int[] horaires) {
        assert gares.length == horaires.length;
        this.numero = numero;
        this.gares = gares;
        this.horaires = horaires;
    }

    public int heurePassage(String gare) {
        for (int i = 0; i < gares.length; i++) {
            if (gares[i] == gare) {
                return horaires[i];
            }
        }
        return -1;
    }

    public static int findIndex(String[] array, String element) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == element) {
                return i;
            }
        }
        return -1;
    }

    public int duree(String gare1, String gare2) {
        int j = findIndex(gares, gare1);
        int k = findIndex(gares, gare2);
        if (j == -1 || k == -1) {
            return -1;
        }

        return horaires[Math.max(j, k)] - horaires[Math.min(j, k)];

    }

    public String toString() {
        return numero + " : " + gares[0] + " " + horaires[0] + " " + gares[gares.length - 1] + " "
                + horaires[horaires.length - 1];

    }

    /**
     * @return the gares
     */
    public String[] getGares() {
        return gares;
    }

    public int nbIntersect(TGV t) {
        String[] tgares = t.getGares();
        String[] garesParcourir = tgares.length < gares.length ? tgares : gares;
        String[] garesCompare = tgares.length >= gares.length ? tgares : gares;
        int nbIntersect = 0;
        for (int i = 0; i < garesParcourir.length; i++) {
            if (findIndex(garesCompare, garesParcourir[i]) != -1) {
                nbIntersect++;
            }

        }
        return nbIntersect;

    }

    public String intersection(TGV t) {

        String[] tgares = t.getGares();
        String[] garesParcourir = tgares.length < gares.length ? tgares : gares;
        String[] garesCompare = tgares.length >= gares.length ? tgares : gares;
        int nbIntersect = 0;
        boolean estPremier = true;
        String intersection = "";
        for (int i = 0; i < garesParcourir.length; i++) {
            if (findIndex(garesCompare, garesParcourir[i]) != -1) {
                nbIntersect++;
                if (estPremier) {
                    intersection = garesParcourir[i];
                    estPremier = false;
                }
            }

        }
        return nbIntersect == 1 ? intersection : null;

    }

}