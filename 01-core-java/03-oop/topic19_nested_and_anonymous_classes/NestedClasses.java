package topic19_nested_and_anonymous_classes;

/*
 * Topic    : Nested classes: static nested, inner, local, anonymous
 * Key idea : You can write a class INSIDE another class when it only makes sense there.
 *            Like a room inside a house - you don't build a bedroom on the road.
 *   static nested - a normal class that just lives inside another class's name.
 *                   Java's Map.Entry is one. Needs no outer object.
 *   inner         - belongs to ONE outer OBJECT and can use that object's fields.
 *                   Like a car's spark plug - it belongs to that one particular engine.
 *   local         - written inside a method, and usable only inside that method.
 *   anonymous     - a one-time class with no name, written exactly where you need it.
 * Run      : java -cp out topic19_nested_and_anonymous_classes.NestedClasses
 * Try this : Try to create a spark with 'new Engine.Spark()' - why doesn't it compile?
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

        // static nested: has no link to any Engine object. Create it as new Engine.Report(...)
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

        // inner (no 'static'): every Spark belongs to one Engine, and can read that Engine's fields
        class Spark {
            void fire() {
                starts++;                                   // this is the OUTER Engine object's field
                System.out.println("spark in " + model + ", starts = " + starts);
            }
        }

        Report report() {
            return new Report(model + " started " + starts + " times");
        }
    }

    public static void main(String[] args) {
        Engine engine = new Engine("V8");

        Engine.Spark spark = engine.new Spark();             // an inner object needs an outer object first: engine.new
        spark.fire();
        spark.fire();
        System.out.println(engine.report());

        Engine.Report report = new Engine.Report("made directly");   // static nested: no Engine object needed
        System.out.println(report);

        // local class: written inside main, so only main can use it
        class Shouter implements Greeter {
            public String greet(String name) {
                return "HELLO, " + name.toUpperCase() + "!";
            }
        }
        System.out.println(new Shouter().greet("asha"));

        // anonymous class: write the interface's method right here, use it once. No class name needed
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
