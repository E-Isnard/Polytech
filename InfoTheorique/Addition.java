import java.lang.Thread;
/**
 * Addition
 */
public class Addition {

    public static String plusUn(String mb) {

        if (mb.equals("0")) {
            return "0";
        } else if (mb.equals("1")) {
            return "10";
        }
        String subString = mb.substring(0, mb.length() - 1);
        if (mb.endsWith("0")) {

            return subString + "1";

        }
        return plusUn(subString) + "0";

    }

    public static String add(String mb1, String mb2) {
        if(mb1.equals("0")){
            return mb2;
        }
        else if(mb2.equals("0")){
            return mb1;
        }
        else if(mb1.equals("1")){
            return plusUn(mb2);
        }
        else if(mb2.equals("1")){
            return plusUn(mb1);
        }
        String subString1 = mb1.substring(0, mb1.length() - 1);
        String subString2 = mb2.substring(0, mb2.length() - 1);
        if (mb1.endsWith("1") && mb2.endsWith("1")) {
            return plusUn(add(subString1, subString2))+ "0";
        } else if (mb1.endsWith("0") && mb2.endsWith("0")) {
            return add(subString1, subString2) + "0";
        }
        return add(subString1,subString2)+"1";
    }

    public static String mult(String mb1,String mb2){
        if(mb1.equals("0") || mb2.equals("0") ){
            return "0";
        }
        else if(mb1.equals("1")){
            return mb2;
        }
        else if(mb2.equals("1")){
            return mb1;
        }
        String subString = mb2.substring(0,mb2.length()-1);
        if(mb2.endsWith("0")){

            return mult(mb1, subString)+"0";

        }
        return add(mult(mb1, subString)+"0",mb1);
    }

    public static String puissance(String mb1,String mb2){

        if(mb2.equals("0")){
            return "1";
        }
        else if(mb1.equals("0")){
            return "0";
        }
        


    }



    public static void main(String[] args) throws InterruptedException {
        String mb1 = "1";
        String mb2 = "1";
        System.out.println(add(mb1, mb2));
        System.out.println(mult("10", "10"));
        for(int i=0;i<=100;i++){
            for(int j=0;j<=100;j++){
                Thread.sleep(100);
                System.out.println("Multiplication");
                System.out.println(i*j);
                System.out.println(MotsBinaires.toInt(mult(MotsBinaires.toMotsBinaires(i),MotsBinaires.toMotsBinaires(j))));
            }
        }

    }

}