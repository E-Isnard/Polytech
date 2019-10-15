/**
 * Teacher
 */
public class Teacher {

    private String name;
    private String lastname;
    private String subject;

    public Teacher(String name, String lastname, String subject) {
        this.name = name;
        this.lastname = lastname;
        this.subject = subject;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    public String toString() {
        return(name+" "+lastname+" ("+subject+")");
    }

    public static void main(String[] args) {
        Teacher Paceco = new Teacher("Dino", "Paceco", "Le web");

        System.out.println(Paceco);
    }


}