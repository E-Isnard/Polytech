package lab7.basesurnom;

import java.util.HashMap;

/** la base de données des surnoms
 * on stocke des objets de type surnom, et on y accède par la personne 
 * en entier
 * @author helen
 */
 class ServeurSurnom {

	// la liste d'association des personnes et de leurs surnoms
	private HashMap<Personnel,Surnom> listeSurnom;
	
	 ServeurSurnom() {
		listeSurnom = new HashMap<Personnel,Surnom>();
	}
	
	// ajoute une personne avec un surnom public, privé et le code associé
	 void addSurnom(Personnel p, String pu,String ps,int code) {
		addSurnom(p,new Surnom(pu,ps,code));
	}
	
	// ajoute une personne avec un surnom public
	 void addSurnom(Personnel p, String pu) {
		addSurnom(p,new Surnom(pu));
	}
	
	// ajoute une personne avec son surnom
	private void addSurnom(Personnel p, Surnom s){
		listeSurnom.put(p, s);
	}
	
	// récupère le surnom public d'un personne
	 String getSurnomPublic(Personnel p) {
		return (listeSurnom.get(p)).surnomPublic();
	}
	
	// récupère le surnom privé d'une personne
	 String getSurnomPrive(Personnel p,int code) {
		return (listeSurnom.get(p)).surnomPrive(code);
	}
	
	// affiche les surnoms publics
	public String toString() {
		String s = "Liste des surnoms\n";
		for (Personnel n : listeSurnom.keySet()) {
			Surnom su = listeSurnom.get(n);
			s += n;
			s += " surnomé : " + su.surnomPublic() + "\n";
		}
		return s;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((listeSurnom == null) ? 0 : listeSurnom.hashCode());
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
		ServeurSurnom other = (ServeurSurnom) obj;
		if (listeSurnom == null) {
			if (other.listeSurnom != null)
				return false;
		} else if (!listeSurnom.equals(other.listeSurnom))
			return false;
		return true;
	}

	

}
