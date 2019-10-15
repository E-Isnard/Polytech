import java.util.ArrayList;

/**
 * Diviseur
 */
public class Diviseur {

    public static void diviseurs(int n) {
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                System.out.println(i);
            }
        }
    }

    public static int decompremierNUL(int m, int... pj) {
        for (int p : pj) {
            for (int i = p; i <= m; i++) {
                if (m % i == 0) {
                    return (i);
                }
            }
        }
        return (0);
    }

    public static boolean isPremier(int n) {
        int i = 2;
        if (n < 2)
            return (false);
        while (i < n) {
            if (n % i == 0) {
                return (false);
            }
            i++;

        }
        return (true);
    }

    public static ArrayList<Integer> decompPremier(int n) {
        ArrayList<Integer> t = new ArrayList<Integer>();
        if (Diviseur.isPremier(n)) {
            t.add(n);
            return (t);
        } else {
            int i = 2;
            while (n > 1) {
                while (n % i == 0) {
                    t.add(i);
                    n = n / i;
                }
                i++;
            }
            return (t);
        }
    }

    public static int inverse(int n) {
        int inverse = 0;
        while (n != 0) {
            inverse = 10 * inverse + n % 10;
            n = n / 10;

        }
        return (inverse);
    }

    public static boolean isPalindrome(int n) {
        return (n == inverse(n));
    }

    public static boolean isMirror(int n) {
        int p = n;
        int i = 0;
        while (p != 0) {
            i++;
            p /= 10;
        }
        return (isPalindrome(n) && i % 2 == 0);
    }

    public static void main(String[] args) {
        System.out.println(inverse(123));
        System.out.println(isPalindrome(121));
        System.out.println(isMirror(121));

    }
}
