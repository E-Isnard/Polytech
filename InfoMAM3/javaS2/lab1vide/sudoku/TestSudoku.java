package lab1vide.sudoku;

public class TestSudoku {

    // quelques exemples de test
	
    /** A very hard instance generated for http://sudoku.koalog.com. */
    public static final int[][] FO4 = {
        {6,0,0,0,1,0,0,8,0},
        {5,1,7,4,0,0,0,0,0},
        {0,0,3,0,0,0,0,4,0},
        {0,0,0,0,0,0,0,0,1},
        {0,0,0,5,0,0,3,0,0},
        {1,6,0,0,0,9,0,5,2},
        {2,5,9,6,0,0,0,0,0},
        {0,0,0,0,7,0,0,0,0},
        {0,0,0,0,5,0,4,0,0}
    };

    /** A very very hard instance generated for http://sudoku.koalog.com. */
    public static final int[][] FO5 = {
        {4,0,0,0,3,9,0,2,0},
        {0,5,6,0,0,0,0,0,0},
        {0,0,0,0,0,0,6,0,4},
        {0,0,0,0,0,0,9,0,0},
        {5,0,0,1,0,0,2,0,0},
        {0,9,0,0,2,7,0,3,0},
        {0,3,7,0,0,0,0,0,0},
        {0,0,0,0,0,0,8,0,6},
        {9,0,8,0,1,0,0,0,0}
    };

    /** An easy instance. */
    public static final int[][] EASY = {
        {6,0,0,0,5,8,4,9,0},
        {0,2,4,0,0,0,0,0,0},
        {0,0,0,0,2,0,0,3,6},
        {0,0,0,0,0,0,9,0,7},
        {7,0,0,3,0,0,8,0,0},
        {1,5,0,0,8,9,0,0,0},
        {0,3,1,0,0,0,0,0,0},
        {0,0,0,0,3,0,5,0,0},
        {0,0,8,0,9,5,0,0,0}
    };

    /** grille Grenoble */
    public static final int[][] GREN = {
        {8,0,4,0,0,0,2,0,9},
        {0,0,9,0,0,0,1,0,0},
        {1,0,0,3,0,2,0,0,7},
        {0,5,0,1,0,4,0,8,0},
        {0,0,0,0,3,0,0,0,0},
        {0,1,0,7,0,9,0,2,0},
        {5,0,0,4,0,3,0,0,8},
        {0,0,3,0,0,0,4,0,0},
        {4,0,6,0,0,0,3,0,1}
    };

    /** An expert instance de Koalog */
    public static final int[][] EXPERT = {
        {8,0,0,0,0,9,0,0,0},
        {0,0,3,2,4,0,0,0,0},
        {0,0,0,0,0,0,0,9,6},
        {0,0,0,0,0,0,1,0,0},
        {0,0,0,5,0,0,2,0,0},
        {2,7,0,0,0,8,0,3,0},
        {1,9,8,4,0,0,0,0,0},
        {0,0,0,0,5,0,0,0,0},
        {6,0,2,0,8,0,4,0,0}
    };

    /** grille Fab */
    public static final int[][] FAB = {
        {3,0,0,0,2,7,0,0,0},
        {0,8,4,0,0,0,0,0,0},
        {0,0,0,0,0,0,7,6,0},
        {0,0,0,0,0,0,0,0,0},
        {5,0,0,1,0,0,0,0,7},
        {0,0,7,0,0,6,0,1,9},
        {0,5,9,0,0,0,0,0,0},
        {0,0,0,0,8,3,9,0,5},
        {0,0,6,0,1,0,0,0,0}
    };


	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("méthode naïve");
		Sudoku su = new Sudoku(FO5);
		System.out.println(su);
		// exemple d'affichage
//		||-------------------------||
//		|| 4 0 0 || 0 3 9 || 0 2 0 ||
//		|| 0 5 6 || 0 0 0 || 0 0 0 ||
//		|| 0 0 0 || 0 0 0 || 6 0 4 ||
//		||-------------------------||
//		|| 0 0 0 || 0 0 0 || 9 0 0 ||
//		|| 5 0 0 || 1 0 0 || 2 0 0 ||
//		|| 0 9 0 || 0 2 7 || 0 3 0 ||
//		||-------------------------||
//		|| 0 3 7 || 0 0 0 || 0 0 0 ||
//		|| 0 0 0 || 0 0 0 || 8 0 6 ||
//		|| 9 0 8 || 0 1 0 || 0 0 0 ||
//		||-------------------------||
		
		long avant = System.currentTimeMillis();
		boolean sol = su.remplir();
		long apres = System.currentTimeMillis();
		if (sol) {
			System.out.println("Il existe une solution");
			System.out.println(su);
		}
		else {
			System.out.println("Désolé, cette grille n'admet pas de solution");
			System.out.println(su);
		}
		System.out.println("temps résolution " + (apres-avant) + " ms");
		// résultat
//		Il existe une solution
//		||-------------------------||
//		|| 4 8 1 || 6 3 9 || 7 2 5 ||
//		|| 7 5 6 || 4 8 2 || 1 9 3 ||
//		|| 3 2 9 || 7 5 1 || 6 8 4 ||
//		||-------------------------||
//		|| 8 1 2 || 3 4 5 || 9 6 7 ||
//		|| 5 7 3 || 1 9 6 || 2 4 8 ||
//		|| 6 9 4 || 8 2 7 || 5 3 1 ||
//		||-------------------------||
//		|| 1 3 7 || 2 6 8 || 4 5 9 ||
//		|| 2 4 5 || 9 7 3 || 8 1 6 ||
//		|| 9 6 8 || 5 1 4 || 3 7 2 ||
//		||-------------------------||
//
//		temps résolution 92 ms
//		

		////////////
		System.out.println("\n///////////////////////");
		System.out.println("Méthode optimisée");
		FastSudoku fsu = new FastSudoku(FO5);
		avant = System.currentTimeMillis();
		sol = fsu.solution();
		apres = System.currentTimeMillis();
		if (sol) {
			System.out.println("Il existe une solution");
			System.out.println(fsu);
		}
		else {
			System.out.println("Désolé, cette grille n'admet pas de solution");
			System.out.println(fsu);
		}
		System.out.println("temps résolution " + (apres-avant) + " ms");

		Sudoku vide = new Sudoku();
		vide.remplir();
		System.out.println(vide);
//		||-------------------------||
//		|| 1 2 3 || 4 5 6 || 7 8 9 ||
//		|| 4 5 6 || 7 8 9 || 1 2 3 ||
//		|| 7 8 9 || 1 2 3 || 4 5 6 ||
//		||-------------------------||
//		|| 2 1 4 || 3 6 5 || 8 9 7 ||
//		|| 3 6 5 || 8 9 7 || 2 1 4 ||
//		|| 8 9 7 || 2 1 4 || 3 6 5 ||
//		||-------------------------||
//		|| 5 3 1 || 6 4 2 || 9 7 8 ||
//		|| 6 4 2 || 9 7 8 || 5 3 1 ||
//		|| 9 7 8 || 5 3 1 || 6 4 2 ||
//		||-------------------------||
		
		

	}

}
