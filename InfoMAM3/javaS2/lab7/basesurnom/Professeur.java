package lab7.basesurnom;

/** classe de base pour les professeurs 
 * 
 * @author helen
 */
 class Professeur extends Personnel {

	// le laboratoire
	public String laboratoire;
	
	// construit un professeur avec son nom, prénom et laboratoire
	 Professeur(String n, String p, String l) {
		super(n,p);
		laboratoire = l;
	}
	
	public String toString() {
		return super.toString() + ", professeur,  laboratoire " + laboratoire;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((laboratoire == null) ? 0 : laboratoire.hashCode());
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
		Professeur other = (Professeur) obj;
		if (laboratoire == null) {
			if (other.laboratoire != null)
				return false;
		} else if (!laboratoire.equals(other.laboratoire))
			return false;
		return true;
	}

	
}
