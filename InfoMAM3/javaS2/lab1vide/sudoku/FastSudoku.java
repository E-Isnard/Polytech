package lab1vide.sudoku;

/** une classe pour résoudre les sudokus 
* en choisissant la case la plus contrainte à chaque étape.
 */
class FastSudoku{
	// les valeurs par défaut d'une grille de Sudoku
	private static int TAILLE_DEFAUT = 9;
	private static int TAILLE_CARRE_DEFAUT = 3;

    private int taille; // la taille de la grille
    private int tailleCarre; // la taille d'un carre dans la grille
    private int[][] grille; // la grille de sudoku

    /** constructeur par défaut qui construit une grille de Sudoku
     *  standard
     */
     FastSudoku() {
     	taille =  TAILLE_DEFAUT;
    	tailleCarre = TAILLE_CARRE_DEFAUT ;
    	grille = new int[taille][taille];
    }


    /** construire une grille de taille par défaut 
     * avec des valeurs initiales 
     * PRECOND : t est de taille 9
     */
     FastSudoku(int[][] t) {
    	this(); // appel au constructeur par défaut
    	for (int i=0;i<t.length;i++)
            for (int j=0;j<t.length;j++)
                grille[i][j]=t[i][j];
    }


    /** 
     * @param l: le n° de ligne
     * @param possible : possible[k]=true si la valeur k est possible pour la ligne l
     */
     private void lignePossible(int l,boolean[] possible) {
    	
    }

     /** 
      * @param c: le n° de colonne
      * @param possible : possible[k]=true si la valeur k est possible pour la colonne c
      */
      private void colonnePossible(int c,boolean[] possible) {
     	
     }


    /** calcule les valeurs possibles pour le carre 
     * dont le coin (haut,gauche) est (i,j)
     * @param i: la coordonnée x du carré dont le coin (haut,gauche) est en (i,j)
     * @param j: la coordonnée y du carré dont le coin (haut,gauche) est en (i,j)
     * @param possible : possible[k]=true si la valeur k est possible pour le carré (i,j)
     * */
    private void carrePossible(int i,int j,boolean[] possible) {
    	
    }
 
    /** calcule les valeurs possibles pour la case (i,j) c'est à dire les 
     * valeurs que l'on peut mettre dans (i,j) 
     * 
     * @param i: 
     * @param j: 
     * @param possible : possible[k]=true si la valeur k est possible pour la case (i,j)
     * */

    private void calculePossible(int i, int j, boolean[] possible) {
    	for (int k=0;k<taille;k++)
		possible[k]=true;
	// met à jour les valeurs possibles
	lignePossible(i,possible);
	colonnePossible(j,possible);
	carrePossible(i,j,possible);
    }
    
    
    private int nombreValeursPossible(int i, int j,boolean[] possible){
    	calculePossible(i, j, possible);
    	int nbValeursPossibles = 0;
	for (int k=0;k<taille;k++)
		if (possible[k]) 
			nbValeursPossibles++;
	return nbValeursPossibles;
    }
    
    /* CONSEQUENT : remplit la grille, renvoie false si c'est impossible */
     boolean solution() {
    	
    	int nbValeursPossibles= 0;
	boolean possible[] = new boolean[taille];
    	// recherche de la case la plus contrainte de la grille
    	// c'est à dire la case qui laisse le moins de possibilités
    	
     	// A COMPLETER

     	// essai de toutes les valeurs possibles pour la case la plus contrainte
    	// for (int v=0;v<taille;v++)
 		// A COMPLETER
    	return false;
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
	
    /** @return : renvoie une chaine qui représente la grille */
    public String toString() {
    	
    	return "";
    }
}
