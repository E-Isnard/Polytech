import java.util.regex.*;

/**
 * Utilisateur
 */
public class Utilisateur {

    private String nom;
    private String prenom;
    private String email;
    private String activite;
    private int annee;

    public Utilisateur(String nom,String prenom,String email,String activite,int annee){

        if(Pattern.matches("[a-zA-z0-0-9]+@[a-zA-z0-0-9]+\\.[a-zA-z0-0-9]+", email) && (activite=="visiteur" || activite=="permanent" || activite=="occasionnel")){
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.annee = annee;
        this.activite=activite;
        }
        else{
            //throw new Exception("Données incorrectes");
            System.err.println("Données incorrectes");
        }

    }

    /**
     * @return the activite
     */
    public String getActivite() {
        return activite;
    }

    /**
     * @param activite the activite to set
     */
    public void setActivite(String activite) {

        if(activite=="visiteur" || activite=="permanent" || activite=="occasionnel"){
        
        this.activite = activite;
        }
        else{
            System.err.println("Activité invalide");
        }
    }
}
