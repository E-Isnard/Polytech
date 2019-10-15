import PaD.*;

public class Carte {
    //On définit les différents attributs de la cartes
    private Couleur couleur; //Couleur de la carte
    private Valeur valeur;   //Valeur de la carte
    private PaD.Image img;   //Image la représentant dans la PaD

    public Carte(Valeur v, Couleur c) {
        //Constructeur de la carte
        this.valeur = v;
        this.couleur = c;
        //On accède à l'image de la carte.
        this.img = new Image("Cartes/" + this.valeur + "-" + this.couleur + ".gif");

    }

    public String toString() {
        //On affiche la carte sous forme de chaîne de caractères.
        //Exemple: Si la carte est l'as de pique quand on voudra l'afficher avec un print 
        //cela nous affichera : [as(10),pique]
        return ("[" + this.valeur + "(" + this.valeur.valeur() + ")," + this.couleur + "]");
    }

    public int compareTo(Carte c) {
        // Compare une carte avec une autre
        // elle renvoie 1 si la carte sur laquellle la méthode s'applique a une valeur plus grande
        // que celle donnée en argument, 0 si elles ont la meme valeur et -1 si celle en argument a une 
        // valeur plus grande que celle sur laquelle la méthode s'applique.
        if (this.valeur.valeur() > c.valeur.valeur()) {
            return (1);

        }

        else if (this.valeur.valeur() == c.valeur.valeur()) {
            return (0);
        }

        else {
            return (-1);
        }

    }

    public void dessin(PlancheADessin pad, double x, double y) {
        // Affiche la carte sur une pad aux coordonnées (x,y) données en argument
        Dessinable c = this.img;
        c.setOrig(x, y);
        pad.ajouter(c);
    }

    public Couleur getCouleur(){
        // Retourne la couleur de la carte
        return(this.couleur);
    }

    public Valeur getValeur(){
        // Retourne la valeur de la carte
        return(this.valeur);
    }

    public Carte copieCarte(){
        // Quand on veut assigner une carte à une autre , par exemple en faisant:
        // Carte carte2 = carte1
        // Cela ne nous créer pas une nouvelle carte ayant la meme couleur et la meme valeur que carte1
        // mais simplement un pointeur vers carte1.
        // Cela pose probleme quand on veut copier une main et afficher à la fois la main copiée et la
        // nouvelle main.
        // Cette méthode sert donc à créer une nouvelle carte identique à celle sur laquelle la méthode 
        // s'applique
        return(new Carte(this.valeur,this.couleur));
    }
}