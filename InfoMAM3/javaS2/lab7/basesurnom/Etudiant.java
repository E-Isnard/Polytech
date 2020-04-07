package lab7.basesurnom;

/** classe de base pour les étudiants *:
 * @author helen
 */

 class Etudiant extends Personnel{

	// le n° de promotion
	public int promo;
	
	// créé un étudiant avec son nom, prénom et promotion
	 Etudiant(String n, String p, int pr) {
		super(n,p);
		promo = pr;
	}
	
	public String toString() {
		return super.toString() + ", étudiant, promo " + promo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + promo;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Etudiant other = (Etudiant) obj;
		if (promo != other.promo)
			return false;
		return true;
	}

	
}
