package lab3.domev2;


/**
 * Item: Un bel objet
 */
public class Item {

    protected String title;
    protected boolean gotIt;
    protected String comment;
    protected int playingTime;
    protected int year;

    Item(String theTitle, int time,int year) {
        this.title = theTitle;
        this.gotIt = false;
        this.playingTime = time;
        this.comment = "";
        this.year = year;
    }

    /**
     * Enter a comment for this DVD.
     * 
     * @param comment The comment to be entered.
     */
    void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return The comment for this DVD.
     */
    String getComment() {
        return comment;
    }

    /**
     * Set the flag indicating whether we own this DVD.
     * 
     * @param ownIt true if we own the DVD, false otherwise.
     */
    void setOwn(boolean ownIt) {
        gotIt = ownIt;
    }

    /**
     * @return true if we own a copy of this DVD.
     */
    boolean getOwn() {
        return gotIt;
    }

    int getPlayingTime(){
        return playingTime;
    }

    public String toString() {
        String s = "Item: " + title + " (" + playingTime + " mins)\n";
        if (gotIt) {
            s += "*";
        }
        s += "    " + comment;
        return s;
    }

    void print() {
        System.out.println(toString());
    }

}