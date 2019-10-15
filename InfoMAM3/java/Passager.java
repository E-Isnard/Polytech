/**
 * Passager
 */
public class Passager {

    private String nom,prenom;
    private int age;

    Passager(String prenom,String nom,int age){
        this.nom=nom;
        this.prenom=prenom;
        this.age=age;
    }
    
    Passager(){
        this.nom="";
        this.prenom="";
        this.age=0;
        
        
    }

    public String toString(){
        return(prenom+" "+nom);
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }
}