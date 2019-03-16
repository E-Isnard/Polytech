import java.util.function.*;

public class Newton{

    public static void newton(double x0,double e,double dx,Function<Double,Double> f){
        if(Math.abs(f.apply(x0))<e){
            System.out.println(x0);
        }
        else{
        double pente = (f.apply(x0+dx)-f.apply(x0))/dx;
        double x1 = x0-f.apply(x0)/pente;
        newton(x1, e, dx, f);
        
        }

        
    }

    public static void main(String[] args) {
        Function<Double,Double> f = (x) -> x*x*x-2*x-5;
        newton(1, 1E-10, 1E-10, f);
    }
}