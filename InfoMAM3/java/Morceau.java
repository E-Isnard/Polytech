/**
 * Morceau
 */
public class Morceau {

    private String nom, interprete, droits;
    private int duree, prix;

    Morceau(String nom, String interprete, String droits, int duree, int prix) {
        assert droits == "libre" || droits == "copyright";
        this.nom = nom;
        this.interprete = interprete;
        this.droits = droits;
        this.duree = duree;
        if (droits == "libre") {
            this.prix = 0;
        } else {
            this.prix = prix;
        }

    }

    Morceau() {
        this.nom = "Morceau";
        this.interprete = "interprete";
        this.droits = "libre";
        this.duree = 0;
        this.prix = 0;
    }

    Morceau(String nom, String interprete, int duree) {
        this.nom = nom;
        this.interprete = interprete;
        this.duree = duree;
        this.droits = "libre";
        this.prix = 0;
    }

    public String prix() {
        int euros = prix / 100;
        int centimes = prix % 100;
        return (euros + "€" + centimes + "c");
    }

    public String duree() {
        int minutes = duree / 60;
        int secondes = duree % 60;
        return (minutes + " mn " + secondes + " s");
    }

    /**
     * @return the prix
     */
    public int getPrix() {
        return prix;
    }

    /**
     * @return the duree
     */
    public int getDuree() {
        return duree;
    }

    public static void main(String[] args) {
        Morceau m = new Morceau("Echoes", "Camel", "copyright", 300, 900);
        System.err.println(m.prix());
    }

}