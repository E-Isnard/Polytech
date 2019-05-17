
public class Exec {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws ArbreVideException {
        ArbreBinaire<String> b = new ArbreChaine<String>("x", new ArbreChaine<String>("y"), ArbreChaine.emptyTree);

        ArbreBinaire<String> a = new ArbreChaine<String>("a", new ArbreChaine<String>("b"), b);

        ArbreBinaire<String> c = new ArbreChaine<String>("i", a, new ArbreChaine<String>("j"));

        ArbreBinaire<String> d = new ArbreChaine<String>("i", new ArbreChaine<String>("j"), a);


        

        if (!b.isEmpty()) {
            // System.out.println(b.getValue());
            ArbreChaine.affichage(c,"");
            System.out.println(ArbreChaine.mirror(c,d));
            // System.out.println(ArbreChaine.hauteur(c));
        }

    }

    
}