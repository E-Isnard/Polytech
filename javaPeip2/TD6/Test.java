public class Test{


public static void main(String[] args) {
    
    Disque C = new Disque();
    Fichier f1 = new Fichier("Coucou.");
    Fichier f2 = new Fichier("Coucou !");
    Fichier f3 = new Fichier("Coucou !!");
    Repertoire d1 = new Repertoire("Repertoire");
    d1.ajouter(f3);
    //d1.ajouter(f3);
    C.ajouter(f1);
    C.ajouter(f2);
    C.ajouter(d1);
    C.lister();

}

}