public class NoZeroExecption extends Exception{
    
    public NoZeroExecption(){
        super();

    }

    public String toString(){
        return("Pas de zero dans l'intervalle à epsilon près");
    }
}