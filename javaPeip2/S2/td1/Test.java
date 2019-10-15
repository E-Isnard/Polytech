import java.util.*;

public class Test {


    public static Hashtable<String,Integer> buildRomansList(){
        Hashtable<String,Integer> romansList = new Hashtable<String,Integer>();
        romansList.put("I",1);
        romansList.put("V", 5);
        romansList.put("X",10);
        romansList.put("L",50);
        romansList.put("C",100);
        romansList.put("D",500);
        romansList.put("M",1000);
        return romansList;
    }

    public static long facto(long n) {
        assert n >= 0;
        if (n == 0)
            return 1;
        return n * facto(n - 1);
    }

    public static long fiboRec(long n) {
        if (n <= 2)
            return 1;
        return fiboRec(n - 1) + fiboRec(n - 1);
    }

    public static long fiboIte(long n){
        assert n>0;
        if(n<=2) return n;
        int fib = 1;
        int fibPrev = 1;
        for(int i =2;i<n;i++){
            int temp = fib;
            fib += fibPrev;
            fibPrev = temp;
        }

        return fib;
    }

    public static void ecrireEntierRec(int n) {
        assert n >= 0;
        if (n >= 10)
            ecrireEntierRec(n / 10);
        System.out.print(n % 10);

    }

    public static void ecrireEntierIte(int n) {
        assert n >= 0;
        int d = 1;
        List<Integer> tab = new ArrayList<Integer>();
        while (d <= n) {
            tab.add((n%(d*10))/d);
            d *= 10;
 
        }

        for (int i = tab.size() - 1; i >= 0; i--) {
            System.out.print(tab.get(i));
        }

    }

    public static int pgdc(int n,int p){
        assert n>0 && p>0;
        int i = Math.min(n,p);
        while(n%i!=0 || n%p==0){
            i--;
        }
        return(i);

    }

    public static int pgcdRec(int n,int p){
        assert n>0 && p>0;
        if(n>p) pgcdRec(n-p,p);
        else if(n<p) pgcdRec(n,p-n);
        return(n);
    }

    public static int euclide(int n,int p){
        assert n>0 && p>0;
        int r;
        int a = Math.max(n,p);
        int b = Math.min(n,p);
        while (b > 0) {
            r = a % b;
            a = b;
            b = r;
          }
        return a;
        
    }

    public static int euclideRec(int n,int p){
        assert n>0 && p>0;
        int a = Math.max(n,p);
        int b = Math.min(n,p);
        int r=a%b;
        if(r==0){
            return(b);
        }
        else{
            return euclideRec(b, r);
        }
        
    }

    public static void ecrireRomain(int n){
        assert n>=0;
        Hashtable<String,Integer> romansList = buildRomansList();
        

    }

    public static void main(String[] args) {
        // System.out.println(facto(20));
        //int k =pgdc(2821211, 12123);
        //int l = pgcdRec(5, 15);
        //int m = euclide(105,4704);
        //int n = euclideRec(3*3*9*4, 35*3*4);
        //System.out.println(k);
        //System.out.println(l);
        //System.out.println(m);
        //System.out.println(n);
        

    }

}