import java.lang.Math;

/**
 * Trigo
 */
public class Trigo {

    public static double cos(double x, int n) {
        int i = 1;
        double s = 1;
        double signe = 1;
        double fact = 1;
        double puissance = 1;
        while (2 * i <= n) {

            signe = signe * -1;
            fact = fact * (2 * i - 1) * (2 * i);
            puissance = puissance * x * x;
            s = s + signe * puissance / fact;
            i++;

        }
        return (s);
    }

    public static double sin(double x, int n) {
        int i = 0;
        double s = 0;
        double signe = 1;
        double fact = 1;
        double puissance = x;
        while (2 * i + 1 <= n) {

            s = s + signe * puissance / fact;
            fact = fact * (2 * i + 2) * (2 * i + 3);
            puissance = puissance * x * x;
            signe *= -1;
            i++;
        }
        return (s);

    }

    public static void main(String[] args) {
        System.out.println(sin(Math.PI / 6, 40));
        System.out.println(Math.sin(Math.PI / 6));

    }
}