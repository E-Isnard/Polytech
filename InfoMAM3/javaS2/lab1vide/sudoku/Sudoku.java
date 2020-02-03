package lab1vide.sudoku;

/** une classe pour résoudre les sudokus de façon naive */
 class Sudoku{
	// les valeurs par défaut d'une grille de Sudoku
	private static int TAILLE_DEFAUT = 9;
	private static int TAILLE_CARRE_DEFAUT = 3;

    private int taille; // la taille de la grille
    private int tailleCarre; // la taille d'un carre dans la grille
    private int[][] grille; // la grille de sudoku

    /** constructeur par défaut qui construit une grille de Sudoku
     *  standard
     */
     Sudoku() {
    	taille =  TAILLE_DEFAUT;
    	tailleCarre = TAILLE_CARRE_DEFAUT ;
    	grille = new int[taille][taille];
    }
    
    /** construire une grille de taille par défaut 
     * avec des valeurs initiales 
     * PRECOND : t est de taille 9
     */
     Sudoku(int[][] t) {
// A COMPLETER
    }


    /** CONSEQUENT : renvoie vrai si la  valeur v est possible pour la ligne l
     * @param l: le n° de ligne
     * @param v: la valeur
     * @return : true si la valeur v peut être mise dans la ligne l
     */
     private boolean lignePossible(int l,int v) {
// A COMPLETER
    	return false; 
    }

    /** CONSEQUENT : renvoie vrai si la  valeur v est possible pour la colonne c
     * @param c: le n° de colonne
     * @param v: la valeur
     * @return : true si la valeur v peut être mise dans la colonne l
     */
    private boolean colonnePossible(int c,int v) {
// A COMPLETER
	return false; 
    }

    /** CONSEQUENT : 
     * @param i: la coordonnée x du carré dont le coin (haut,gauche) est en (i,j)
     * @param j: la coordonnée y du carré dont le coin (haut,gauche) est en (i,j)
     * @return : true si la valeur v est possible pour ce carré
     * */
    private boolean carrePossible(int i,int j,int v) {
    	int coinI = (i/tailleCarre) * tailleCarre;
    	int coinJ = (j/tailleCarre) * tailleCarre;
    	for (int i1=0;i1<tailleCarre;i1++)
    		for (int j1=0;j1<tailleCarre;j1++)
    			if (grille[coinI+i1][coinJ+j1]==v) return false;
    	return true;
    }

    /** renvoie la ligne de la case à droite de la case (i,j) 
     * @param i : la coordonnée x de la case
     * @param j : la coordonnée y de la case
     * @return: le n° de ligne de la case à droite de (i,j)
    * */
    private int ligneSuivante(int i,int j){
    	if (j==taille-1) return i+1;
    	else return i;
    }

    /** renvoie la colonne de la case à droite de  (i,j)
     * @param i : la coordonnée x de la case
     * @param j : la coordonnée y de la case
     * @return: le n° de colonne qui est à droite de la case (i,j)
    *  */
    private int colonneSuivante(int i,int j){
    	if (j==taille-1) return 0;
    	else return j+1;
    }

    /** CONSEQUENT : remplit la grille si c'est possible
     * @return : renvoie false si c'est impossible */
     boolean remplir() {
    	return remplir(0,0);
    }

    
  /* CONSEQUENT : place une valeur en (i,j), renvoie false si c'est impossible */
  private boolean remplir(int i, int j) {
  // on a dépassé la dernière ligne donc la grille est remplie
	if (i==taille) return true;
  //  la case a une valeur initiale, on continue sur la prochaine case
// A COMPLETER
  // la case est vide
  // on essaie toutes les valeurs possibles 
	int k=0;
	while (k<=taille) {
// A COMPLETER
	}
	return false ;
  }

  ////////////////////////////////////////////////////////////////////////////////////////////////////////////

      
    /** Méthode privée pour dessiner une ligne 
     * @return: une ligne de longueur taille formée de tirets
     */
    private String separateurHorizontal() {
    	String result = "||";
    	// -- pour la place d'un nombre
    	for (int i=0;i<taille;i++) result += "--";
    	// -- pour la place d'un séparateur vertical
    	for (int i=0;i<taille/tailleCarre;i++) result += "--";
    	result += "-||\n";
    	return result;
    }
	
    /** le séparateur vertical est ||, les chiffres sont espacés d'un espace,
     * le séparateur horizontal est obtenu par la méthode separateurHorizontal()
     * voir exemple dans la classe de test
     * @return : renvoie une chaine qui représente la grille */
    public String toString() {
    	// A COMPLETER
    	return "";
    }


}
