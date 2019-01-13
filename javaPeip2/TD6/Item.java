public abstract class Item {
    private String nom;

    public Item(String nom) {
        this.setNom(nom);

    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String toString() {
        return (nom);
    }

    public abstract void lister();

}