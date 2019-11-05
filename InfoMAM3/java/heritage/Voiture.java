package heritage;

/**
 * Voiture
 */
public class Voiture extends Vehicule {

    private double cylindree, puissance;
    private int nbPortes, kilometrage;

    

    public void affiche() {

        super.affiche();
        System.out.println("{" + " cylindree='" + getCylindree() + "'" + ", puissance='" + getPuissance() + "'"
                + ", nbPortes='" + getNbPortes() + "'" + ", kilometrage='" + getKilometrage() + "'" + "}");

    }

    public double getCylindree() {
        return this.cylindree;
    }

    public double getPuissance() {
        return this.puissance;
    }

    public int getNbPortes() {
        return this.nbPortes;
    }

    public int getKilometrage() {
        return this.kilometrage;
    }

    public Voiture(String marque, int dateAchat, double prixAchat, double prixCourant, double cylindree,
            double puissance, int nbPortes, int kilometrage) {
        super(marque, dateAchat, prixAchat, prixCourant);
        this.cylindree = cylindree;
        this.puissance = puissance;
        this.nbPortes = nbPortes;
        this.kilometrage = kilometrage;
    }

    @Override
    public void calculePrix(int anneActuelle) {
        int pourcentage = anneActuelle-this.getDateAchat();
        pourcentage += kilometrage/10000;
        if(this.getMarque()=="Fiat" || this.getMarque()=="Renault"){
            pourcentage+=10;
        }
        if(this.getMarque()=="Ferrari" || this.getMarque()=="Porsche"){
            pourcentage-=20;
        }
        this.setPrixCourant(this.getPrixAchat()*(1-pourcentage/100));
    }

}