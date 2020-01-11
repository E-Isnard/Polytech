package src;

import java.util.Scanner;
import java.lang.Math;
import src.activites.*;
import src.activites.casino.*;
import src.gestionhotel.*;

/**
 * HotelCaracao: Classe principale qui permet de jouer un client qui arrive à
 * l'hotel Caracao On ne joue qu'un seul client, meme si on peut imaginer qu'il
 * soit accompagner.
 * 
 */

public class HotelCaracao {

    public static void main(String[] args) throws InterruptedException {

        int nbNuitsPassees = 0; // Nombre de nuits passees a l'hotel
        Double prixTotal = 0.0; // Prix qu'on devra payer à la fin du séjour dans l'hotel
        boolean premiereFoisCasino = true; // Variable pour voir si c'est notre premiere fois au casino
        Scanner s = new Scanner(System.in); // Creation du scanner pour poser des questions au client
        // Renseignement sur le Client

        System.out.println("Bonjour bienvenue a l'Hotel Blue-Bay Caracao ! ");
        System.out.println("Veuillez renseigner vos informations:");

        System.out.println("Votre prenom:");
        String prenom = Client.askQuestion("Prenom", s);

        System.out.println("Votre nom:");
        String nom = Client.askQuestion("Nom", s);

        System.out.println("Votre date de naissance");
        String naissance = Client.askQuestion("Naissance", s);

        System.out.println("Votre e-mail:");
        String email = Client.askQuestion("Email", s);

        System.out.println("Votre numero de telephone:");
        String numero = Client.askQuestion("Numero", s);

        Client client = new Client(nom, prenom, naissance, email, numero);

        // On demande au client quelle chambre il veut

        System.out.println("Nous avons 4 chambres de libre:");
        System.out.println(
                "La chambre 103 qui est une chambre simple, la chambre 202 qui est une chambre double,la chambre 331 qui est une chambre triple et la chambre 372 qui est une chambre deluxe pour 2 personnes");
        System.out.println("Laquelle voulez-vous ?");

        int choixChambre = 0;
        while (choixChambre != 103 && choixChambre != 202 && choixChambre != 331 && choixChambre != 372) {
            choixChambre = s.nextInt();
        }
        String typeChambre = "";
        if (choixChambre == 103) {
            typeChambre = "simple";
        } else if (choixChambre == 202) {
            typeChambre = "double";
        } else if (choixChambre == 331) {
            typeChambre = "triple";
        } else if (choixChambre == 372) {
            typeChambre = "deluxe";
        }

        System.out.println("Combien de nuits voulez-vous rester ?");
        int nbNuits = 0;
        // Le client doit rester au moins une nuit
        while (nbNuits <= 0) {
            nbNuits = s.nextInt();
            if (nbNuits <= 0) {
                System.out.println("Vous devez rester au moins une nuit");
            }
        }

        Chambre chambre = new Chambre(choixChambre, typeChambre, client, nbNuits);
        prixTotal += chambre.getPrix(); // On rajoute le prix de la chambre au prix total

        System.out.println("Voici le recu de votre chambre:");
        System.out.println(chambre);

        // On demande au client quelle activité le client veut faire
        int choix = 0;

        while (choix != 5) {
            System.out.println("Notre hotel vous propose differentes activites:");
            System.out.println("1.Aller dormir dans votre chambre");
            System.out.println("2.Aller jouer dans notre Casino,le Deluxe Caesars Palace");
            System.out.println("3.Aller manger dans notre restaurent 5 etoiles");
            System.out.println("4.Aller vous relaxer a notre Spa 'Yumma Massage'");
            System.out.println("5.Partir de l'hotel\n");
            System.out.println("Votre choix: ");

            choix = s.nextInt();

            if (choix == 1) {

                if (nbNuits > nbNuitsPassees) {
                    System.out.println("Bonne nuit !");
                    Chambre.dodo(); // on dort
                    nbNuitsPassees++;
                } else {
                    System.out.println("C'est votre dernier jour a l'hotel !\n");
                }

            } else if (choix == 2) {

                System.out.println("Bienvenue au Casino 'Deluxe Caesars Palace' !");
                System.out.println("Nous vous proposons de jouer a la roulette pour tester votre chance !");
                // On affiche un message si c'est la premiere fois qu'on va au casino
                if (premiereFoisCasino) {
                    System.out.println(
                            "Comme c'est votre premiere fois au Casino nous vous offrons 20 euros de jeton(2 euros = 1 jeton) !");
                }
                System.out.println("Combien voulez-vous de jetons ?");
                int nbJetons = s.nextInt();
                System.out.println("Nous rajoutons donc " + 2 * nbJetons + "euros au prix total");
                prixTotal += 2 * nbJetons; // On rajoute au prix total le prix des jetons achetés
                if (premiereFoisCasino) {
                    nbJetons += 10;// On offre 10 jetons si c'est la premiere fois que le client va au casino

                }
                Casino casino = new Casino(nbJetons);

                boolean go = true; // Variable pour arreter la boucle quand on a en a marre de jouer

                while (go) {

                    System.out.println(
                            "Sur quoi voulez-vous parier?(rouge,noir,pair,impair,manque,passe ou un nombre entre 0 et 36)");
                    String paris = s.next();

                    System.out.println("Combien voulez-vous parier ?");
                    int montant = s.nextInt();

                    try {
                        Mise mise = new Mise(montant, paris);
                        casino.roulette(mise); // Si le montant de la mise est plus grand que le nombre de jetons qu'on
                                               // a
                                               // au casino la roulette ne se lance pas
                    } catch (MiseIncorrecteException mie) {
                        System.out.println(mie); // L'exception sera levée si le type de paris est incorrect
                    }
                    System.out.println("Il vous reste " + casino.getNbJetons() + " jetons");
                    System.out.println("Voulez-vous racheter des jetons ?(o/N)");
                    String racheter = s.next();
                    if (racheter.equals("O") || racheter.equals("o")) {
                        System.out.println("Combien de jetons voulez-vous en plus ?");
                        int jetonsEnPlus = s.nextInt();
                        prixTotal += 2 * jetonsEnPlus;
                        casino.rajouterJetons(jetonsEnPlus);
                    }

                    System.out.println("Voulez vous partir du casino ?(o/N)");
                    String partir = s.next();
                    go = (!partir.equals("O") && !partir.equals("o"));

                }

                System.out.println("J'espere que vous vous etes bien amuse !");
                System.out.println("Il vous reste " + casino.getNbJetons() + " jetons");
                System.out.println("Vous recuperez donc " + 2 * casino.getNbJetons() + " euros\n");
                // On rend les jetons restants sous forme d'argent
                prixTotal -= 2 * nbJetons; // Prix total peut etre negatif si on a gagne plus d'argent au casino que ce
                                           // que l'on doit a l'hotel
                // premiere fois casino devient faux puisqu'on a joue au casino
                premiereFoisCasino = false;

            } else if (choix == 3) {

                Restaurant resto = new Restaurant(client);

                System.out.println("Bienvenue dans notre restaurent, le \"Mojiro-Mojiro\" !");
                System.out.print("Vous avez le choix entre prendre un plat(5 euros),un accompagnement(2 euros),");
                System.out.println("un dessert(3,5 euros) et une boisson(2 euros)");
                System.out.println(
                        "Vous avez aussi le choix entre deux formules:la \"Mojito\"(6euros,plat+boisson) ou \"Daiquiri\"(8 euros,plat+accompagnement+boisson) ");

                boolean finDeCommande = false; // variable pour voir si le client a fini de commander
                String continuerCommander = "";

                while (!finDeCommande) {
                    System.out.println(
                            "Que voulez-vous commander ?{'plat','accompagnement','boisson','dessert','menuMojito','menuDaiquiri'}");
                    String commande = s.next();
                    resto.addCommande(commande);
                    System.out.println("Ca sera tout? (o/N)");
                    continuerCommander = s.next();
                    // Si on répond "o" ou "O" on arrete de commander
                    finDeCommande = (continuerCommander.equals("O") || continuerCommander.equals("o"));

                }

                System.out.println("Bonne appetit ! ");
                resto.manger();

                System.out.println("J'espere que vous vous etes regale(s) !");

                // Affichage de la note
                System.out.println("Voivi la note:");
                System.out.println(resto);
                // On affiche le prix des commandes au prix total
                prixTotal += resto.getPrixTotal();

            } else if (choix == 4) {

                Spa spa = new Spa();
                boolean continuerMassage = true;

                System.out.println("Bienvenue au Spa \"Yuma Massage\" !  ");
                System.out.println("Voici une description de nos differents massages");
                // La classe toString de spa renvoie une description des massages
                System.out.println(spa);

                while (continuerMassage) {

                    System.out.println("Quel massage voulez-vous ?");
                    int choixMassage = s.nextInt();
                    spa.addMassage(choixMassage);
                    System.out.println("Voulez-vous un autre massage(o/N) ?");
                    String reponse = s.next();
                    // Si on repond "o" ou "O" on continue de commander des massages
                    continuerMassage = (reponse.equals("O") || reponse.equals("o"));

                }
                System.out.println("Voici la facture:");
                // Affichage de la facture
                spa.afficherFacture();
                prixTotal += spa.getPrixSpa();
                System.out.println("A bientot !");

            }

        }
        System.out.println("Vous nous quittez deja ! J'espere que vous avez passe un bon sejour");
        // On affiche un message differents en fonction de si on doit de l'argent ou non
        // a l'hôtel
        prixTotal = Math.ceil(prixTotal * 100) / 100; // On arondit deux chiffres apres la virgule
        if (prixTotal <= 0) {
            System.out.println("Je vois que vous avez ete chanceux au Casino, vous gagnez donc " + (-prixTotal));
        } else if (prixTotal == 0) {
            System.out.println("Je vois que vous avez ete chanceux au Casino, vous ne nous devez rien");
        } else {
            System.out.println("Le prix total est de " + prixTotal + " euros");
        }
        System.out.println("Au revoir a bientot !");

        s.close(); // On ferme le scanner

    }

}