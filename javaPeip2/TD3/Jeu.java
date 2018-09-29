public class Jeu{
    public static void main(String[] args) {
        Carte damePique = new Carte(Valeur.dame,Couleur.pique);
        Carte asPique = new Carte(Valeur.as,Couleur.pique);
        System.out.println(asPique.compareTo(damePique));
    }
}