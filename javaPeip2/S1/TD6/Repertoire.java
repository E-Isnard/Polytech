import java.util.Vector;
import java.lang.String;

public class Repertoire extends Item {
    private Vector<Item> lItems;

    public Repertoire(String nom) {
        super(nom);
        this.lItems = new Vector<Item>();
    }

    public void lister() {
        System.out.println("[" + this + "]");
        for (Item i : this.lItems) {
            System.out.print("|--> ");
            i.lister();
        }
    }

    public void ajouter(Item i) {
        boolean b = false;
        for (Item j : lItems) {
            if (j.getNom() == i.getNom()) {
                b = true;
            }
        }
        if (!b) {
            lItems.add(i);
        } else {
            System.err.println(String.format("L'item %s est déjà présent dans le repertoire %s",i.getNom(),this.getNom()));
        }
    }

}