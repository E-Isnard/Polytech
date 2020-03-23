package lab2.diary;

/**
 * Represent a week's worth of Days.
 * 
 * @author David J. Barnes and Michael Kolling
 * @version 2001.09.14
 */
public class Week {
    // Represent a Week as a set of Monday to Friday days.
    public static final int BOOKABLE_DAYS_PER_WEEK = 5;
    
    // A week number within a particular year (0-52).
    private final int weekNumber;
    private final DayOK[] days;

    /**
     * Constructor for objects of class Week.
     * @param weekNumber The week number (0-52).
     */
    public Week(int weekNumber)   {
        this.weekNumber = weekNumber;
        days = new DayOK[BOOKABLE_DAYS_PER_WEEK];
        int dayInYear = weekNumber * 7 + 1;
        for(int day = 0; day < BOOKABLE_DAYS_PER_WEEK; day++) {
            days[day] = new DayOK(dayInYear);
            dayInYear++;
        }
    }

    /**
     * Print a list of appointments for this week on standard output.
     */
    public void showAppointments()  {
        System.out.println("*** Week " + weekNumber + " ***");
        for(int i = 0; i < BOOKABLE_DAYS_PER_WEEK; i++) {
            days[i].showAppointments();
        }
    }

    /**
     * @param dayInWeek Which day (1..BOOKABLE_DAYS_PER_WEEK).
     * @return The DayOK representing that day number, or null if
     *         dayInWeek is out of range.
     */
    public DayOK getDay(int dayInWeek)   {
        if(dayInWeek >= 1 && dayInWeek <= BOOKABLE_DAYS_PER_WEEK) {
            return days[dayInWeek - 1];
        }
        else {
            return null;
        }
    }

    /**
     * @return The week number (0-52).
     */
    public int getWeekNumber()   {
        return weekNumber;
    }
}
