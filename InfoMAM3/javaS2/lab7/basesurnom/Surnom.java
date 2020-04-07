package lab7.basesurnom;

/**
 * classe pour stocker les surnoms
 * @author helen
 *
 */
class Surnom {

	// taille maximale du surnom
	public final static int MAX_SIZE = 10;

	// surnom public
	public String surnomPublic;
	// surnom privé : il faut connaitre le code pour y accéder
	public String surnomPrive;
	// le code secret
	public int code;

	// cosntructeur avec surnom public et privé
	Surnom(String pu, String pr, int c) {
		surnomPublic = pu;
		surnomPrive = pr;
		code = c;
	}

	// constructeur sans surnom privé
	Surnom(String pu) {
		this(pu, "", -1);
	}

	// renvoie le surnom public
	String surnomPublic() {
		return surnomPublic;
	}

	// renvoie le surnom privé si on fournit la clef
	public String surnomPrive(int c) {
		if (c == code)
			return surnomPrive;
		return "il faut le code pour avoir le surnom privé";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + code;
		result = prime * result + ((surnomPrive == null) ? 0 : surnomPrive.hashCode());
		result = prime * result + ((surnomPublic == null) ? 0 : surnomPublic.hashCode());
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
		Surnom other = (Surnom) obj;
		if (code != other.code)
			return false;
		if (surnomPrive == null) {
			if (other.surnomPrive != null)
				return false;
		} else if (!surnomPrive.equals(other.surnomPrive))
			return false;
		if (surnomPublic == null) {
			if (other.surnomPublic != null)
				return false;
		} else if (!surnomPublic.equals(other.surnomPublic))
			return false;
		return true;
	}

	// public boolean equals(Surnom s) {
	// 	return (this.surnomPrive.equals(s.surnomPrive) && this.surnomPublic.equals(s.surnomPublic)
	// 			&& this.code == s.code);
	// }

	

}
