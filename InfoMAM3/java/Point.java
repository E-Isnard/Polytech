/**
 * Point
 */
public class Point {
    private double x;
    private double y;

    public Point(double x,double y){
        this.x = x;
        this.y = y;
    }

    public Point(){
        this.x = 0;
        this.y = 0;
    }

    public String toString(){
        return("("+x+","+y+")");
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }

    public static void main(String[] args) {
        Point p = new Point(10,20);
        System.out.println(p);
        p.setX(30);
        System.out.println(p);
    }
    
}