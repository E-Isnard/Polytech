package lab3.domev2;

/**
 * The DVD class represents a DVD object. Information about the DVD is stored
 * and can be retrieved. We assume that we only deal with movie DVDs at this
 * stage.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2008.03.30
 */
class DVD extends Item {

    private String director;

    /**
     * Constructor for objects of class DVD
     * 
     * @param theTitle    The title of this DVD.
     * @param theDirector The director of this DVD.
     * @param time        The running time of the main feature.
     */
    DVD(String theTitle, String theDirector,int year, int time) {
        super(theTitle, time,year);
        this.director = theDirector;

    }

    public String toString() {
        String s = super.toString().replaceFirst("Item", "DVD") + "\n";
        s += "The director: " + director;
        return s;

    }

    

}
