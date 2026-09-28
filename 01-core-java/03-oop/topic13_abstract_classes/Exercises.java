package topic13_abstract_classes;

/*
 * Exercises for topic 13. Complete the classes below this one, then run:
 *   java -cp out topic13_abstract_classes.Exercises
 * It stops at the first exercise that isn't solved yet. "All exercises pass" means you're done.
 * Stuck? See solutions/ExercisesSolution.java - but try first.
 */
public class Exercises {

    public static void main(String[] args) {
        Employee asha = new SalariedEmployee("Asha", 60_000);      // 60,000 a year
        Employee ravi = new HourlyEmployee("Ravi", 40, 500);       // 40 hours at 500

        check(asha.monthlyPay() == 5_000, "exercise 1 SalariedEmployee.monthlyPay");
        check(ravi.monthlyPay() == 20_000, "exercise 1 HourlyEmployee.monthlyPay");
        check(asha.payslip().equals("Asha: 5000.00"), "exercise 2 payslip is shared code");
        check(ravi.payslip().equals("Ravi: 20000.00"), "exercise 2 payslip is shared code");
        System.out.println("All exercises pass");
    }

    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}

// Every employee has a name and a payslip; HOW the pay is worked out depends on the kind of employee.
// 1. Make monthlyPay() abstract, and implement it in both subclasses.
// 2. payslip() is written once here and works for every subclass.
abstract class Employee {
    private final String name;

    Employee(String name) {
        this.name = name;
    }

    double monthlyPay() {                  // TODO: make this abstract (no body)
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    String payslip() {
        return String.format("%s: %.2f", name, monthlyPay());
    }
}

class SalariedEmployee extends Employee {
    SalariedEmployee(String name, double yearlySalary) {
        super(name);
        // TODO: keep the salary
    }
    // TODO: monthlyPay() = yearly salary / 12
}

class HourlyEmployee extends Employee {
    HourlyEmployee(String name, int hoursThisMonth, double hourlyRate) {
        super(name);
        // TODO: keep the hours and the rate
    }
    // TODO: monthlyPay() = hours * rate
}
