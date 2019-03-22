import java.util.*;

public class Test {
    public static void main(String[] args) {
        List<String> l1 = new ArrayList<String>();
        l1.add(0, "aa");
        l1.add(1, "bb");
        l1.add(2, "cc");
        l1.add(0, "zz");
        l1.remove(1);
        System.out.println(l1.size());
        System.out.println(l1);
        System.out.println(l1.get(1));
    }

}