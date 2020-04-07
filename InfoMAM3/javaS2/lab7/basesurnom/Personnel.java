package lab7.basesurnom;

/** classe de base pour le personnel *
 *  est la classe mère des étudiants et professeurs
 * @author helen
 */

 class Personnel {

	// le nom
	public String nom;
	// le prénom
	public String prenom;
	
	// construit un personnel avec son nom et son prénom
	 Personnel(String n, String p) {
		nom = n;
		prenom = p;
	}
	
	public String toString() {
		return nom + " " + prenom;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Personnel other = (Personnel) obj;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (prenom == null) {
			if (other.prenom != null)
				return false;
		} else if (!prenom.equals(other.prenom))
			return false;
		return true;
	}

	
}
