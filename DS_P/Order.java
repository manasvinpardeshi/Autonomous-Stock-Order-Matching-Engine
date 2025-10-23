package DS_P;

public class Order 
{
    // --- Private fields ---
    private int orderId;
    private String type;      // "BUY" or "SELL"
    private double price;
    private int quantity;
    private long timestamp;

    // --- Constructor with validation ---
    public Order(int orderId, String type, double price, int quantity, long timestamp) {
        if (!type.equals("BUY") && !type.equals("SELL")) {
            throw new IllegalArgumentException("Order type must be 'BUY' or 'SELL'");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }

        this.orderId = orderId;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
        this.timestamp = timestamp;
    }

    // --- Getters ---
    public int getOrderId() {
        return orderId;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setQuantity(int newQuantity) {
        if (newQuantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        this.quantity = newQuantity;
    }
    
    // --- Optional: nice string for printing ---
    @Override
    public String toString() {
        return String.format(
            "%s Order[ID:%d, Price:%.2f, Qty:%d, Timestamp:%d]",
            type, orderId, price, quantity, timestamp
        );
    }

}
