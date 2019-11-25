package recursivite;
/**
 * Factorielle
 */
public class Factorielle {
    

    public static int facto(int n){
        if(n==0){
            return 1;
        }
        else{
            return n*facto(n-1);
        }
    }

    public static void main(String[] args) {
        int n =3;
        int f = facto(n);
        System.out.println(f);
    }


}