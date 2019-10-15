import java.util.function.*;
public class Dichotomie {
    

    public static void dichotomie(Function<Double,Double> f,double a, double b, double e) throws NoZeroExecption {

        double c = (a + b) / 2;
        if (Math.abs(b - a) < e) {
            if (f.apply(a) * f.apply(b) < 0) {
                System.out.println(c);
            } else {
                throw new NoZeroExecption();
            }
        } else if (f.apply(a) * f.apply(c) < 0) {
            dichotomie(f,a, c, e);
        } else {
            dichotomie(f,c, b, e);
        }
    }

    

    



    public static void main(String[] args) {
        Function<Double,Double> f = (x) -> Math.exp(x)-2;
        try {
            dichotomie(f,0, 10, 1E-3);
        } catch (NoZeroExecption e) {

            System.out.println(e);
        }

    }
}