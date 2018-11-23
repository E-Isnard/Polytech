public class Test {
    public static void main(String[] args) {
    Figure [] tf = new Figure [4];
    tf[0] = new Ellipse(3,5,0,0);
    tf[1] = new Rectangle (8 ,3,0,0);
    tf[2] = new Carre (4,0,0);
    tf[3] = new Cercle (4,0,0);
    
    double s = tf[0].aire();

    System.out.println(s);

        
    }
}