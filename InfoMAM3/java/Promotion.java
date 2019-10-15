/**
 * Promotion
 */
public class Promotion {
    private Teacher responsable;
    private String annee;
    private String parcours;

    public Promotion(Teacher t,String a,String p){
        this.responsable=t;
        this.annee=a;
        this.parcours=p;
    }

    /**
     * @return the annee
     */
    public String getAnnee() {
        return annee;
    }

    /**
     * @return the parcours
     */
    public String getParcours() {
        return parcours;
    }

    /**
     * @return the responsable
     */
    public Teacher getResponsable() {
        return responsable;
    }
    
}