package topic19_nested_and_anonymous_classes;

/*
 * Topic    : Nested classes: static nested, inner, local, anonymous
 * Key idea : a class can live inside another class when it only makes sense there.
 *   static nested - an ordinary class, namespaced by its outer class (Map.Entry is one)
 *   inner         - belongs to an OUTER OBJECT and can use its fields
 *   local         - declared inside a method, used only there
 *   anonymous     - a one-off class without a name, written right where it's needed
 * Run      : java -cp out topic19_nested_and_anonymous_classes.NestedClasses
 * Try this : try to create an Engine.Spark with 'new Engine.Spark()' - why doesn't it compile?
 */
public class NestedClasses {

    interface Greeter {
        String greet(String name);
    }

    static class Engine {
        private final String model;
        private int starts;

        Engine(String model) {
            this.model = model;
        }

        // static nested: no link to any Engine object; created as new Engine.Report(...)
        static class Report {
            private final String text;

            Report(String text) {
                this.text = text;
            }

            @Override
            public String toString() {
                return "Report: " + text;
            }
        }

        // inner (not static): every Spark belongs to one Engine and can read its fields
        class Spark {
            void fire() {
                starts++;                                   // the OUTER object's field
                System.out.println("spark in " + model + ", starts = " + starts);
            }
        }

        Report report() {
            return new Report(model + " started " + starts + " times");
        }
    }

    public static void main(String[] args) {
        Engine engine = new Engine("V8");

        Engine.Spark spark = engine.new Spark();             // an inner object needs an outer object
        spark.fire();
        spark.fire();
        System.out.println(engine.report());

        Engine.Report report = new Engine.Report("made directly");   // static nested: no Engine needed
        System.out.println(report);

        // local class: only visible inside main
        class Shouter implements Greeter {
            public String greet(String name) {
                return "HELLO, " + name.toUpperCase() + "!";
            }
        }
        System.out.println(new Shouter().greet("asha"));

        // anonymous class: implement the interface inline, once
        Greeter polite = new Greeter() {
            @Override
            public String greet(String name) {
                return "Good morning, " + name;
            }
        };
        System.out.println(polite.greet("Ravi"));
        System.out.println("Topic 20 shows how a lambda replaces this anonymous class in one line.");
    }
}
