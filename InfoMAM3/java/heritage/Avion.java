package heritage;

/**
 * Avion
 */
public class Avion extends Vehicule {

    public int nbHeuresVol;
    public String moteur;

    public Avion(String marque, int dateAchat, double prixAchat, double prixCourant, int nbHeuresVol,
            String moteur) {
        super(marque, dateAchat, prixAchat, prixCourant);
        this.nbHeuresVol = nbHeuresVol;
        this.moteur = moteur;
    }

    @Override
    public void affiche() {
        
        super.affiche();
        System.out.println("{nbHeuresVol='"+this.getNbHeuresVol()+"',moteur='"+this.getMoteur()+"'");
        
        

    }

    public int getNbHeuresVol() {
        return nbHeuresVol;
    }

    public void setNbHeuresVol(int nbHeuresVol) {
        this.nbHeuresVol = nbHeuresVol;
    }

    public String getMoteur() {
        return moteur;
    }

    public void setMoteur(String moteur) {
        this.moteur = moteur;
    }

    @Override
    public void calculePrix(int anneActuelle) {
        int pourcentage = 0;
        if(moteur=="helice"){
            pourcentage+=10*nbHeuresVol/100;
        }
        else{
            pourcentage+=10*nbHeuresVol/1000;
        }
        this.setPrixCourant(Math.max(0, this.getPrixCourant()*(1-pourcentage/100)));
    }

    

    

    
    


    
}