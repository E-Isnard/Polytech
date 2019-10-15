import java.util.Stack;

public class TestPile{
    public static void main(String[] args) {
        Stack<Object> S = new Stack<Object>();
        String str = "oui";
        S.push(3);
        S.push(4);
        S.push('a');
        S.push(str);
        

        System.out.println(S.peek());
        System.out.println(S.empty());
    }
    
}