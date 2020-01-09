package src.activites;

import java.time.LocalDateTime; // Import the LocalDateTime class
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class

import src.gestionhotel.Client;

/**
 * Fonction qui permet de commander au restaurant
 * 
 * @author Enzo Isnard et Théo Picke
 * 
 */
public class Restaurant { // La classe restaurant permet de creer une fiche restaurant pour 1 personne,
                          // qui permettra d'enregistrer et d'afficher la commande du client en question.
  private Client client; // Le Client du Blue-Bay Caraçao qui commande au Mojito-Mojito. On prendra
                         // client=null pour toute personne qui n'est pas cliente a l'hôtel.
  private int codeCommande; // Le code commande qui permet de verifier ce que le Client a commande.
  // Fonctionement du code commande : Le code commande n'est que la commande codee
  // sous la forme d'une entier. Le code commande vaut 1 si le Client n'a rien
  // demande.
  // Lorsque le client commande un nouvel element, son code commande est multiplie
  // par un nombre premier (2 pour le plat, 3 pour l'accompagnement, 5 pour la
  // boisson, 7 pour le dessert)
  // Lorsque le client commande un menu, son code commande est multiplie par le
  // produit de nombre premier correspondant (10 pour le menu 'Mojito', 30 pour
  // 'Daiquiri')
  // On peut ainsi verifier aisement le contenu de la commande du Client avec
  // l'operateur %, et cela permet d'ajouter automatiquement les menus si le
  // client commande les elements du menu mais pas le menu directement
  private int nbTicketsSortis; // Le nombre de Tickets Mojito-Mojito donnes par le client
  private double prixTotal; // Prix totale de la commande

  public Restaurant() { // Un constructeur qui cree un objet de type Restaurant permettant de realiser
                        // des commandes au Mojito-Mojito pour les non-clients.
    // Le client est attribue a null, cela veut dire qu'il n'est pas enregistre a
    // l'hôtel et n'a donc pas accès au système de tickets Mojito-Mojito.
    this.codeCommande = 1; // Il n'a encore rien commande, donc son code commande vaut 1
    this.nbTicketsSortis = 0; // Un ticket Mojito-Mojito est fourni au Client a son entree dans le resto.
                              // Cependant, il peut choisir de l'utiliser lors du prochain repas.
    // Le nombre de Tickets Mojito-Mojito utilises dependra du nombre que le client
    // presente a la caisse.
  }

  /**
   * Un constructeur qui cree une fiche restaurant pour le Client c. Cette fiche
   * gardera en memoire les tickets utilises et sortis. Si c=null, se comporte
   * comme le constructeur ci-dessus
   * 
   * @param client2 Le client du restaurant
   */
  public Restaurant(src.gestionhotel.Client client2) { //
    this.client = client2; // On utilise la fiche Client de l'hôtel
    this.codeCommande = 1;
    this.nbTicketsSortis = 0;
    this.prixTotal = 0.0;
  }

  /**
   * Une fonction qui permet d'attribuer le client C a la fiche Restaurant. Si le
   * client=null, reset les tickets Mojito-Mojito.
   * 
   * @param c Le client du restaurent
   */
  public void setClient(Client c) {
    this.client = c;
    if (c == null) {
      this.nbTicketsSortis = 0;
    }
  }

  /**
   * Rajoute t tickets Mojito-Mojito a la fiche restaurant du Client, en fonction
   * du nombre qu'il presente a la caisse. S'il s'agit d'une fiche non-Client,
   * l'action n'a tout simplement pas lieu et un message est envoye sur le
   * terminal.
   * 
   * @param t nombre de ticket a rajouter
   */
  public void addTicket(int t) {
    if (this.client == null) {
      System.out.println(
          "L'operation d'ajouter des tickets Mojito n'a pu etre executee, car la commande n'est pas enregistree au nom d'un Client du Blue-Bay Caraçao. Utilisez setClient ou creez un autre objet Restaurant.");
    } else { // Si un Client lui est bien attribuee, alors l'operation se deroule sans
             // problème
      this.nbTicketsSortis = this.nbTicketsSortis + t;
    }
  }

  /**
   * Une fonction qui permet de reset les tickets Mojitos-Mojitos, sans reset le
   * client
   */
  public void resetTickets() {
    this.nbTicketsSortis = 0;
  }

  /**
   * Fonction reccursive. Ajoute un element a la commande actuelle, ce qui modifie
   * le codeCommande. L'element ajoute doit faire partie de la liste suivante :
   * {"plat","accompagnement","boisson","dessert","menuMojito","menuDaiquiri"} et
   * il doit ne pas avoir deja ete commande. Sinon, l'action n'est pas realisee et
   * un message est envoye sur le terminal
   * 
   * @param ajout commande a rajouter
   */
  public void addCommande(String ajout) {
    if (ajout.equals("plat")) {
      if (codeCommande % 2 == 0) { // Le code est conçue de telle façon qu'il soit egal a 1 ou au produit de nombre
                                   // premiers differents.
        // Un moyen simple pour savoir si la commande comprend un plat ou non est donc
        // de verifier s'il est divisible par le nombre premier associe, ici 2.
        System.out.println(
            "L'operation d'ajouter un plat a la commande n'a pu etre executee, car le Client a deja commande un plat.");
        // Si un plat a dejà ete pris on affiche un message d'erreur

      } else {
        this.codeCommande = this.codeCommande * 2;
        prixTotal += 5;
      }
    } else if (ajout.equals("accompagnement")) { // La meme logique d'applique pour chaque element a la carte
      if (codeCommande % 3 == 0) {
        System.out.println(
            "L'operation d'ajouter un accompagnement a la commande n'a pu etre executee, car le Client a deja commande un accompagnement.");
      } else {
        this.codeCommande = this.codeCommande * 3;
        prixTotal += 2;
      }
    } else if (ajout.equals("boisson")) {
      if (codeCommande % 5 == 0) {
        System.out.println(
            "L'operation d'ajouter une boisson a la commande n'a pu etre executee, car le Client a deja commande une boisson.");
      } else {
        this.codeCommande = this.codeCommande * 5;
        prixTotal += 2;
      }
    } else if (ajout.equals("dessert")) {
      if (codeCommande % 7 == 0) {
        System.out.println(
            "L'operation d'ajouter un dessert a la commande n'a pu etre executee, car le Client a deja commande un dessert.");
      } else {
        this.codeCommande = this.codeCommande * 7;
        prixTotal += 3.5;
      }
    } else if (ajout.equals("menuMojito")) { // dans le cas des menus on se contente d'executer la fonction pour chaque
                                             // element du menu, car celui-ci sera detecte automatiquement a l'addition
                                             // (fonction toString)

      if (codeCommande % 2 == 0 || codeCommande % 5 == 0) {
        System.out.println("Vous ne pouvez pas prendre un menu Mojito car vous avez deja pris un plat ou une boisson");
      } else {
        codeCommande *= 10; // 10=2*5, on rajoute donc un plat et une boisson au code commande
        prixTotal += 6;
      }
    } else if (ajout.equals("menuDaiquiri")) { // Meme chose pour le menu Daiquiri, qui peut etre après ou avant le
                                               // menuMojito car on ajoute un element/menu a la fois. L'ordre aura une
                                               // importance pour calculer l'addition (fonction toString)
      if (codeCommande % 2 == 0 || codeCommande % 5 == 0 || codeCommande % 3 == 0) {
        System.out.println(
            "Vous ne pouvez pas prendre un menu Daiquiri car vous avez deja pris un plat, un accompagnement ou une boisson");
      } else {
        codeCommande *= 30; // 30=2*5*3,on rajoute donc un plat,une boisson et un accompagnement au code
                            // commande
        prixTotal += 8;
      }

    } else {
      System.out.println("L'operation d'ajouter '" + ajout
          + "' a la commande n'a pu etre executee, car la chaîne de charactères ne fait pas partie de celles autorisees : {'plat','accompagnement','boisson','dessert','menuMojito','menuDaiquiri'}");
    }
  }

  /**
   * Une fonction qui permet de reset la Commande en cours, Client ou non Client.
   * Ne reset pas les tickets Mojitos-Mojitos sortis.
   */
  public void resetCommande() {
    this.codeCommande = 1;
  }

  /**
   * Cette fonction est utilisee dans la fonction toString. Pour la commande dont
   * le prix total est p, renvoie un ticket sur l'utilisation des coupon du Client
   * s'inspirant de ce qui est indique dans le 3.5.3.4 du Projet
   * 
   * @param p: prix de la commande
   * @return renvoie les prix
   */
  public String reductionTickets(double p) {
    String rep = "\n\nTickets Mojito-Mojito\nNombre de tickets sortis : " + this.nbTicketsSortis
        + "   Nombre de tickets utilises : "; // On stocke ce qui va etre renvoye par la fonction dans la variable rep
    double r = this.nbTicketsSortis * 5.0; // On calcule le coût maximal rembourse par les coupons
    int nbTicketsUtilises = this.nbTicketsSortis; // nbTicket sortis vaudra le nombre de tickets utilises pour payer
                                                  // l'addition. Pour le moment on considère qu'on utilise tout les
                                                  // tickets, puis on reduira ce nombre autant de fois que necessaire
    if (r > p) { // Si le coût maximal rembourse par les ticket depasse le prix a payer, on doit
                 // determiner si il faut utiliser moins de tickets que sorti par le Client, mais
                 // il n'y a en aucun cas un reste a payer
      while (r - 5.0 > p) {
        nbTicketsUtilises = nbTicketsUtilises - 1;
        r = r - 5.0;
      }
      rep = rep + nbTicketsUtilises + "\nTotal des supplements a payer : 0.00 euros";
    } else { // Si le prix a payer depasse ou est egal celui rembourse par les tickets, alors
             // le nombre de tickets sortis egale le nombre de ticket utilises, et il y a un
             // reste a payer
      double suppl = p - r; // On determine la valeurs des supplements
      rep = rep + nbTicketsUtilises + "\nTotal des supplements a payer : " + suppl + "0 euros";
    }
    return rep;
  }

  /**
   * Renvoie l'addition de la commande
   * 
   * @return L'addition
   */
  public String toString() {
    LocalDateTime myDateObj = LocalDateTime.now();
    String rep = "******************** MOJITO-MOJITO ********************\nDate : ";
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("MMM dd, yyyy hh:mm:ss a");
    rep = rep + myDateObj.format(myFormatObj) + "\nCommande :\n"; // Renvoie l'heure sous le bon format
    int c = this.codeCommande; // On recupère le code commande, qui va nous permettre de construire l'addition
                               // au fur et a mesure
    double p = 0.0;// Le prix total a payer
    if (c % 30 == 0) { // On teste d'abords s'il y a des menus, en commençant par celui qui permet de
                       // faire le plus d'economies, c'est-a-dire le Daiquiri.
      // Cela est teste en verifiant si le code menu comprend chaque element qui le
      // compose, et comme chaque element est code par un nombre premier different des
      // autres, on peut coder le menu comme le multiple des elements.
      // Ici, on a Daiquiri=plat+complement+boisson donc
      // codeDaiquiri=codePlat*codeComplement*codeBoisson=2*3*5=30
      // Ainsi, il suffit de verifier si le code commande est divisible par 30 pour
      // savoir s'il comprend un Menu Daiquiri ou non
      c = c / 30; // On retire les elements du menu Daiquiri de la commande
      rep = rep + "- 1 Menu Daiquiri : 8.00 euros\n"; // On ajoute cela a l'addition
    }
    if (c % 10 == 0) { // Meme chose,
      c = c / 10;
      rep = rep + "- 1 Menu Mojito : 6.00 euros\n";
      p = p + 6.0;
    }
    if (c % 2 == 0) {
      c = c / 2;
      rep = rep + "- 1 Plat : 5.00 euros\n";
      p = p + 5.0;
    }
    if (c % 3 == 0) {
      c = c / 3;
      rep = rep + "- 1 Accompagnement : 2.00 euros\n";
      p = p + 2.0;
    }
    if (c % 5 == 0) {
      c = c / 5;
      rep = rep + "- 1 Dessert : 3.50 euros\n";
      p = p + 3.5;
    }
    if (c % 7 == 0) {
      c = c / 7;
      rep = rep + "- 1 Boisson : 2.00 euros\n";
      p = p + 2.0;
    }
    rep = rep + "Prix total a payer : " + p + "0 euros";
    if (this.client != null) {
      rep = rep + reductionTickets(p);
    }
    rep = rep + "\n*******************************************************";
    return rep;
  }

  /**
   * Renvoie le prix totale de la commande
   * 
   * @return prixTotal
   */
  public double getPrixTotal() {
    return prixTotal;
  }

  /**
   * Methode qui fait une petite animation lorqu'on mange
   * 
   * @throws InterruptedException
   */
  public void manger() throws InterruptedException {

    System.out.print("Crounch");
    for (int i = 1; i <= 3; i++) {
      Thread.sleep(2000);
      System.out.print(",crounch");
    }
    System.out.println();

  }

}
