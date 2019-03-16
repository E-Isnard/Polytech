import java.io.*;
import java.util.*;

public class Parenthese {

    public static Boolean isWellParenthesed(String str) {
        Stack<Character> pile = new Stack<Character>();
        char[] charArray = str.toCharArray();
        for (char c : charArray) {
            if (c == '(' || c == '[' || c == '{') {
                pile.push(c);
            } else if (c == ')' || c == ']' || c == '}') {
                if (pile.isEmpty()) {
                    return false;
                } else if (c == ')' && pile.pop() != '(') {
                    return false;
                } else if (c == ']' && pile.pop() != '[') {
                    return false;
                } else if (c == '}' && pile.pop() != '{') {
                    return false;
                }
            }
        }
        return (pile.isEmpty());
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String fileName = sc.nextLine();
        File f = new File(fileName);
        FileReader fr = new FileReader(f);
        BufferedReader buf = new BufferedReader(fr);
        String str = buf.readLine();
        System.out.println(isWellParenthesed(str));
        buf.close();
        sc.close();
    }

}
