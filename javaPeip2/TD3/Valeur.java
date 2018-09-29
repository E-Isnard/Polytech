public enum Valeur {
    deux(2),trois(3),quatre(4),cinq(5),sept(7),huit(8),neuf(9),dix(10),valet(10),dame(10),
    roi(10),as(20);

    Integer valeur;
    Valeur(Integer v){valeur = v;}
    Integer valeur() {return valeur;}
}
