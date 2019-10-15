/**
 * TicketMachine models a ticket machine that issues flat-fare tickets. The
 * price of a ticket is specified via the constructor. Instances will check to
 * ensure that a user only enters sensible amounts of money, and will only print
 * a ticket if enough money has been input.
 * 
 * @author David J. Barnes and Michael Kolling
 * @version 2006.03.30
 */
public class TicketMachine {

    // The price of a ticket from this machine.
    private int price;
    // The amount of money entered by a customer so far.
    private int balance;
    // The total amount of money collected by this machine.
    private int total;

    private String nomMachine;

    /**
     * Create a machine that issues tickets of the given price.
     */
    public TicketMachine(int ticketCost, String nom) {
        this.price = ticketCost;
        this.balance = 0;
        this.total = 0;
        this.nomMachine = nom;
    }

    /**
     * @Return The price of a ticket.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Return The amount of money already inserted for the next ticket.
     */
    public int getBalance() {
        return balance;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(int price) {
        this.price = price;
    }

    public String getTicket() {
        if(balance>price){
            balance = balance - price;

            return ("##############\n#   Ticket   #\n#   " + price + " cents #\n##############");
        }
        else{
            return("Pas assez d'argent dans la machine");
        }

    }


    public void getTicket2() {
        if(balance>price){
            balance = balance - price;

            System.out.println( ("##############\n#   Ticket   #\n#   " + price + " cents #\n##############"));
        }
        else{
            System.err.println(("Pas assez d'argent dans la machine"));
        }

    }



    public String toString() {
        return ("Le ticket coûte " + price + " cents");
    }

    /**
     * Receive an amount of money in cents from a customer. Check that the amount is
     * sensible.
     */
    public void insertMoney(int amount) {
        if (amount > 0) {
            balance = balance + amount;
        } else {
            System.err.println("Use a positive amount: " + amount);
        }
    }

    /**
     * Return the money in the balance. The balance is cleared.
     */
    
     public void resetMachine(){
         this.balance = 0;

     }

}
