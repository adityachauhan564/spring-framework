package topic02_classes_and_objects;

/*
 * Topic    : A well-written class (template to copy)
 * Key idea : private fields + constructor + getters + toString().
 *            Fields are final: an Employee can't change after it is built.
 * Run      : java -cp out topic02_classes_and_objects.Employee
 */
public class Employee {

    private final int id;
    private final String name;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + "'}";
    }

    public static void main(String[] args) {
        Employee e = new Employee(1, "Aditya");
        System.out.println(e);              // println calls toString()
        System.out.println(e.getName());
    }
}
