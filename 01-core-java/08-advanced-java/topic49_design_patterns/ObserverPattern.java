package topic49_design_patterns;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/*
 * Pattern  : Observer - "tell everyone who's interested when something happens"
 * Use for  : events: an order was placed, a price changed, a file arrived.
 * Key idea : the subject keeps a list of listeners and notifies each one; it doesn't know
 *            what they do. New reactions are added without touching the subject.
 * Spring   : ApplicationEventPublisher + @EventListener; Kafka topics at a larger scale (stage 05).
 * Run      : java -cp out topic49_design_patterns.ObserverPattern
 */
public class ObserverPattern {

    record OrderPlaced(int orderId, double amount) { }

    static class OrderService {
        private final List<Consumer<OrderPlaced>> listeners = new ArrayList<>();

        void onOrderPlaced(Consumer<OrderPlaced> listener) {
            listeners.add(listener);
        }

        void placeOrder(int id, double amount) {
            System.out.println("order " + id + " saved");
            OrderPlaced event = new OrderPlaced(id, amount);
            listeners.forEach(listener -> listener.accept(event));   // notify, without knowing who listens
        }
    }

    public static void main(String[] args) {
        OrderService orders = new OrderService();
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
