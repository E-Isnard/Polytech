/**
 * MotsBinaires
 */
public class MotsBinaires {

    public static String toMotsBinaires(int n) {

        if (n == 1) {
            return "1";
        } else if (n == 0) {
            return "0";
        }

        if (n % 2 == 0) {
            return toMotsBinaires(n / 2) + "0";
        }

        return toMotsBinaires(n / 2) + "1";

    }

    public static int toInt(String mb) {

        if (mb.equals("")) {
            return 0;
        }
        String lastChar = mb.substring(mb.length() - 1);

        if(lastChar.equals("0")){
            return 2*toInt(mb.substring(0, mb.length()-1));
        }
        return 2*toInt(mb.substring(0, mb.length()-1))+1;

    }

    public static void main(String[] args) {
        int n = 7;

        String septString = toMotsBinaires(n);
        System.out.println(septString);
        String mb = "111";
        System.out.println(toInt(mb));


        for(int i=0;i<=100;i++){
            System.out.println(toInt(toMotsBinaires(i)));
        }
        
    }

}