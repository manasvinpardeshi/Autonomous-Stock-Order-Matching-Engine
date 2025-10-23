package DS_P;
import java.util.List;
import engine.OrderMatchingEngine;
public class OrderSubmission 
{
	private OrderMatchingEngine engine;  // reference to your teammates' engine

    // --- Constructor ---
    public OrderSubmission(OrderMatchingEngine engine) {
        this.engine = engine;
    }

    // --- Submit a list of orders sequentially ---
    public void submitOrders(List<Order> orders) {
        for (Order o : orders) {
            engine.addOrder(o);      // Call their engine's method
            System.out.println("Submitted: " + o); // Optional: log to console
        }
    }

    // --- Optional: Submit orders concurrently ---
    public void submitOrdersConcurrently(List<Order> orders) {
        orders.parallelStream().forEach(o -> {
            engine.addOrder(o);
            System.out.println("Submitted: " + o);
        });
    }

}
