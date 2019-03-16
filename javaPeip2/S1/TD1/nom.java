import java.util.Scanner;

public class nom{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Votre prénom : ");
        String prénom = sc.nextLine();
        System.out.println("Votre âge: ");
        int age = sc.nextInt();
        sc.close();
        System.out.println(prénom + ", vous êtes né en " + (2018-age));
    }
}