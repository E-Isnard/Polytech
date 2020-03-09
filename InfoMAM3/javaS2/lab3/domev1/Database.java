package lab3.domev1;

import java.util.ArrayList;

/**
 * The database class provides a facility to store CD and video 
 * objects. A list of all CDs and videos can be printed to the
 * terminal.
 * 
 * This version does not save the data to disk, and it does not
 * provide any search functions.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2008.03.30
 */
 class Database {
    private ArrayList<CD> cds;
    private ArrayList<DVD> dvds;

    /**
     * Construct an empty Database.
     */
     Database()  {
        cds = new ArrayList<CD>();
        dvds = new ArrayList<DVD>();
    }

    /**
     * Add a CD to the database.
     * @param theCD The CD to be added.
     */
     void addCD(CD theCD)  {
        cds.add(theCD);
    }

    /**
     * Add a DVD to the database.
     * @param theDVD The DVD to be added.
     */
     void addDVD(DVD theDVD)  {
        dvds.add(theDVD);
    }

    /**
     * Print a list of all currently stored CDs and DVDs to the
     * text terminal.
     */
     void list()  {
        // print list of CDs
        for(CD cd : cds) {
        	System.out.println(cd);
        }
        // print list of DVDs
        for(DVD dvd : dvds) {
            System.out.println(dvd);
        }
    }
}
