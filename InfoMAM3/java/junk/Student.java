import java.util.ArrayList;
import java.util.Comparator;

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
    private ArrayList<Double> notes;

    /**
     * Create a new student with a given name and ID number.
     */
    public Student(String name, String firstName, String id, Promotion promo, int ects,ArrayList<Double> notes) {
        this.lastName = name;
        this.firstName = firstName;
        this.id = id;
        this.promo = promo;
        this.ects = ects;
        this.notes = notes;
        
    }

    public Student(String name, String firstname, String id) {
        this.lastName = name;
        this.firstName = firstname;
        this.id = id;
        this.promo = null;
        this.ects = 0;
        this.notes = new ArrayList<Double>();

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

    /**
     * @return the notes
     */
    public ArrayList<Double> getNotes() {
        return notes;
    }

    public void addNote(double note){
        notes.add(note);
    }

    public double moyenne(){
        double s =0;
        double n=0;
        for(double note:notes){
            s+=note;
            n++;

        }
        return(s/n);

    }

    public double max(){
        int n = notes.size();
        double max = notes.get(0);
        for(int i=1;i<=n;i++){
            if(max<notes.get(i)){
                max = notes.get(i);
            }
        }
        return(max);
    }

    public double min(){
        int n = notes.size();
        double min = notes.get(0);
        for(int i=1;i<=n;i++){
            if(min>notes.get(i)){
                min = notes.get(i);
            }
        }
        return(min);
    }
    

    public String toString() {
        String s = "[";
        for(double note:notes){
            s+=s+note+" ";
        }
        s+="]";
        return (firstName + " " + lastName + " (" + id + ")" + s);
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
