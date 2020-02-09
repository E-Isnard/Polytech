package lab1vide.recursionsimple;

/**
 * classe pour écrire quelques fonctions récursives simples sur les tableaux
 * d'entier pour se chauffer
 * 
 * @author helen
 */
class TableauxEntiers {
    // les entiers
    private int[] lesElements;

    // constructeur
    TableauxEntiers(int[] t) {
        this.lesElements = t;
    }

    //////////////////////////////
    // exemple du max
    /**
     * @return le maximum du tableau
     */
    int maximum() {
        return maxDicho(0, lesElements.length - 1);
    }

    /**
     * @param g : indice de gauche de la zone traitée
     * @param d : indice de droite de la zone traitée
     * @return : renvoie le maximum du tableau dans la zone
     */
    private int maxDicho(int g, int d) {
        if (g == d)
            return lesElements[g];
        int milieu = (g + d) / 2;
        return Math.max(maxDicho(g, milieu), maxDicho(milieu + 1, d));
    }

    /////////////////////////////////////////
    // partie à compléter
    /**
     * TODO : à compléter
     * 
     * @return true si lesElements est trié par ordre croissant
     */
    boolean estTrie() {
        int elementPrecedent = lesElements[0];
        for (int i = 1; i < lesElements.length; i++) {
            if (elementPrecedent > lesElements[i]) {
                return false;
            }
            elementPrecedent = lesElements[i];
        }
        return true;
    }

    /**
     * recherche dichotomique TODO : à compléter, appelle rechercheDicho, utilise
     * estTrie()
     * 
     * @param x : l'élement entier cherché
     * @return* - -2 si le tableau n'est pas trié - -1 si le tableau est trié mais
     *          que x n'est pas dans le tableau - si le tableau est trié, renvoie
     *          l'indice de x si x est dans le tableau
     */
    int rechercheVite(int x) {
        if (!this.estTrie()) {
            return -2;
        }
        return rechercheDicho(x, 0, lesElements.length - 1);

    }

    /**
     * PRECONDITION : lesElements est trié par ordre croissant complexité : O(log n)
     * TODO : à compléter, méthode récursive qui cherche x par dichotomie la zone de
     * recherche est comprise entre gauche et droite inclus
     */
    private int rechercheDicho(int x, int gauche, int droite) {
        if (gauche == droite) {
            if (lesElements[gauche] == x) {
                return gauche;
            }
            return -1;
        }
        int milieu = (gauche + droite) / 2;
        if (x == lesElements[milieu]) {
            return milieu;
        } else if (x < lesElements[milieu]) {
            return rechercheDicho(x, gauche, milieu - 1);
        }

        return rechercheDicho(x, milieu + 1, droite);
    }

    /**
     *
     * @return: true si le tableau lesElements est un palindrome c'est à dire que
     *          les éléments sont les mêmes en partant de la gauche ou de la droite
     *          Exemples : {1,4,8,4,1} est un palindrome {1,4,8,8,4,1} est un
     *          palindrome {1,4,8,7,4,1} n'est pas un palindrome
     *
     */
    boolean estPalindrome() {
        return estPalindrome(0, lesElements.length - 1);
    }

    /**
     *
     * @param gauche: indice gauche de la zone de tableau pas encore examinée
     * @param droite: indice droit de la zone de tableau pas encore examinée
     * @return: true si la zone comprise entre gauche et droite de lesElements est
     *          un palindrome
     */
    private boolean estPalindrome(int gauche, int droite) {
        if ((lesElements[gauche] == lesElements[droite] && droite - gauche == 1) || (lesElements[gauche] == lesElements[droite] && droite - gauche == 2)) {
            return true;
        }
        if (lesElements[gauche] != lesElements[droite]) {
            return false;
        }
        return estPalindrome(gauche + 1, droite - 1);

    }

    

}
