public class Test {

    public static void main(String[] args) {
        // TicketMachine oui = new TicketMachine(85,"Skynet");
        // System.out.println(oui);
        // oui.insertMoney(200);
        // System.out.println(oui.getTicket());

        // Student s = new Student("jde","jdjdslskde","21212");
        // System.out.println(s.getLogin());

        ClockDisplay cd = new ClockDisplay();
        cd.timeTick();
        System.out.println(cd.getTime());
        cd.setTime(15, 0);
        System.out.println(cd.getTime());
        Utilisateur u = new Utilisateur("Enzo", "Isnard", "enzoisnard@gmail.com", "permanent", 2017);
        Badge b = new Badge(u, true, true);
        System.out.println(b.getNbImpression());
        
        System.out.println(b.acceder(1));
        


    }
}