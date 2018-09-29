public class Test {
    public static void main(String[] args) {
        for (Valeur v : Valeur.values())
            System.out.println(v + " : " + v.valeur());
    }
}