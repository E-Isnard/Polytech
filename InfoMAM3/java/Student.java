/**
 * The Student class represents a student in a student administration system.
 * 
 * @author IPA 2011-2012
 * @version 2011-2012
 */
public class Student {

    // the student's first name
    private String firstName;
    // the student's last name;
    private String lastName;
    // the student ID
    private String id;

    // student promotion
    private Promotion promo;

    private int ects;

    /**
     * Create a new student with a given name and ID number.
     */
    public Student(String name, String firstName, String id, Promotion promo, int ects) {
        this.lastName = name;
        this.firstName = firstName;
        this.id = id;
        this.promo = promo;
        this.ects = ects;
        
    }

    public Student(String name, String firstname, String id) {
        this.lastName = name;
        this.firstName = firstname;
        this.id = id;
        this.promo = null;
        this.ects = 0;

    }

    /**
     * Return the first name of this student.
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Return the last name of this student.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set a new first name for this student.
     */
    public void setFirstName(String replacementName) {
        this.firstName = replacementName;
    }

    /**
     * Return the student ID of this student.
     */
    public String getId() {
        return id;
    }

    /**
     * @param ects the ects to set
     */
    public void setEcts(int ects) {
        this.ects = ects;
    }

    /**
     * @return the ects
     */
    public int getEcts() {
        return ects;
    }

    /**
     * @return the promo
     */
    public Promotion getPromo() {
        return promo;
    }

    /**
     * @param promo the promo to set
     */
    public void setPromo(Promotion promo) {
        this.promo = promo;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String toString() {
        return (firstName + " " + lastName + " (" + id + ")");
    }

    public void addCredits(int credits) {
        this.ects = this.ects + credits;
    }

    public String getLogin() {
        if (lastName.length() > 3) {
            return (lastName.substring(0, 4) + id.substring(0, 3));
        }
        return (lastName + id.substring(0, 3));

    }

}
