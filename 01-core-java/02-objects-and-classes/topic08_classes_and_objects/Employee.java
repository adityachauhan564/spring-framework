package topic08_classes_and_objects;

/*
 * Topic    : A well-written class (a template you can copy)
 * Key idea : A class is a blueprint. An object is one real thing made from it.
 *            Like an employee ID card format (class) and your actual ID card (object).
 *            A good simple class has 4 parts:
 *              1. private fields  - the data, hidden from outside
 *              2. a constructor   - fills the data when the object is created
 *              3. getters         - let others READ the data
 *              4. toString()      - a readable text version of the object
 *            The fields are final, so once an Employee is created it can never change.
 * Run      : java -cp out topic08_classes_and_objects.Employee
 */
public class Employee {

    private final int id;              // private = only this class can touch it. final = set once, never changed
    private final String name;

    // constructor: same name as the class, no return type. Runs once when you write "new Employee(...)"
    public Employee(int id, String name) {
        this.id = id;                  // this.id = the field of this object, id = the value passed in
        this.name = name;
    }

    // getter: a safe way for others to read the private field
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // @Override = we are replacing Java's default toString() with our own nicer version
    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + "'}";
    }

    public static void main(String[] args) {
        Employee e = new Employee(1, "Aditya");     // "new" creates the object and calls the constructor
        System.out.println(e);              // println calls toString() on its own
        System.out.println(e.getName());
    }
}
