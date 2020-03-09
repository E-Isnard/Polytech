package lab3.domev2;

/**
 * The CD class represents a CD object. Information about the 
 * CD is stored and can be retrieved.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2008.03.30
 */
 class VideoGame extends Item {
    private int nbJoueurs;
    private boolean isBoardGame;

    
     VideoGame(String theTitle,int year,int nbJoueurs,boolean isBoardGame)   {
        super(theTitle,0,year);
        this.nbJoueurs = nbJoueurs;
        this.isBoardGame = isBoardGame;
        
        
        
        
    }

    public String toString() {
        String s = "VideoGame: title: "+ title+"\n";
        s+="Published in "+year+"\n";
        s+="Number of players : "+nbJoueurs+"\n";
        if(isBoardGame){
            s+="Platform game";
        }
        return s;
        
    }
}
