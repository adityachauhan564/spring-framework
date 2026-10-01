package topic13_abstract_classes;

/*
 * Exercises for topic 13.
 * How to use:
 *   - Complete the classes written BELOW this one. Fill in every "TODO".
 *   - Then run:  java -cp out topic13_abstract_classes.Exercises
 *   - It stops at the first exercise that is not solved yet.
 *   - When you see "All exercises pass", you are done.
 * Stuck? See solutions/ExercisesSolution.java - but please try yourself first.
 */
public class Exercises {

    public static void main(String[] args) {
        Employee asha = new SalariedEmployee("Asha", 60_000);      // fixed salary: 60,000 a year
        Employee ravi = new HourlyEmployee("Ravi", 40, 500);       // paid per hour: 40 hours at 500 an hour

        check(asha.monthlyPay() == 5_000, "exercise 1 SalariedEmployee.monthlyPay");
        check(ravi.monthlyPay() == 20_000, "exercise 1 HourlyEmployee.monthlyPay");
        check(asha.payslip().equals("Asha: 5000.00"), "exercise 2 payslip is shared code");
        check(ravi.payslip().equals("Ravi: 20000.00"), "exercise 2 payslip is shared code");
        System.out.println("All exercises pass");
    }

    // stops the program with a clear message when an answer is wrong
    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}

// Every employee has a name and gets a payslip. But HOW the pay is calculated depends on the type of employee:
// a full-time employee gets a fixed salary, a part-timer is paid by the hour.
// 1. Make monthlyPay() abstract, then write it in both child classes.
// 2. payslip() is written only once, here, and it works for every child class.
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
