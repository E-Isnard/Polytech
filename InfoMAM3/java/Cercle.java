import java.awt.Point;
import java.lang.Math;

/**
 * Cercle
 */
public class Cercle {

    public Point p;
    public double r;
    public String c;

    public Cercle(Point p,float r,String c){
        this.p = p;
        this.r = r;
        this.c = c;
    }

    public Cercle(){
        this.p = new Point();
        this.r = 5;
        this.c = "Rouge";
    }

    public void setP(Point p) {
        this.p = p;
    }

    
    public void setR(float r) {
        this.r = r;
    }

    public void setC(String c) {
        this.c = c;
    }

    public boolean inCircle(Point p){
        double dcarre =  Math.pow(p.getX()-this.p.getX(),2)+Math.pow(p.getY()-this.p.getY(),2);
        return(dcarre==r*r);
    }

    public static void main(String[] args) {
        Cercle c =  new Cercle(new Point(0,0),1,"rouge");
        Point p = new Point(1,1);
        System.out.println(c.inCircle(p));
    }

    



}