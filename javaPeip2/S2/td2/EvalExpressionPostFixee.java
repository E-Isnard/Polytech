import java.io.*;
import java.util.Stack;


public class EvalExpressionPostFixee{
    public static void main(String[] args) throws Exception{
        StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        Stack<Double> p = new Stack<Double>();
        st.ordinaryChar((int) '/');
        while(st.nextToken() != StreamTokenizer.TT_EOF){
            if(st.ttype == StreamTokenizer.TT_NUMBER){
                p.push(st.nval);
            }
            else{
                char op = (char) st.ttype;
                if(op == '+' || op=='-' || op=='*' || op=='/'){
                Double n2 = p.pop();
                Double n1 = p.pop();
                if(op=='+'){
                    
                    p.push(n1+n2);

                }
                else if(op=='-'){
                    p.push(n1-n2);
                }
                else if(op=='*'){
                    p.push(n1*n2);
                }
                else if(op=='/'){
                    p.push(n1/n2);
                }
            }
            else{
                if(op=='='){
                    System.out.println(p.peek());
                }
                else{
                    System.err.println("L'opérateur est inconnu");
                }
            }   
            }
        }
        
    
    }
    
}
