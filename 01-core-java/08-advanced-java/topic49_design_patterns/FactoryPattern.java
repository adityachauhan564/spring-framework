package topic49_design_patterns;

/*
 * Pattern  : Factory - ONE place decides which class to create
 * Use for  : picking the right implementation from some input or setting ("upi", "card", ...).
 * Key idea : The caller just asks the factory: "give me a Notifier for EMAIL".
 *            It gets back the interface type, and never sees the real class.
 *            Only the factory knows the real classes - so adding a new type means changing one method.
 *            Like ordering at a restaurant counter: you say "one masala dosa", and the kitchen
 *            decides which cook makes it. You never walk into the kitchen.
 * Spring   : The Spring container is one giant factory - you ask for a type, it decides what to build.
 * Run      : java -cp out topic49_design_patterns.FactoryPattern
 */
public class FactoryPattern {

    interface Notifier {
        String send(String message);
    }

    static class EmailNotifier implements Notifier {
        public String send(String message) {
            return "email: " + message;
        }
    }

    static class SmsNotifier implements Notifier {
        public String send(String message) {
            return "sms: " + message;
        }
    }

    enum Channel { EMAIL, SMS }

    static class NotifierFactory {
        static Notifier create(Channel channel) {
            return switch (channel) {            // the ONLY place in the code that knows the real classes
                case EMAIL -> new EmailNotifier();
                case SMS -> new SmsNotifier();
            };
        }
    }

    public static void main(String[] args) {
        for (Channel channel : Channel.values()) {
            Notifier notifier = NotifierFactory.create(channel);    // the caller only ever sees the interface
            System.out.println(notifier.send("your order has shipped"));
        }
    }
}
