public class Hanoi {
    public static void deplacer(int n,char a,char b){
        System.out.println("Je déplace le disque "+n+" de la plateforme "+a+" vers la plateforme "+b);
    }

    public static void hanoi(int n,char a,char b,char c){
        
        if(n>0){
        hanoi(n-1,a,c,b);
        deplacer(n, a, b);
        hanoi(n-1,c,b,a);
        }
    }


    public static void main(String[] args) {
        hanoi(3,'a','b','c');
    }
}