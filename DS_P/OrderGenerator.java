package DS_P;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OrderGenerator 
{
	private int nextOrderId;  // Tracks unique order IDs
    private Random rand;      // Random number generator

    // --- Constructor ---
    public OrderGenerator() {
        nextOrderId = 1;
        rand = new Random();
    }

    // --- Generate n orders for a single stock ---
    public List<Order> generateOrders(int n, String stockSymbol) {
        List<Order> orders = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String type = rand.nextBoolean() ? "BUY" : "SELL";      // Random BUY/SELL
            double price = 90 + rand.nextDouble() * 20;             // Random price 90-110
            int quantity = 1 + rand.nextInt(20);                    // Random qty 1-20
            long timestamp = System.currentTimeMillis();            // Current time

            // Create new order
            Order order = new Order(nextOrderId++, type, price, quantity, timestamp);

            orders.add(order);
        }

        return orders;
    }

    // --- Generate orders for multiple stocks ---
    public List<Order> generateOrdersMultipleStocks(int nPerStock, List<String> stockSymbols) {
        List<Order> allOrders = new ArrayList<>();
        for (String symbol : stockSymbols) {
            allOrders.addAll(generateOrders(nPerStock, symbol));
        }
        return allOrders;
    }

    // --- Optional: print orders nicely for testing ---
    public void printOrders(List<Order> orders) {
        for (Order o : orders) {
            System.out.println(o.toString()); // Uses the overridden toString()
        }
    }

}
