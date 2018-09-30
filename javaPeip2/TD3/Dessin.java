import PaD.*;

public class Dessin {
    public static void main(String[] args) {
        PlancheADessin pad = new PlancheADessin();
        double milieu = pad.getLargeur() / 2;
        Dessinable titre = new Texte(milieu - 60, 10, " Mon Bonhomme ");
        Dessinable tete = new CerclePlein(milieu, 80, 60, PlancheADessin.ROUGE);
        Dessinable cou = new Ligne(milieu, 110, milieu, 170);
        Dessinable corps = new RectanglePlein(milieu - 40, 170, 80, 100, PlancheADessin.VERT);
        pad.ajouter(titre);
        pad.ajouter(tete);
        pad.ajouter(cou);
        pad.ajouter(corps);
        // ou bien pad.ajouter(titre, t^ete, cou, corps);
    }
}