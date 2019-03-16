import PaD.*;
// La classe "Main"
public class Jeu {

    public static void main(String[] args) {
        // On crée une planche a dessin
        PlancheADessin pad = new PlancheADessin(1080, 520, true);
        // On crée un jeu et on le mélange
        Jeu52 jeu1 = new Jeu52();
        jeu1.melanger();
        
        // Cete partie ordonne le jeu et l'affiche(Questions 9 et 10)
        
        /* jeu1.ordonner();
        jeu1.dessinJeu(pad);
        Dessinable titre = new Texte(250,0,"Jeu de 52 cartes");
        pad.ajouter(titre);
        System.out.println(jeu1); */

        // Ici on crée 4 joueurs et on affiche leurs cartes et leurs noms(Question 17)
        
        /* Joueur raimu = new Joueur("Raimu");
        Joueur wanda = new Joueur("Wanda");
        Joueur martin = new Joueur("Martin");
        Joueur nicolas = new Joueur("Nicolas");
        
        raimu.prendreMesCartes(jeu1, 0, 12);
        raimu.montrerCartes(pad, 30);
        
        wanda.prendreMesCartes(jeu1, 13, 25);
        wanda.montrerCartes(pad, 140);
        
        martin.prendreMesCartes(jeu1, 26, 38);
        martin.montrerCartes(pad, 250);
        
        nicolas.prendreMesCartes(jeu1, 39, 51);
        nicolas.montrerCartes(pad, 360); */
        
        // Ici on crée une main comportant les 5 premières cartes du jeu 
        // Ensuite on crée une copie de cette main et on l'ordonne
        // Puis on affiche les deux mains pour les comparer.
        // (Question supplémentaire)
        Main main1 = new Main("Première main");
        main1.prendreMesCartes(jeu1, 0, 4);
        
        Main main2 = new Main("Deuxieme main");
        main2.copie(main1);
        main2.ordonner();
        
        main1.montrerCartes(pad, 30);
        main2.montrerCartes(pad,140);
    
    }

}