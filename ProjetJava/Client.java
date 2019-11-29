import java.util.regex.*;

/**
 * Client
 */
public class Client {

    private String nom, prenom, naissance, email, numero;

    public Client(String nom, String prenom, String naissance, String email, String numero) {
        /*
         * Contructeur de Client: Si les attributs ne sont pas dans le bon format on ne
         * les affecte pas
         * 
         */
        if (checkName(nom)) {
            this.nom = nom;
        }
        if (checkName(prenom)) {
            this.prenom = prenom;
        }
        if (checkDate(naissance)) {
            this.naissance = naissance;
        }
        if (checkEmail(email)) {
            this.email = email;
        }
        if (checkNum(numero) != -1) {
            this.numero = numero;
        }

    }

    public String toString() {
        String s = "";
        s += "Nom et prénom du client: " + nom + " " + prenom + "\n";
        s += "e-mail: " + email + "\n";
        s += "Telephone : " + numero + "\n";

        return s;

    }

    public static int checkNum(String num) {
        /*
         * Fonction qui retourne 0 si le numéro contient un code international, 1 s'il
         * commence par un 0 et -1 s'il est incorrect
         * 
         */
        if (Pattern.matches("^\\+\\d{11}$", num)) {
            return 0;
        } else if (Pattern.matches("^0\\d{9}$", num)) {
            return 1;
        } else {
            return -1;
        }

    }

    public static boolean checkEmail(String email) {
        /*
         * Fonction qui vérifie qu'une adresse mail soit dans le bon format
         * 
         */
        return Pattern.matches("^[a-zA-z0-9]+@[a-zA-z0-9]+.[a-zA-z]+$", email);
    }

    public static boolean checkName(String name) {
        /*
         * Fonction qui vérifie qu'un nom ne soit constitué que de lettres
         */
        return Pattern.matches("^[a-zA-z]+$", name);
    }

    public static boolean checkDate(String date) {
        /*
         * Fonction qui vérifie qu'une date soit dans le format dd/mm/yy ou dd/mm/yyyy
         */
        return Pattern.matches("^([0-2]\\d|(3)[0-1])(\\/)(((0)\\d)|((1)[0-2]))(\\/)\\d{2}(\\d{2})?$", date);
    }

    public static void main(String[] args) {
        String num = "+33651034089";
        String date = "18/08/99";
        String email = "enzoisnard@gmail.com";
        Client c = new Client("isnard", "enzo", "18/08/1999", "enzoisnard@gmail.com", "0651034089");
        int n = checkNum(num);
        boolean b = checkEmail(email);
        boolean b2 = checkDate(date);
        System.out.println(n);
        System.out.println(c);
        System.out.println(b);
        System.out.println(b2);
    }

}