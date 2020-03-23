package lab2.search;

/**
 *  Une classe pour des algorithmes sur les tableaux d'entiers<br>
 * 
 * @author  H. Collavizza
 */

class RechercheBinaire {
	/** le tableau d'entiers sur lequel on travaille
	 */
	private int[] leTableau;

	/** pour construire le tableau
	 */
	RechercheBinaire(int[] t) {
		this.leTableau = t;
	}

	/** pour rechercher l'indice d'un élément 
	 * antécédent : leTableau est un tableau d'entiers ordonné par ordre croissant, x est un entier
	 * conséquent : 
	 * complexité : 
	 */
	public int rechercheVite(int x) {
		int gauche = 0;
		int droite = this.leTableau.length - 1;
		int milieu;
		while (gauche <= droite) {
			milieu = (gauche + droite) / 2;
			if (x == this.leTableau[milieu])
				return milieu;
			if (x < this.leTableau[milieu])
				// droite = milieu boucle pour la recherche de 6
				// boucle dès que l'on a 1 elt et que l'elt cherché est
				// plus petit
				droite = milieu - 1;
			else
				gauche = milieu;
		}
		return -1;
	}

	
}
