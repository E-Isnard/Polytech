package src.gestionhotel;
import java.util.Scanner;
import java.util.regex.*;

/**
 * Classe qui permet de créer un client
 * 
 * @author Théo Picke et Enzo Isnard
 */
public class Client {

    private String nom, prenom, naissance, email, numero;

    /**
     * Contructeur de Client
     * 
     * @param nom       nom du client
     * @param prenom    prenom du client
     * @param naissance date de naissance du client
     * @param email     email du client
     * @param numero    numero de telephone du client
     */
    public Client(String nom, String prenom, String naissance, String email, String numero) {

        this.nom = nom;

        this.prenom = prenom;

        this.naissance = naissance;

        this.email = email;

        this.numero = numero;

    }

    /**
     * Fonction toString de client: Elle affiche un résumé des informations sur le
     * client
     * 
     * @return informations sur le client
     */
    public String toString() {

        String s = "Nom et prenom du client: " + nom + " " + prenom + "\n";
        s += "e-mail: " + email + "\n";
        s += "Telephone : " + numero + "\n";

        return s;

    }

    /**
     * 
     * Fonction qui retourne 0 si le numéro contient un code international, 1 s'il
     * commence par un 0 et -1 s'il est incorrect
     * 
     * 
     * @param num numero de telephone 
     * @return 0,1 ou -1
     */
    public static int checkNum(String num) {

        if (Pattern.matches("^\\+\\d{11}$", num)) {
            return 0;
        } else if (Pattern.matches("^0\\d{9}$", num)) {
            return 1;
        } else {
            return -1;
        }

    }

    /**
     * 
     * Fonction qui vérifie qu'une adresse mail soit dans le bon format
     * 
     * 
     * @param email
     * @return
     */
    public static boolean checkEmail(String email) {

        return Pattern.matches("^[a-zA-z0-9]+@[a-zA-z0-9]+.[a-zA-z]+$", email);
    }

    /**
     * 
     * Fonction qui vérifie qu'un nom ne soit constitué que de lettres
     * 
     * @param name
     * @return
     */
    public static boolean checkName(String name) {

        return Pattern.matches("^[a-zA-Z]+$", name);
    }

    /**
     * 
     * Fonction qui vérifie qu'une date soit dans le format dd/mm/yy ou dd/mm/yyyy
     * 
     * @param date
     * @return
     */
    public static boolean checkDate(String date) {

        return Pattern.matches("^([0-2]\\d|(3)[0-1])(\\/)(((0)\\d)|((1)[0-2]))(\\/)\\d{2}(\\d{2})?$", date);
    }

    /**
     * Fonction qui pose une question au client sur une de ses informations avec un
     * scanner
     * 
     * @param question information qu'on veut connaitre sur le client
     * @param s        Scanner pour poser la question
     * @return la réponse du client, cela peut etre "" si on pose une question sur
     *         une information qui n'existe pas.
     */
    public static String askQuestion(String question, Scanner s) {
        String reponse = "";

        if (question.equals("Prenom") || question.equals("Nom")) {

            while (!checkName(reponse)) {
                System.out.println("Veuillez ne mettre que des lettres svp");
                reponse = s.nextLine();
            }
        } else if (question == "Naissance") {
            while (!checkDate(reponse)) {
                System.out.println("Le format de la date doit etre dd/mm/yy ou dd/mm/yyyy");
                reponse = s.nextLine();
            }
        } else if (question == "Email") {
            while (!checkEmail(reponse)) {
                System.out.println("Le format doit etre user@domaine.{com,fr,...}");
                reponse = s.nextLine();
            }
        } else if (question == "Numero") {
            System.out.println("Le format est +CC CCCCCCCCC(sans espace) ou 0CCCCCCCCC");
            while (checkNum(reponse) == -1) {
                reponse = s.nextLine();
            }
        }

        return reponse;
    }

}