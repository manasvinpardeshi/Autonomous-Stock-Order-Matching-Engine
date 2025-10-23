package engine;

import DS_P.Order;

public class OrderMatchingEngine {

    private OrderBookBST orderBook;

    // --- Constructor ---
    public OrderMatchingEngine(OrderBookBST orderBook) {
        this.orderBook = orderBook;
    }

    // --- Add a new order and try to match it ---
    public void addOrder(Order order) {
        System.out.println("\n📥 New Order Received: " + order);

        if (order.getType().equalsIgnoreCase("BUY")) {
            matchBuyOrder(order);
        } else if (order.getType().equalsIgnoreCase("SELL")) {
            matchSellOrder(order);
        } else {
            System.out.println("❌ Invalid order type: " + order.getType());
        }
    }

    // --- Match BUY order with best SELL orders ---
    private void matchBuyOrder(Order buyOrder) {
        while (true) {
            Order bestSell = orderBook.peekBestSell();
            if (bestSell == null) break;                     // No sell orders
            if (buyOrder.getPrice() < bestSell.getPrice()) break; // No price match

            // Execute trade
            int tradeQty = Math.min(buyOrder.getQuantity(), bestSell.getQuantity());
            double tradePrice = bestSell.getPrice();

            System.out.println("✅ TRADE EXECUTED: " +
                    "BUY " + buyOrder.getOrderId() +
                    " ↔ SELL " + bestSell.getOrderId() +
                    " @ " + tradePrice + " Qty: " + tradeQty);

            // Adjust quantities using setter
            int remainingBuy = buyOrder.getQuantity() - tradeQty;
            int remainingSell = bestSell.getQuantity() - tradeQty;

            buyOrder.setQuantity(remainingBuy);
            bestSell.setQuantity(remainingSell);

            // Remove completed orders
            if (remainingSell <= 0) removeSellOrder(bestSell);
            if (remainingBuy <= 0) return;
        }

        // Add leftover buy order to order book
        orderBook.addOrder(buyOrder);
    }

    // --- Match SELL order with best BUY orders ---
    private void matchSellOrder(Order sellOrder) {
        while (true) {
            Order bestBuy = orderBook.peekBestBuy();
            if (bestBuy == null) break;                      // No buy orders
            if (sellOrder.getPrice() > bestBuy.getPrice()) break; // No price match

            // Execute trade
            int tradeQty = Math.min(sellOrder.getQuantity(), bestBuy.getQuantity());
            double tradePrice = bestBuy.getPrice();

            System.out.println("✅ TRADE EXECUTED: " +
                    "BUY " + bestBuy.getOrderId() +
                    " ↔ SELL " + sellOrder.getOrderId() +
                    " @ " + tradePrice + " Qty: " + tradeQty);

            // Adjust quantities using setter
            int remainingSell = sellOrder.getQuantity() - tradeQty;
            int remainingBuy = bestBuy.getQuantity() - tradeQty;

            sellOrder.setQuantity(remainingSell);
            bestBuy.setQuantity(remainingBuy);

            // Remove completed orders
            if (remainingBuy <= 0) removeBuyOrder(bestBuy);
            if (remainingSell <= 0) return;
        }

        // Add leftover sell order to order book
        orderBook.addOrder(sellOrder);
    }

    // --- Placeholder remove functions (for logs) ---
    private void removeBuyOrder(Order order) {
        System.out.println("📤 Removed BUY order: " + order.getOrderId());
    }

    private void removeSellOrder(Order order) {
        System.out.println("📤 Removed SELL order: " + order.getOrderId());
    }
}
