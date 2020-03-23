package lab2.search;

import lab2.diary.*;

/**
 * Test
 */
public class Test {

    public static void main(String[] args) {
        DayOK ddo1 = new DayOK(66);
        Appointment aaa1 = new Appointment("aaa1", 1);
        Appointment aaa2 = new Appointment("aaa2", 3);
        Appointment aaa3 = new Appointment("aaa3", 2);
        System.out.println(ddo1.makeAppointment(15, aaa1));
        System.out.println(ddo1.makeAppointment(13, aaa3));

    }

}