package topic13_abstract_classes.solutions;

// Answers for topic13_abstract_classes/Exercises.java
public class ExercisesSolution {

    public static void main(String[] args) {
        Employee asha = new SalariedEmployee("Asha", 60_000);
        Employee ravi = new HourlyEmployee("Ravi", 40, 500);

        check(asha.monthlyPay() == 5_000, "exercise 1 SalariedEmployee.monthlyPay");
        check(ravi.monthlyPay() == 20_000, "exercise 1 HourlyEmployee.monthlyPay");
        check(asha.payslip().equals("Asha: 5000.00"), "exercise 2 payslip is shared code");
        check(ravi.payslip().equals("Ravi: 20000.00"), "exercise 2 payslip is shared code");
        System.out.println("All exercises pass");
        // new Employee("X") would not compile: you can't create an object of an abstract class
    }

    // stops the program with a clear message when an answer is wrong
    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}

abstract class Employee {
    private final String name;

    Employee(String name) {
        this.name = name;
    }

    abstract double monthlyPay();          // no body: every child class MUST write its own

    String payslip() {                     // shared: written once, and it uses each child's own monthlyPay()
        return String.format("%s: %.2f", name, monthlyPay());
    }
}

class SalariedEmployee extends Employee {
    private final double yearlySalary;

    SalariedEmployee(String name, double yearlySalary) {
        super(name);
        this.yearlySalary = yearlySalary;
    }

    @Override
    double monthlyPay() {
        return yearlySalary / 12;
    }
}

class HourlyEmployee extends Employee {
    private final int hoursThisMonth;
    private final double hourlyRate;

    HourlyEmployee(String name, int hoursThisMonth, double hourlyRate) {
        super(name);
        this.hoursThisMonth = hoursThisMonth;
        this.hourlyRate = hourlyRate;
    }

    @Override
    double monthlyPay() {
        return hoursThisMonth * hourlyRate;
    }
}
