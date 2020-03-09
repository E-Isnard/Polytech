package lab3.domev2;

/**
 * The CD class represents a CD object. Information about the CD is stored and
 * can be retrieved.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2008.03.30
 */
class CD extends Item {

    private String artist;
    private int numberOfTracks;

    /**
     * Initialize the CD.
     * 
     * @param theTitle  The title of the CD.
     * @param theArtist The artist of the CD.
     * @param tracks    The number of tracks on the CD.
     * @param time      The playing time of the CD.
     */
    CD(String theTitle, String theArtist, int year, int tracks, int time) {
        super(theTitle, time, year);
        artist = theArtist;
        numberOfTracks = tracks;

    }

    public String toString() {

        String s = "CD: artist :    " + artist + "\n";
        s += "number of tracks: " + numberOfTracks + "\n";
        if(comment.equals("")){
            s += "title: " + title + " (" + playingTime + " mins)\n";
        }
        else{
            s += "title: " + title + " (" + playingTime + " mins)*\n";
        }
        
        s += "Published in " + year + "\n";

        s += "    " + comment;
        return s;

    }
}
