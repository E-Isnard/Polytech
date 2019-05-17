
public class ArbreChaine<T extends Comparable<T>> implements ArbreBinaire<T> {

    public static final ArbreBinaire emptyTree = new ArbreChaine();

    private T value;

    private ArbreBinaire<T> leftTree;

    private ArbreBinaire<T> rightTree;

    private ArbreChaine() {
        // Initialisation des attributs à null.
        this.value = null;
        this.leftTree = null;
        this.rightTree = null;
    }

    @SuppressWarnings("unchecked")
    public ArbreChaine(T value) {
        // Initialisation de la valeur.
        this.value = value;

        // Initialisation des arbres binaire à vide.
        this.leftTree = emptyTree;
        this.rightTree = emptyTree;
    }

    @SuppressWarnings("unchecked")
    public ArbreChaine(T value, ArbreBinaire leftTree, ArbreBinaire rightTree) {
        // Initialisation de la valeur.
        this.value = value;

        // Si l'arbre gauche est null.
        if (leftTree == null) {
            // Initialisation de l'arbre gauche à vide.
            this.leftTree = emptyTree;
        } else {
            // Initialisation de l'arbre gauche.
            this.leftTree = leftTree;
        }

        // Si l'arbre droit est null.
        if (rightTree == null) {
            // Initialisation de l'arbre droit à vide.
            this.rightTree = emptyTree;
        } else {
            // Initialisation de l'arbre droit.
            this.rightTree = rightTree;
        }
    }

    public T getValue() throws ArbreVideException {
        
        return this.value;

    }

    public ArbreBinaire<T> getLeft()  {
        
        return this.leftTree;
    }

    public ArbreBinaire<T> getRight() {
        
        return this.rightTree;
    }

    public boolean isEmpty() {
        return (this == emptyTree);
    }

    public static void affichage(ArbreBinaire a,String offset) throws ArbreVideException {
        if (a == emptyTree) {
            System.out.print("");
        } else {
            if (a.getLeft() != emptyTree) {
                affichage(a.getRight(),offset+"--");
            }
            System.out.println(offset+a.getValue());
            if (a.getRight() != emptyTree) {
                affichage(a.getLeft(),offset+"--");
            }
        }
    }

    public static int hauteur(ArbreBinaire a) throws ArbreVideException {
        if (a == emptyTree)
            return 0;
        else
            return (1 + Math.max(hauteur(a.getLeft()), hauteur(a.getRight())));
    }

    public static boolean mirror(ArbreBinaire a,ArbreBinaire b) throws ArbreVideException{
        if(a.isEmpty() && b.isEmpty()) return true;
        if(a.isEmpty() || b.isEmpty()) return false;
        if(a.getValue() != b.getValue()) return false;
        return mirror(a.getLeft(),b.getLeft()) && mirror(a.getRight(),b.getRight());

    }

}