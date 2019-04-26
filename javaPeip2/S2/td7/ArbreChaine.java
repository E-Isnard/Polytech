public class ArbreChaine<T extends Comparable<T>>
implements ArbreBinaire<T>{

    private T valeur;
    private ArbreBinaire<T> sag,sad;
    public static final ArbreChaine<T> = new ArbreChaine();



    public ArbreChaine<T>(T v){
        this.valeur = v;
        this.sag = this.sad = null;
    }

    private ArbreChaine(){
        this.valeur = null;
        this.sag = this.sad = null;
        

    }
    

}