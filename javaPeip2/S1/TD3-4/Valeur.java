public enum Valeur {
    deux(2), trois(3), quatre(4), cinq(5), six(6), sept(7), huit(8), neuf(9), dix(10), valet(10), dame(10), roi(10),
    as(20);

    Integer valeur;

    Valeur(Integer v) {
        valeur = v; //constructeur de valeur
    }

    Integer valeur() {
        return valeur; //Permet d'avoir la valeur des cartes 
    }
}
