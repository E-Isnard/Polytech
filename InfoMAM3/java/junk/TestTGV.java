/**
 * TestTGV
 */
public class TestTGV {

    public static void afficheTGV(TGV[] tab, String u, String v) {
        for (TGV t : tab) {
            if (TGV.findIndex(t.getGares(), u) != -1 && TGV.findIndex(t.getGares(), v) != -1) {
                System.out.println(t);
            }
        }
    }

    public static void afficheTGVCorres(TGV[] tab, String u, String v) {
        for (int i = 0; i < tab.length; i++) {
            for(int j=i;j<tab.length;j++){
                if(TGV.findIndex(tab[i].getGares(), u)!=-1 && TGV.findIndex(tab[j].getGares(), v)!=-1 && tab[i].intersection(tab[j])!=null ){
                    System.out.print(tab[i]);
                    System.out.print(" -> arret " + tab[i].intersection(tab[j]) +" -> ");
                    System.out.println(tab[j]);
                }

            }

        }
    }

    public static void main(String[] args) {
        String[] gares = {"1","2","3"};
        int[] horaires = {100,200,300};
        String[] gares2 = {"a","b","2","c"};
        int[] horaires2 = {10,200,400};
        String[] gares3 = {"j","k","l","1","c","2"};
        int[] horaires3 = {10,200,400,450,500};

        TGV t1 = new TGV(1, gares, horaires);
        TGV t2 = new TGV(2, gares2, horaires2);
        TGV t3 = new TGV(3, gares3, horaires3);

        TGV[] tab = {t1,t2,t3};

        //afficheTGV(tab, "1", "2");
        afficheTGVCorres(tab, "1", "c");
    }

}