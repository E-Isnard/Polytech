import java.util.Random;
import PaD.*;

public class Jeu52 {
    private Carte jeu[] = new Carte[52];

    public Carte[] getJeu(){
        return(this.jeu);
    }

    Jeu52() {
        int i = 0;
        for (Valeur v : Valeur.values()) {
            for (Couleur c : Couleur.values()) {
                this.jeu[i] = new Carte(v, c);
                i++;
            }
        }

    }

    public String toString() {
        String str = new String();
        for (int i = 0; i < 52; i++) {
            str += this.jeu[i];
        }
        return (str);
    }

    public void melanger() {
        Random r = new Random();
        for (int i = 0; i < 52; i++) {

            int j = r.nextInt(52);
            int k = r.nextInt(52);
            Carte tempCarte = this.jeu[j];
            this.jeu[j] = this.jeu[k];
            this.jeu[k] = tempCarte;
        }

    }

    public void ordonnerCouleur(){
        for (int i=52;i>0;i--){
            for(int j=1;j<i;j++){
                if(this.jeu[j-1].getCouleur().ordinal() > this.jeu[j].getCouleur().ordinal()){
                    Carte tempCarte = this.jeu[j-1];
                    this.jeu[j-1] = this.jeu[j];
                    this.jeu[j] = tempCarte;
                    

                }
            }
        }

        
    }

    public void ordonnerValeur(){
        for (int i=52;i>0;i--){
            for(int j=1;j<i;j++){
                if(this.jeu[j-1].getValeur().ordinal() > this.jeu[j].getValeur().ordinal()){
                    Carte tempCarte = this.jeu[j-1];
                    this.jeu[j-1] = this.jeu[j];
                    this.jeu[j] = tempCarte;
                    

                }
            }
        }

        
    }

    public void ordonner(){
        this.ordonnerValeur();
        this.ordonnerCouleur();
    }

    public void dessinJeu(PlancheADessin pad) {
        int x = 0;
        int y = 0;
        for (int i = 0; i < 52; i++) {
            if (x > 600) {
                x = 0;
                y += 40;
            }
            this.jeu[i].dessin(pad, x, y);
            x += 40;

        }
    }
}
