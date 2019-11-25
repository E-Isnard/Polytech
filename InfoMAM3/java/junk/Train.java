import java.util.ArrayList;

/**
 * Train
 */
public class Train {

    private Passager[][] wagons;
    public static final Passager PassagerVide = new Passager();
    private int size;

    Train(int n) {
        this.wagons = new Passager[n][50];
        this.size = n;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < 50; j++) {
                wagons[i][j] = PassagerVide;
            }

        }

    }

    public void reserver(Passager p, int wagon, int place) {
        assert wagon>=0 && place>=0 && place<50 && wagon<size;
        if (wagons[wagon][place].equals(PassagerVide)) {
            wagons[wagon][place] = p;

        }

    }

    public int nbPassager() {
        int n = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < 50; j++) {
                if (wagons[i][j] != PassagerVide) {
                    
                    n++;
                }
            }
        }
        return (n);
    }

    public int nbPassagerReduit() {
        int n = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < 50; j++) {
                if (wagons[i][j] != PassagerVide && (wagons[i][j].getAge() >= 60
                        || (wagons[i][j].getAge() >= 18 && wagons[i][j].getAge() <= 25))) {
                    n++;
                }
            }
        }
        return(n);

    }

    public ArrayList<Passager> passagerTarifPlein(){
        ArrayList<Passager> tableau = new ArrayList<Passager>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < 50; j++) {
                if (wagons[i][j] != PassagerVide && !(wagons[i][j].getAge() >= 60
                        || (wagons[i][j].getAge() >= 18 && wagons[i][j].getAge() <= 25))) {
                    tableau.add(wagons[i][j]);
                }
            }
        }
        return(tableau);


    }

    public static void main(String[] args) {
        Train t = new Train(8);
        Passager p = new Passager("Enzo","Isnard",40);
        Passager q = new Passager("Bertrand","Dupont",50);
        t.reserver(p, 3, 10);
        t.reserver(q, 2, 13);
        
        
        System.out.println(t.passagerTarifPlein());
        
    }

}