import java.util.Scanner;

/**
 * Jeu
 */
public class Jeu {

    private int nbAlumettes = 17;

    public void afficherAlumettes(){
        for(int i=1;i<=nbAlumettes;i++){
            System.err.print(" | ");
        }
        System.out.println();
    }

    public void commencerJeu(){
        nbAlumettes=17;
        Joueur j = new Joueur();
        Ordinateur o = new Ordinateur();
        int n = 0;
        while(nbAlumettes>0){
            afficherAlumettes();
            n = j.propositionJoueur();
            nbAlumettes-=n;
            if(0>nbAlumettes){
                System.out.println("Vous avez gagne !\n");
            }
            else{
                n = o.propositionOrdi();
                nbAlumettes-=n;
                if(nbAlumettes<0){
                    System.out.println("L'ordinateur a gagne...\n");
                }

            }
            

        }
        this.menuJeu();
        
    }

    public void menuJeu(){
        System.out.println("Voulez-vous jouer ? (o/n)");
        Scanner sc = new Scanner(System.in);
        String choix = sc.nextLine();
        
        if(choix.equals("o")){
            this.commencerJeu();
        }
        //sc.close();
        
        
    }

}