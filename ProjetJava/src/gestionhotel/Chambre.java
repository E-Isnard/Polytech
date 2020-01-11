package src.gestionhotel;
import java.lang.Thread;
import java.time.LocalDateTime; // Import the LocalDateTime class
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class

/**
 * Classe qui permet de créer une chambre pour un client
 * 
 * @author Théo Picke et Enzo Isnard
 * 
 */

public class Chambre {
  private int id; // Le numero de la chambre
  private String type; // Le type de Chambre
  private Double prix; // Le prix de la chambre
  private Client client; // Le client a qui est attribuee la chambre
  private int nbNuits; // Nombre de nuit qu'on passe dans la chambre

  /**
   * Constructeur qui cree une chambre libre de numero i et de type
   * 
   * @param i       id le la chambre
   * @param t       type de la chambre
   * @param nbNuits nombre de nuits qu'on passe dans la chambre
   */
  public Chambre(int i, String t, int nbNuits) {
    this.id = i;
    this.type = t;
    this.nbNuits = nbNuits;
    this.prix = defPrix(t) * nbNuits; // On attribue le prix en fonction du type de la chambre
    // Client sera egal a null. On designera une Chambre libre comme une Chambre
    // avec un client egal a null
  }

  /**
   * Contructeur qui cree une chambre d'id i , de type t et avec client c
   * 
   * @param i       id de la chambre
   * @param t       type de la chambre
   * @param c       client d la chambre
   * @param nbNuits nombre de nuits qu'on passe dans a chambre
   */
  public Chambre(int i, String t, Client c, int nbNuits) { // Constructeur qui cree une chambre occupee par le Client c,
                                                           // de numero i
    // et de type t
    this.id = i;
    this.type = t;
    this.nbNuits = nbNuits;
    this.prix = defPrix(t) * nbNuits; // On attribue automatiquement le prix en fonction du type de la chambre
    this.setClient(c); // La fonction setClient verifie si la chambre est du bon type, si non la
                       // chambre sera libre et un message d'erreur sera envoye
  }

  /**
   * Renvoie le prix de la chambre en fonction du type de chambre t
   * 
   * @param t type de la chambre
   * @return le prix en fonction du type de chambre
   */
  public Double defPrix(String t) {
    Double rep = 0.0;
    if (t.equals("simple")) { 
      rep = 124.70;
    } else if (t.equals("double")) {
      rep = 137.60;
    } else if (t.equals("triple")) {
      rep = 163.40;
    } else if (t.equals("deluxe")) {
      rep = 189.20;
    } else { // Si le type n'appartient pas a la liste, on renvoie une erreur
      System.out.println(
          "Erreur : Le prix de la chambre n'a pu etre attribue, car le type de Chambre entre en paramètre ne fait pas partie de la liste suivante : {'simple','double','triple','deluxe'}. Le prix a ete place a 0.0 euros");
      return 0.0; // En cas d'erreur sur le type de Chambre, on renvoie 0.0 car il ne serait pas
                  // logique de faire payer une Chambre d'un type inconnu.
    }
    rep = rep * 1.14; // Prise en compte de la TVA
    rep = Math.floor(rep * 100) / 100; // Renvoie le prix arrondi par defaut au centime près
    return rep;
  }

  /**
   * attribut le client c a la chambre
   * 
   * @param c client
   */
  public void setClient(Client c) { // Permet de changer le client pour le client c, cette fonction est egalement
                                    // utilisee dans affecterChambre et libererChambre presentes ci-dessous
    if (prix == 0.0) { // Si le prix de la chambre vaut 0.0, c'est-a-dire si le type de chambre est
                       // incorrect, on ne realise pas l'operation et on envoie un message d'erreur
      System.out.println("Erreur : Le changement de client de la chambre " + this.id
          + " n'a pu etre realise, car le type de chambre est incorrect. Veuillez changer le type de chambre en premier lieu.");
    } else { // Sinon, le changement a lieu normalement
      this.client = c;
    }
  }

  /**
   * setter de id
   * 
   * @param i id
   */
  public void setID(int i) {
    this.id = i;
  }

  /**
   * Permet de changer le type de Chambre pour t, et met a jour automatiquement le
   * prix de la Chambre. Verifie si le type est correct, sinon libère la chambre
   * 
   * @param t type de la chambre
   */
  public void setType(String t) {
    this.type = t;
    this.prix = defPrix(t);
    this.setClient(this.client); // Cette commande sert juste a verifier si le type de chambre est correct, sinon
                                 // il rend la chambre libre et renvoie un message d'erreur
  }

  /**
   * Renvoie la fiche de reservation de la chambre. Est utilise pour afficher la
   * Chambre sur le terminal
   * 
   * @return reçu de la chambre
   */
  public String toString() {
    LocalDateTime myDateObj = LocalDateTime.now();
    String t = "******************* HOTEL BLUE BAY CARACAO *******************\nDate de reservation : ";
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("MMM dd, yyyy hh:mm:ss a");
    t = t + myDateObj.format(myFormatObj) + "\n"; // Renvoie l'heure sous le bon format
    if (client == null) {
      t = t + "Chambre libre\n"; // Si la chambre est libre, il est inutile d'afficher le client
    } else {
      t = t + client.toString(); // Voir la fonction toString presente dans la classe Client, qui renvoie une
                                 // chaîne de charactère sous la bonne forme
    }
    t = t + "Chambre n0 : " + id + "   Type de chambre : " + type + "  Nombre de nuits: " + nbNuits
        + "\n**************************************************************";
    return t;
  }

  /**
   * Affecte a la chambre le Client c, et imprime un reçu de la reservation
   * 
   * @param c client
   */
  public void affecterChambre(Client c) {
    this.setClient(c);// setClient verifie si la Chambre est du bon type avant de realiser l'operation
    System.out.println(this);// On renvoie la fiche de reservation de la Chambre, ainsi on est assures que
                             // toute Chambre affectee est reservee dans l'hôtel
  }

  /**
   * On retire le client affecte a la chambre, qui devient libre
   */
  public void libererChambre() {
    this.setClient(null);
  }

  /**
   * Fonction qui permet de dormir.On rajoute des z puis on les effaces avec
   * System.out.print("\b\b\b\b\b \b\b\b\b\b");
   * 
   * @throws InterruptedException
   */
  public static void dodo() throws InterruptedException {

    int i = 0;
    // 14 a ete choisi arbitrairement
    while (i <= 14) {

      if (i % 5 == 0) {
        Thread.sleep(250);// Permet à ce que la transition se fasse mieux quand on arrive à 4 z
        System.out.print("Z");
      } else {
        System.out.print("z");
      }
      Thread.sleep(500);
      if (i % 5 == 4) {
        System.out.print("\b\b\b\b\b     \b\b\b\b\b");
      }
      i++;

    }
  }
  /**
   * getter de prix
   * @return prix
   */
  public Double getPrix() {
    return prix;
  }

}
