package topic49_design_patterns;

/*
 * Pattern  : Factory - one place decides WHICH class to create
 * Use for  : choosing an implementation from input or configuration ("upi", "card", ...).
 * Key idea : callers ask the factory for "a Notifier for EMAIL" and get the interface type back;
 *            only the factory knows the concrete classes. Adding a type changes one method.
 * Spring   : the container is a giant factory - you ask for a type, it decides what to build.
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
            return switch (channel) {            // the ONLY place that knows the concrete classes
                case EMAIL -> new EmailNotifier();
                case SMS -> new SmsNotifier();
            };
        }
    }

    public static void main(String[] args) {
        for (Channel channel : Channel.values()) {
            Notifier notifier = NotifierFactory.create(channel);    // the caller only sees the interface
            System.out.println(notifier.send("your order has shipped"));
        }
    }
}
