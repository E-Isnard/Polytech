import java.util.Random;
import PaD.*;

public class Test {
    public static void main(String[] args) {
        Figure[] tf = new Figure[4];
        Random r = new Random();

        for (int i = 0; i < 4; i++) {
            int n = r.nextInt(3);
            if (n == 0) {
                tf[i] = new Ellisse(3, 5, 0, 0);
            }

            else if (n == 1) {
                tf[i] = new Circle(2, 0, 0);

            }

            else {
                tf[i] = new Carre(2, 0, 0);
            }
        }

        for (int i = 0; i < 4; i++) {
            System.out.println(tf[i].aire());
            System.out.println(tf[i].perimetre());
            System.out.println();
        }

    }

    PlancheADessin pad = new PlancheADessin();
    Figure c = new Circle(20, 0, 0);

}