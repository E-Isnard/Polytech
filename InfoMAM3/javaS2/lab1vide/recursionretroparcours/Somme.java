package lab1vide.recursionretroparcours;

/**
 * On teste récursivement si on peut faire la somme i avec certaines des
 * valeurs de T, un tableau d'entiers.
 * © 2002 Marc Gaëtano
 */
 class Somme {

    private int[] T; // le tableau d'entiers positifs
    private int N;   // la somme des éléments de T
    
    /**
     * Construit un objet Somme.
     * Précondition : table[i] >= 0 pour tout i dans [0..T.length-1].
     * Complexité : O(n) où n = T.length.
     */
     Somme(int[] table) {
        T = table;
        N = 0;
        
        for ( int i = 0; i < T.length; i++ ) {
            N = N + T[i];
        }
    }

    /**
     * Teste si on peut faire la somme i avec un sous-ensembles d'entiers
     * appartenant à T.
     * Complexité : O(2^n) où n = T.length.
     */
     boolean possible(int i) {
        if ( i < 0 || i > N ) {
            return false;
        }
        return possible(i,0);
    }

    /**
     * Teste si on peut faire la somme i avec un sous-ensemble d'entiers
     * appartenant à T[0..k-1].
     * Préconditions : 0 <= i <= N et 0 <= k < T.length.
     * Complexité : O(2^k).
     */
    private boolean possible(int i, int k) {
        // cas d'arrêt 1 : on a déjà réussi à faire la somme, donc c'est possible
        if ( i == 0 ) {
            return true;
        }
        // cas d'arrêt 2 : on a traité tous les éléments
        if ( k == T.length ) {
            return false;
        }
        // cas général -> appel récursif à possible(...,k+1)
        // sous-cas 1 : on peut mettre T[k] dans la somme
        //              la somme est possible si elle est possible en mettant l'élément ou en ne le mettant pas
        if ( T[k] <= i ) {
            return possible(i,k+1) || possible(i - T[k],k+1);
        }
        // sous-cas 2 : l'élément est trop grand pour être mis dans la somme
        //              on regarde s'il est possible de faire la somme sans cet élément
        return possible(i,k+1);
    }
    
    
}
