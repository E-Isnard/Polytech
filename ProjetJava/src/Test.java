package src;
import java.util.Scanner;
import src.activites.*;
import src.activites.casino.*;
import src.gestionhotel.*;

/**
 * Classe Test: Elle sert à voir si toutes les fonctions marchent correctement
 * 
 * @param args
 * @throws MiseIncorrecteException
 * @throws InterruptedException
 */
public class Test {
    public static void main(String[] args) throws MiseIncorrecteException, InterruptedException {// Test de la classe
                                                                                                 // Client
        String num = "+33651034089"; // Numéro de téléphone
        String date = "18/08/99"; // Date de naissance
        String email = "enzoisnard@gmail.com"; // Adresse mail
        Client cl1 = new Client("isnard", "enzo", "18/08/1999", "enzoisnard@gmail.com", "0651034089"); // Création du
                                                                                                       // premier client
        int n = Client.checkNum(num); // Test de la fonction checkNum
        boolean b = Client.checkEmail(email); // Test de la fonction checkEmail
        boolean b2 = Client.checkDate(date); // Test de la fonction checkDate
        System.out.println(n); // Affiche les résultats
        System.out.println(cl1); // Test de toString
        System.out.println(b);
        System.out.println(b2);
        // Création d'un deuxième client
        Client cl2 = new Client("picke", "theo", "24/08/1998", "theopicke@gmail.com", "0679737891"); // Création du
                                                                                                     // second client
        System.out.println(cl1); // Test de toString

        // Test de askQuestion
        Scanner sc = new Scanner(System.in);
        String prenom = Client.askQuestion("Prenom", sc);
        String nimportequoi = Client.askQuestion("nimportequoi", sc);
        sc.close();
        System.out.println(prenom);
        System.out.println(nimportequoi);

        // Test de la classe Chambre
        int id1 = 100; // ID de la Chambre 1
        String t1 = "simple"; // Type de la Chambre 1
        Chambre c1 = new Chambre(id1, t1, 2); // Création de la Chambre 1 via constructeur 1
        int id2 = 101; // ID de la chambre 2
        String t2 = "triple"; // type de la chambre 2
        Chambre c2 = new Chambre(id2, t2, cl2, 2); // Création de la Chambre 2 via constructeur 2
        int id3 = 201; // ID de la chambre 3
        String t3 = "deluxe"; // Type de la Chambre 3
        Chambre c3 = new Chambre(id3, t3, 1);
        Double p = c1.defPrix(t1); // test de defPrix, même si déjà utilisée dans les constructeurs
        c1.setClient(cl1); // test de setClient, même si déjà utilisé dans constructeur 2
        int id4 = 666; // ID de remplacement pour la Chambre 1
        c1.setID(id4); // test de setID
        String t4 = "double"; // Type de chambre à remplacer pour la Chambre 1
        c3.setType(t4); // test de setType
        c1.libererChambre(); // test de libererChambre
        System.out.println(c1); // test de toString, affiche les résultats
        System.out.println(c2); // test de toString, affiche les résultats
        c3.affecterChambre(cl1); // Test de affecterChambre, affiche les résultats

        // Test de dodo

        Chambre.dodo();

        // Test de la classe Restaurant
        Restaurant r1 = new Restaurant(); // Création de la fiche Restaurant 1 via constructeur 1
        Restaurant r2 = new Restaurant(cl1); // Création de la fiche Restaurant 2 via constructeur 2
        Restaurant r3 = new Restaurant(cl2); // Création de la fiche Restaurant 3 via le constructeur 2
        r1.setClient(cl1); // Test de setClient
        r3.setClient(null); // Test de setClient
        int a1 = 1; // Nombre de Tickets à ajouter
        r1.addTicket(a1); // Test de addTicket
        int a2 = 5;
        r2.addTicket(a2);
        r3.addTicket(6); // Test de addTicket avec un client=null, renvoie un message d'erreur sur le
                         // terminal mais continue l'éxécution de main. Ne réalise pas l'opération
                         // d'ajouter les tickets.
        r1.resetTickets(); // Test de resetTickets
        String e1 = "dessert"; // Element/menu à ajouter à la carte
        r1.addCommande(e1); // test d'utilisation d'addCommande
        r1.resetCommande(); // test de resetCommande
        String e2 = "menuMojito";
        r2.addCommande(e2);
        r2.addCommande(e1);
        r3.addCommande("Pizza Reine"); // N'éxécute pas l'ajout à la commende, renvoie un message sur le terminal
        r3.addCommande(e1);
        String z = r2.reductionTickets(40.0);// Test de reductionTickets
        System.out.println(z);
        System.out.println(r1); // Test de toString
        System.out.println(r2);
        System.out.println(r3);

        // Test de manger

        r1.manger();

        // Test de la classe Spa
        Spa s = new Spa();
        System.out.println(s);// On affiche la description du Spa
        s.addMassage(1);
        s.afficherFacture();
        s.addMassage(4);// Massage qui n'existe pas
        s.afficherFacture();// On voit que le massage 4 n'est pas pris en compte puisqu'il n'existe pas

        // Test des classes Casino et Mise
        Mise m = new Mise(200, "manque");
        Casino c = new Casino(500);
        c.roulette(m);
        System.out.println(c.getNbJetons());
        m.setMontant(800);// Mise trop haute
        c.roulette(m);// La roulette nous dit qu'on a pas assez de jetons
        try {
            Mise m2 = new Mise(300, "N'importequoi");// Mise volontairement incorrecte
        } catch (MiseIncorrecteException mie) {
            System.out.println(mie);// On voit que l'erreur est affichée car la mise est incorrecte
        }

    }
}
