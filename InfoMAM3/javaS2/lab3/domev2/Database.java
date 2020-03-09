package lab3.domev2;

import java.util.ArrayList;

/**
 * The database class provides a facility to store CD and video objects. A list
 * of all CDs and videos can be printed to the terminal.
 * 
 * This version does not save the data to disk, and it does not provide any
 * search functions.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2008.03.30
 */
class Database {
    private ArrayList<Item> items;

    /**
     * Construct an empty Database.
     */
    Database() {
        this.items = new ArrayList<Item>();

    }

    void addItem(Item item) {
        items.add(item);
    }

    /**
     * Print a list of all currently stored CDs and DVDs to the text terminal.
     */
    void list() {

        for (Item item : items) {
            System.out.println(item);
        }
    }

    boolean contains(Item item){
        return items.contains(item);
    }

    boolean delete(Item item){
        return items.remove(item);
    }


}
