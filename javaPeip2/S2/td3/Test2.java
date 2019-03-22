import java.util.*;
public class Test2 {

    public static void main(String[] args) {
        List<Integer> maListe = new LinkedList<Integer>();
        

        for(int i=0;i<10;i++){
            maListe.add(2*i+1);
            
            
        }
        int s=0;
        for(int k:maListe){
            s+=k;
        }

        System.out.println(maListe);
        System.out.println(s);
    }
}