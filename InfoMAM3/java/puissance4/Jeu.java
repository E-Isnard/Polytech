package puissance4;

/**
 * Jeu
 */
public class Jeu {

    private int[][] tab;

    public Jeu(int n) {
        this.tab = new int[n][n];
    }

    public Jeu() {
        this.tab = new int[8][8];
    }

    public boolean joueCoup(int col,int c) {
        int i = 0;
        while (tab[i][col]==0 && i < tab.length) {
            i++;
        }
        if (i == tab.length) {
            return false;
        } else {

            tab[i][col] = c;
            return true;

        }

    }

    public int checkCol(int col,int c) {
        int n = tab.length;
        int k = 0;
        for (int i = 0; i < n; i++) {
            if (tab[i][col] == c) {
                k++;
            }
        }
        return k;
    }

    public int checkLine(int line,int c) {
        int n = tab.length;
        int k = 0;
        for (int i = 0; i < n; i++) {
            if (tab[line][i]==c) {
                k++;
            }
        }
        return k;
    }

    public int checkDiag(int d,int c){
        int n = tab.length;
        int k = 0;
        for(int i=0;i<n-d;i++){
            if(tab[i+d][i]==c){
                k++;
            }
        }
        return k;

    }

    public int deckAntidiag(int d,int c){
        int n = tab.length;
        int k = 0;
        for(int i=0;i<n-d;i++){
            if(tab[i+d][n-i]==c){
                k++;
            }
        }
        return k;
    }

    public static void main(String[] args) {
        boolean 
    }
}