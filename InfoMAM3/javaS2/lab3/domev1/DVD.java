package lab3.domev1;

/**
 * The DVD class represents a DVD object. Information about the 
 * DVD is stored and can be retrieved. We assume that we only deal 
 * with movie DVDs at this stage.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2008.03.30
 */
 class DVD 
{
    private String title;
    private String director;
    private int playingTime; // playing time of the main feature
    private boolean gotIt;
    private String comment;

    /**
     * Constructor for objects of class DVD
     * @param theTitle The title of this DVD.
     * @param theDirector The director of this DVD.
     * @param time The running time of the main feature.
     */
     DVD(String theTitle, String theDirector, int time)
    {
        title = theTitle;
        director = theDirector;
        playingTime = time;
        gotIt = false;
        comment = "<no comment>";
    }

    /**
     * Enter a comment for this DVD.
     * @param comment The comment to be entered.
     */
     void setComment(String comment)
    {
        this.comment = comment;
    }

    /**
     * @return The comment for this DVD.
     */
     String getComment()
    {
        return comment;
    }

    /**
     * Set the flag indicating whether we own this DVD.
     * @param ownIt true if we own the DVD, false otherwise.
     */
     void setOwn(boolean ownIt)
    {
        gotIt = ownIt;
    }

    /**
     * @return true if we own a copy of this DVD.
     */
     boolean getOwn()
    {
        return gotIt;
    }

    public String toString(){
        String s = "DVD: " + title + " (" + playingTime + " mins)\n";
        if(gotIt) {
            s += "*";
        }
        s += "    " + director + "\n";
        s +="    " + comment;
        return s;
    }
    
     void print() {
        System.out.println(toString());
     }
}
