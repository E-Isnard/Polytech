package lab7.basesurnom;
import lab7.basesurnom.*;

public class TestSurnom {

	public static void main(String[] s) {

		ServeurSurnom ss = new ServeurSurnom();
		Professeur h = new Professeur("Collavizza", "Helen", "I3S");
		Professeur h1 = new Professeur("Collavizza", "Helen", "I3S");
		System.out.println(h.equals(h1));
		System.out.println(h.hashCode());
		System.out.println(h1.hashCode());


		ss.addSurnom(h, "yogi");
		System.out.println(ss.getSurnomPublic(h));
		System.out.println(ss.getSurnomPublic(h1));


		
	}
}
