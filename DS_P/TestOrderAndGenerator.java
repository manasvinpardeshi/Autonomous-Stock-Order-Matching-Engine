package DS_P;
import java.util.List;
public class TestOrderAndGenerator {
	 // --- Mock engine for testing ---
    static class OrderMatchingEngine {
        public void addOrder(Order o) {
            System.out.println("Engine received: " + o);
        }
    }

    public static void main(String[] args) {
        // 1️⃣ Test Order class
        System.out.println("=== Testing Order class ===");
        try {
            Order validOrder = new Order(1, "BUY", 100.5, 10, System.currentTimeMillis());
            System.out.println("Created valid order: " + validOrder);

            // Uncomment these one by one to see validation in action
            Order invalidType = new Order(2, "HOLD", 100, 10, System.currentTimeMillis());
            Order invalidPrice = new Order(3, "SELL", -50, 10, System.currentTimeMillis());
            Order invalidQuantity = new Order(4, "BUY", 100, 0, System.currentTimeMillis());

        } catch (IllegalArgumentException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }

        // 2️⃣ Test OrderGenerator class
        System.out.println("\n=== Testing OrderGenerator class ===");
        OrderGenerator generator = new OrderGenerator();

        // Single stock
        List<Order> singleStockOrders = generator.generateOrders(5, "ACME");
        System.out.println("Generated orders for ACME:");
        generator.printOrders(singleStockOrders);

        // Multiple stocks
        List<String> stocks = List.of("ACME", "XYZ");
        List<Order> multiStockOrders = generator.generateOrdersMultipleStocks(3, stocks);
        System.out.println("\nGenerated orders for multiple stocks:");
        generator.printOrders(multiStockOrders);

        // 3️⃣ Test submission to mock engine
        System.out.println("\n=== Testing submission to engine ===");
        OrderMatchingEngine engine = new OrderMatchingEngine();
        System.out.println("Submitting single stock orders:");
        for (Order o : singleStockOrders) {
            engine.addOrder(o);
        }

        System.out.println("\nSubmitting multi-stock orders:");
        for (Order o : multiStockOrders) {
            engine.addOrder(o);
        }

        // Optional: concurrent submission
        System.out.println("\nSubmitting concurrently (parallel):");
        multiStockOrders.parallelStream().forEach(engine::addOrder);
    }

}
