import java.util.Random;

/**
 * Secret
 */
public class Secret {

    private String message;

    Secret(String msg) {
        this.message = msg;
    }

    private String code1() {
        String out = "";
        int n = message.length();
        for (int i = n - 1; i >= 0; i--) {
            out += message.charAt(i);
        }
        return out;
    }

    private String code2() {
        String alphabet1 = "abcdefghiklmnopqrstuvwxyz";
        String alphabet2 = "xedpjohavwtiyszunqcgbxmkh";
        String out = "";
        int n = message.length();
        for (int i = 0; i < n; i++) {
            char c = message.charAt(i);
            int j = alphabet1.indexOf(c);
            out += alphabet2.charAt(j);

        }
        return out;
    }

    private String code3() {
        String alphabet1 = "abcdefghiklmnopqrstuvwxyz";
        Random r = new Random();
        int k = r.nextInt(25);
        int n = message.length();
        String out = "";
        for (int i = 0; i < n; i++) {
            System.out.println(k);
            char c = message.charAt(i);
            int j = (alphabet1.indexOf(c) + k) % n;
            char newC = alphabet1.charAt(j);
            out += newC;
            k = alphabet1.indexOf(newC) + 1;

        }
        return out;

    }

    public static void main(String[] args) {
        Secret s = new Secret("aaaaa");
        System.out.println(s.code3());
    }
}