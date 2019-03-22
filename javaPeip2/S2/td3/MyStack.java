import java.util.*;

/**
 * MyStack
 */
public class MyStack<E> {

    private List<E> maListe;

    public MyStack() {
        maListe = new LinkedList<E>();
    }


    public void push(E e) {
        maListe.add(this.size(), e);
    }

    public int size() {
        return (maListe.size());
    }

    public boolean isEmpty(){
        return(maListe.size()==0);
    }

    public E peek(){
        return (maListe.get(maListe.size()-1));

    }

    public E pop() {
        E e = this.peek();
        maListe.remove(maListe.size()-1);
        return(e);
    }

    
    public String toString() {
        String out = "[";
        for(E e:maListe){
            out+=e+",";
        }
        return((String) out.subSequence(0, out.length()-1)+"]");
    }

}