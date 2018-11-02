import PaD.*;
public class Joueur {
    private String nom;
    private Carte mesCartes[] = new Carte[13];

    Joueur(String nom){
        this.nom = nom;
    }

    public Carte[] getMain(){
        return(this.mesCartes);
    }

    public void prendreMesCartes(Jeu52 jeu, int de,int à){
        int j =0;
        for(int i = de;i<à;i++){
            this.mesCartes[j] = jeu.getJeu()[i];
            j = j +1;
          
        }
    }

    public void montrerCartes(PlancheADessin pad,double h){
        // h est la hauteur à laquelle s'affiche les cartes
        int x = 0;
        for(int i = 0;i < this.mesCartes.length-1;i++){
            Dessinable titre = new Texte(520, h+25, this.nom);
            pad.ajouter(titre);
            this.mesCartes[i].dessin(pad, x, h);
            x += 40;
        }
    }

    
}