public class Jeu52 {
    private Carte jeu52[] = new Carte[52];

    public Jeu52() {
        int i = 0;
        for (Valeur v : Valeur.values()) {
            for (Couleur c : Couleur.values()) {
                jeu52[i] = new Carte(v, c);
                i++;
            }
        }

    }

    public String toString() {
        String str = new String();
        for (int i = 0; i < 52; i++) {
            str += jeu52[i];
        }
        return (str);
    }
}
