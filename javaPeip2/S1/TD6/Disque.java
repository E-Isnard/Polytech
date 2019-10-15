import java.util.Vector;

public class Disque {
    private Vector<Item> lItems;

    public Disque() {
        this.lItems = new Vector<Item>();
    }

    public void ajouter(Item i) {
        this.lItems.add(i);
    }

    public void lister() {
        for (Item i : this.lItems) {
            i.lister();
        }
    }
}