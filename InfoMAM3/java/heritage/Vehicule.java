package heritage;

import java.util.Set;

/**
 * Vehicule
 */
public class Vehicule {

    private String marque;
    private double prixAchat, prixCourant;
    private int dateAchat;

    public Vehicule(String marque, int dateAchat, double prixAchat, double prixCourant) {
        this.marque = marque;
        this.dateAchat = dateAchat;
        this.prixAchat = prixAchat;
        this.prixCourant = prixCourant;
    }

    public String getMarque() {
        return this.marque;
    }

    public int getDateAchat() {
        return this.dateAchat;
    }

    public double getPrixAchat() {
        return this.prixAchat;
    }

    public double getPrixCourant() {
        return this.prixCourant;
    }

    public void affiche() {
        System.out.println("{" + " marque='" + getMarque() + "'" + ", dateAchat='" + getDateAchat() + "'"
                + ", prixAchat='" + getPrixAchat() + "'" + ", prixCourant='" + getPrixCourant() + "'" + "}");
    }

    public void calculePrix(int anneActuelle){
        int nbAnnes = anneActuelle-dateAchat;
        prixCourant = prixAchat*(1-nbAnnes/100);
    }

    /**
     * @param prixAchat the prixAchat to set
     */
    public void setPrixAchat(double prixAchat) {
        this.prixAchat = prixAchat;
    }

    /**
     * @param prixCourant the prixCourant to set
     */
    public void setPrixCourant(double prixCourant) {
        this.prixCourant = prixCourant;
    }

}
