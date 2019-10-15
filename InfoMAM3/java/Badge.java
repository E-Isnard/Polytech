/**
 * Badge
 */
public class Badge {
    private boolean niveau0;
    private boolean niveau1;
    private boolean niveau2;
    private double nbImpression;
    private Utilisateur utilisateur;
    private int nbCopiesImprimmes=0;
    
    

    public Badge(Utilisateur u,boolean niveau1,boolean niveau2){
        this.utilisateur=u;
        if(u.getActivite()=="visiteur"){
            this.niveau0=true;
            this.niveau1=false;
            this.niveau2=false;
            this.nbImpression=0.0;
        }
        if(u.getActivite()=="occasionnel"){
            this.niveau0=true;
            this.niveau1=niveau1;
            this.niveau2=niveau2;
            this.nbImpression=100.0;

        }
        if(u.getActivite()=="permanent"){
            this.niveau0=true;
            this.niveau1=true;
            this.niveau2=true;
            this.nbImpression=Double.POSITIVE_INFINITY;
        }

    }

    public Badge(String nom,String prenom,String email,String activite,int annee,boolean niveau1,boolean niveau2){
        Utilisateur u= new Utilisateur(nom, prenom, email, activite, annee);
        this.utilisateur=u;
        if(u.getActivite()=="visiteur"){
            this.niveau0=true;
            this.niveau1=false;
            this.niveau2=false;
            this.nbImpression=0.0;
        }
        if(u.getActivite()=="occasionnel"){
            this.niveau0=true;
            this.niveau1=niveau1;
            this.niveau2=niveau2;
            this.nbImpression=100.0;

        }
        if(u.getActivite()=="permanent"){
            this.niveau0=true;
            this.niveau1=true;
            this.niveau2=true;
            this.nbImpression=Double.POSITIVE_INFINITY;
        }
        
    }

    public void imprimer(int nbCopies){
        if(this.nbImpression>=nbCopies && nbCopies>0){
            System.out.println("Impression...");
            this.nbImpression-=1;
            this.nbCopiesImprimmes+=1;
            

        }
        else{
            System.err.println("Vous n'avez plus/pas le droit d'imprimer");
        }
    }

    public boolean acceder(int niveau){
        if(niveau==0){
            return niveau0;
        }
        else if(niveau==1){
            return niveau1;
        }
        else if(niveau==2){
            return niveau2;
        }
        else{
            return false;
        }
    }

    public void changerDroits(int niveau,boolean droit){
        if(utilisateur.getActivite()=="occasionnel"){
            if(niveau==0){
                niveau0=droit;
            }
            else if(niveau==1){
                niveau1=droit;
            }
            else if(niveau==2){
                niveau2=droit;
            }
        }
    }

    public void changerTypeUtilisateur(String activite){
        utilisateur.setActivite(activite);
        if(utilisateur.getActivite()=="visiteur"){
            this.niveau0=true;
            this.niveau1=false;
            this.niveau2=false;
            this.nbImpression=0.0;
        }
        if(utilisateur.getActivite()=="occasionnel"){
            this.niveau0=true;
            this.niveau1=false;
            this.niveau2=false;
            this.nbImpression=100.0;

        }
        if(utilisateur.getActivite()=="permanent"){
            this.niveau0=true;
            this.niveau1=true;
            this.niveau2=true;
            this.nbImpression=Double.POSITIVE_INFINITY;
        }
        


    }

    public void ajouterCopies(int nbCopies){
        if(utilisateur.getActivite()=="occasionnel"){
            nbImpression+=nbCopies;
        }
    }

    public boolean droitImprimer(){
        return(nbImpression>0);
    }

    /**
     * @return the nbImpression
     */
    public double getNbImpression() {
        return nbImpression;
    }

    /**
     * @return the nbCopiesImprimmes
     */
    public int getNbCopiesImprimmes() {
        return nbCopiesImprimmes;
    }
    
}