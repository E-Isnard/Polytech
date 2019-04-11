import java.util.*;


class ListeTri<C extends Comparable<C>, V> {
    List<Element<C,V>> l;

    public ListeTri() {
	l = new ArrayList<Element<C,V>>();
    }

    public int size() {
	return l.size();
    }
    
    /*
     *  Rôle : ajout x dans la le ordonnee courante à sa place pour
     *         maintenir la relation d'ordre sur les Elements
     */
    public void add(C c, V v) {
	l.add(new Element<C,V>(c,v));
    }

    /*
     *  Rôle : tri la le courante selon la methode d'insertion sequentielle
     */
    public void  triInsertSeq() {

		for(int i  = 1;i<this.size()-1;i++){
			Element<C,V> lastElement = this.l.get(i);
			int j = i-1;
			while(j>=0 && this.l.get(j).cle().compareTo(lastElement.cle())>0){
				this.l.set(j+1,this.l.get(j));
				j--;
			}
		}
	
    }
    
    /*
     *  Rôle : tri la le courante selon la methode selection directe
     */
    public void  triSelection() {
	for (int i=0; i<l.size()-1; i++) {
	    // Invariant : la sous-le l(0..i-1) est triee
	    // et tous les Elements de l(i..l.size()-1) sont
	    // superieurs à ceux de la première sous-le
	    int min=i;
	    // chercher l'indice du  min dans la le l(i..l.size()-1)
	    for (int j=i+1; j < l.size(); j++)
		if (l.get(j).cle().compareTo(l.get(min).cle())<0)
		    min=j;
	    // echanger l.get(i) et l.get(min)
	    if (min != i) {
		Element<C,V> aux = l.get(i);
		l.set(i, l.get(min));
		l.set(min, aux);
	    }
	}
    }

    /*
     *  Rôle : tri la le courante selon la methode d'insertion dichotomique
     */
    public void  triInsertDicho() {
		for (int i = 1; i < this.l.size(); i++) {
			// Récupération du dernier élément non trié.
			Element<C, V> last = this.l.get(i);

			// Récupération du bon indice de l'élément non trié.
			int j = dichotomicSearch(last.cle(), i);

			// Pour chaque élément trié précédant l'élément non trié.
			for (int c = i - 1; c > j - 1; c--) {
				// Décalage des éléments à droite.
				this.l.set(c+1, this.l.get(c));
			}

			//System.out.println(i + " " + this.l);

			// Ajout de l'élément non trié à sa place.
			this.l.set(j, last);
		}
	}
	
	public int dichotomicSearch(C key, int max){
		// Initialisation des bornes.
		int left = 0;
		int right = max - 1;

		// Initialisation du milieu.
		int middle = (left + right) / 2;

		// Initialisation de l'élément milieu.
		Element<C,V> Element;

		// Initialisation de l'élément maximal.
		Element<C, V> ElementMax = this.l.get(max);

		do {
			// Récupération du milieu.
			middle = (left + right) / 2;

			// Récupération de l'élément milieu.
			Element = this.l.get(middle);

			// Si la clé de l'élément milieu correspond à celle de l'élément maximal.
			if (Element.cle().compareTo(ElementMax.cle()) == 0) {
				// On retourne le milieu.
				return middle;
			}

			// Si la clé de l'élément milieu est trop grande.
			else if (Element.cle().compareTo(ElementMax.cle()) > 0) {
				// Récupération de l'intervalle de gauche.
				right = middle - 1;
			}

			// Si la clé de l'élément milieu est trop petite.
			else {
				// Récupération de l'intervalle de droite.
				left = middle + 1;
			}
		} while (left <= right);

		// On retourne la borne gauche.
		return left;
	}

	public void echanger(int i, int j) {
		Element<C,V> temp = this.l.get(i);
		this.l.set(i, this.l.get(j));
		this.l.set(j, temp);
	}

	
	public int pivot(int first, int last) {
		return (first + last) / 2;
	}

	
	public int partition(int first, int last, int pivot) {
		// Le pivot est placé à la fin de la liste.
		echanger(pivot, last);

		// Initialisation du dernier élément déplacé.
		int j = first;

		// Pour chaque élément de la liste (sauf le dernier).
		for (int i = first; i < last; i++) {
			// Si l'élément est inférieur au pivot.
			if (this.l.get(i).cle().compareTo((this.l.get(last).cle())) <= 0) {
				// Echange de l'élément avec le dernier élément déplacé.
				echanger(i, j);

				// Incrémentation du dernier élément déplacé.
				j++;
			}
		}

		// Le pivot est placé à la fin des éléments déplacés.
		echanger(last, j);

		// On retourne l'indice final du pivot.
		return j;
	}

	
	public Element<C,V> triRapide(int first, int last) {
		// La liste est valide.
		if (first < last) {
			// Récupération du pivot.
			int pivot = pivot(first, last);

			// Partionnement de la liste.
			pivot = partition(first, last, pivot);

			// Tri de la liste à gauche.
			triRapide(first, pivot - 1);

			// Tri de la liste à droite.
			triRapide(pivot + 1, last);
		}

		// On retourne la valeur médiane de la liste.
		return (this.l.get((first + last)/2));
	}

	
	public Element<C,V> triRapide() {
		return triRapide(0, this.l.size() - 1);
	}
    
    public String toString() { return l.toString();  }

    private class Element<C extends Comparable<C>,V> {
        private C cle;
	private V valeur;
	
	Element(C c, V v) {
	    this.cle = c;
	    this.valeur = v;
	}

	public String toString() {
	    return "(" + this.cle + "," + this.valeur + ")";
	}

	C cle() { return this.cle; }
	V valeur() { return this.valeur; }
    } // fin class Element

    public static void main(String []args) throws Exception {
	ListeTri<Integer, String> l = new ListeTri<Integer, String>();
	Random r = new Random(); 
	long n = Long.valueOf(args[0]);
	for (int i=0; i<n; i++) l.add(r.nextInt(), "x");
	//System.out.println(l);
	System.out.println("--------- start ------------");
	long t1 = System.nanoTime();
	// l.triSelection();
	// l.triInsertSeq();
	// l.triInsertDicho();
	System.out.println("La mediane\n");
	System.out.println(l.triRapide());
	long t2 = System.nanoTime();
	System.out.println("--------- end ------------");
	System.out.println(l);
	System.out.println("temps de tri = " + (t2-t1)/1e9 + " sec");
    }
}
