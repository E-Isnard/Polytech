package lab3.domev1;

/**
 * The CD class represents a CD object. Information about the 
 * CD is stored and can be retrieved.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2008.03.30
 */
 class CD {
    private String title;
    private String artist;
    private int numberOfTracks;
    private int playingTime;
    private boolean gotIt;
    private String comment;

    /**
     * Initialize the CD.
     * @param theTitle The title of the CD.
     * @param theArtist The artist of the CD.
     * @param tracks The number of tracks on the CD.
     * @param time The playing time of the CD.
     */
     CD(String theTitle, String theArtist, int tracks, int time)   {
        title = theTitle;
        artist = theArtist;
        numberOfTracks = tracks;
        playingTime = time;
        gotIt = false;
        comment = "<no comment>";
    }

    /**
     * Enter a comment for this CD.
     * @param comment The comment to be entered.
     */
     void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return The comment for this CD.
     */
     String getComment() {
        return comment;
    }

    /**
     * Set the flag indicating whether we own this CD.
     * @param ownIt true if we own the CD, false otherwise.
     */
     void setOwn(boolean ownIt) {
        gotIt = ownIt;
    }

    /**
     * @return true if we own a copy of this CD.
     */
     boolean getOwn() {
        return gotIt;
    }

    public String toString() {
        String s = "CD: " + title + " (" + playingTime + " mins)\n";
        if(gotIt) {
            s += "*";
        }
        s += "    " + artist + "\n";
        s +="    tracks: " + numberOfTracks+ "\n";
        s +="    " + comment;
        return s;
    }
}
