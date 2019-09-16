
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

    private Promotion promo;

    /**
     * Create a new student with a given name and ID number.
     */
    public Student(String name, String firstName, String id, Promotion promo) {
        this.lastName = name;
        this.firstName = firstName;
        this.id = id;
        this.promo = promo;
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

  
}
