public class Test {

    public static void main(String[] args) {
        TicketMachine oui = new TicketMachine(85,"Skynet");
        System.out.println(oui);
        oui.insertMoney(200);
        System.out.println(oui.getTicket());


    }
}