package lab1vide.recursionsimple;

public class Test {

    public static void main(String[] s){
        // création des objets
        int[] t1 = {11,42,67,89,113};
        int[] t2 = {14,8,21,67};

        TableauxEntiers te1 = new TableauxEntiers(t1);
        TableauxEntiers te2 = new TableauxEntiers(t2);

        System.out.println(te1.maximum());
        System.out.println(te2.maximum());

        // test des méthodes
        System.out.println(te1.estTrie());
        System.out.println(te2.estTrie());

        System.out.println(te1.rechercheVite(89));
        System.out.println(te1.rechercheVite(113));
        System.out.println(te1.rechercheVite(1));
        System.out.println(te2.rechercheVite(67));



    }
}

