package topic49_design_patterns;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/*
 * Pattern  : Observer - "when something happens, tell everyone who is interested"
 * Use for  : events: an order was placed, a price changed, a file arrived.
 * Key idea : The subject keeps a list of listeners and informs each one. It doesn't know or care
 *            what they do with the news. New reactions can be added without touching the subject.
 *            Like a YouTube channel: upload one video, and every subscriber gets a notification.
 *            The channel doesn't need to know who the subscribers are.
 * Spring   : ApplicationEventPublisher + @EventListener; Kafka topics for the same idea at a bigger scale (stage 05).
 * Run      : java -cp out topic49_design_patterns.ObserverPattern
 */
public class ObserverPattern {

    record OrderPlaced(int orderId, double amount) { }

    static class OrderService {
        private final List<Consumer<OrderPlaced>> listeners = new ArrayList<>();     // the subscribers

        void onOrderPlaced(Consumer<OrderPlaced> listener) {
            listeners.add(listener);                                                  // subscribe
        }

        void placeOrder(int id, double amount) {
            System.out.println("order " + id + " saved");
            OrderPlaced event = new OrderPlaced(id, amount);
            listeners.forEach(listener -> listener.accept(event));   // notify everyone, without knowing who they are
        }
    }

    public static void main(String[] args) {
        OrderService orders = new OrderService();
        // three separate reactions to the same event - OrderService knows none of them
        orders.onOrderPlaced(e -> System.out.println("  email: thanks for order " + e.orderId()));
        orders.onOrderPlaced(e -> System.out.println("  stock: reserve items for order " + e.orderId()));
        orders.onOrderPlaced(e -> {
            if (e.amount() > 1000) {
                System.out.println("  fraud check: order " + e.orderId() + " is large");
            }
        });

        orders.placeOrder(1, 250);
        orders.placeOrder(2, 5000);
    }
}
