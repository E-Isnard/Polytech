import java.util.ArrayList;
import java.util.Arrays;

/**
 * Abonne
 */
public class Abonne {

    private static ArrayList<Integer> identifiants = new ArrayList<Integer>();
    private String nom;
    private String typeAbo;
    private Integer id;

    Abonne(String nom,String typeAbo){

        assert !identifiants.contains(id);
        assert typeAbo == "Basic" || typeAbo=="VIP" || typeAbo=="Premium";

        
        


    }

}