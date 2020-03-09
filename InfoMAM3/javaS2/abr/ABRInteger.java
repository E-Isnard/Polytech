package abr;

/** 
 * Une classe pour les arbres binaires de recherche de int
 * un arbre binaire de recherche est défini par le schéma d'induction :
 *     l'arbre vide est un arbre binaire de recherche
 *     si x est un int, si gauche et droite sont des arbres binaires de recherche
 *        et que x est supérieur à la racine de gauche et x est inférieur à la racine de droite
 *        alors <x, gauche, droite> est un arbre binaire de recherche.
 * @author helen
 */

class ABRInteger {
    private int racine; //l'élément
    private ABRInteger gauche;  // le sous-arbre gauche
    private ABRInteger droite;  // le sous-arbre droit

    /** constructeur par défaut, ABR vide
     */
    ABRInteger() {
    	this(0,null,null);
    }

    /** construit un arbre de racine o, de sous-arbre gauche
     * et  droit nuls
     */
    private ABRInteger(int o) {
    	this(o,null,null);
    }

   /** construit un arbre de racine o, de sous-arbre gauche g
     * et de sous-arbre droit d
     */
    private ABRInteger(int o, ABRInteger g, ABRInteger d) {
        racine = o;
        gauche = g;
        droite = d;
    }

    boolean vide() {
        return racine==0 && gauche==null && droite==null;
    }

    // pour afficher l'arbre
    public String toString() {
        if (!vide()) return toStringAux();
        return "Arbre vide";
    }

    private String toStringAux() {
        String s = "\nVALEUR : " + racine + " ";
        if (gauche!=null)
            s = s + "GAUCHE : " + gauche.racine + " ";
        if (droite!=null)
            s= s + "DROITE : " + droite.racine + " ";
        if (gauche!=null) s= s + gauche.toStringAux();
        if (droite!=null) s= s + droite.toStringAux();
        return s;
    }

///////////////////////////////////////////////////////////////
// méthodes de base à compléter
///////////////////////////////////////////////////////////////

    /* Consequent : this contient o et est un arbre de recherche
     * complexité : theta(log(n)) dans le meilleur des cas ie si l'arbre est équilibré
     *              theta(n) dans le pire des cas ie si l'arbre est dégénéré
     * NB: les clefs sont uniques dans l'arbre, donc si il existe un objet
     * ayant la même clef que o dans l'arbre, alors o n'est pas ajouté
      */
    void ajouter(int o) {
        if(o==racine){
            return;
        }
        else if(o<racine){
            if(gauche!=null){
                gauche.ajouter(o);
            }
            gauche = new ABRInteger(o);

        }
        else if(o>racine){

            if(droite!=null){
                droite.ajouter(o);
            }
            droite = new ABRInteger(o);

        }
    }

    /* Consequent : renvoie vrai si o est dans l'arbre, renvoie false sinon
     * complexité : theta(1) dans le meilleur des cas
     *              theta(n) dans le pire des cas
     */
     boolean existe(int o) {
       if(racine==o){
           return true;
       }
       else if(o<racine){
           if(gauche==null){
               return false;
           }
           return gauche.existe(o);
       }
       else{
        if(droite==null){
            return false;
        }
        return droite.existe(o);

       }

    }

    // affiche les éléments de l'arbre par ordre de clef croissante
    // complexité : theta(n)
    void afficheParOrdre() {
        if(gauche!=null){
            System.out.println(gauche.racine);
        }
        System.out.println(racine);
        if(droite!=null){
            System.out.println(droite.racine);
        }
    }

    // renvoie le nombre d'éléments de l'arbre
    int size() {
        if(this.equals(new ABRInteger())){
            return 0;
        }
        return 1+gauche.size()+droite.size();
    }

///////////////////////////////////////////////////////////////
// méthodes pour la suppression à compléter
///////////////////////////////////////////////////////////////


    /**
     * conséquent : l'arbre ne contient pas d'objet de clef c
     * PRECOND : l'arbre est non vide
     * complexité : O(log_2(n))  si l'arbre est bien équilibré
     * PRINCIPE : on remplace l'élément par le maximum de son sous-arbre gauche
     *      - utilise supprimeMax
     *      - traite les cas limites : quand les 2 fils sont vides ou quand
     *            un des deux fils est vide
     */
    void delete(int o) {
    }

    /**
     * supprime le max de l'arbre et le renvoie
     * conséquent : le max des éléments de l'arbre a été supprimé
     * PRECOND: l'arbre n'est pas vide
     * complexité : theta(log2(n))
     * @return : le max de l'arbre
     */
    int supprimeMax() {
        return 0;
    }

    public static void main(String[] args) {
        ABRInteger a1 = new ABRInteger(1);
        ABRInteger a2 = new ABRInteger(3);
        ABRInteger a = new ABRInteger(2, a1, a2);
        a.ajouter(4);
        System.out.println(a.existe(212));
        a.afficheParOrdre();
        System.out.println(a);

    }

}



