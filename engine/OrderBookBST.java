package engine;

import DS_P.Order;
import java.util.LinkedList;

// 🌳 Represents a single node in the BST (each price level)
class OrderTreeNode {
    double price; // Key for BST (price level)
    LinkedList<Order> orders; // Multiple orders can have the same price
    OrderTreeNode left, right;

    public OrderTreeNode(Order order) {
        this.price = order.getPrice();
        this.orders = new LinkedList<>();
        this.orders.add(order);
    }
}

// 💼 The main OrderBook class using a Binary Search Tree (BST)
public class OrderBookBST {
    private OrderTreeNode rootBuy;   // Root of the buy order tree
    private OrderTreeNode rootSell;  // Root of the sell order tree

    // Add a new order to the appropriate tree
    public void addOrder(Order order) {
        if (order.getType().equalsIgnoreCase("BUY")) {
            rootBuy = insert(rootBuy, order, true);
        } else if (order.getType().equalsIgnoreCase("SELL")) {
            rootSell = insert(rootSell, order, false);
        } else {
            System.out.println("Invalid order type: " + order.getType());
        }
    }

    // 🧩 Insert helper function for BST
    private OrderTreeNode insert(OrderTreeNode node, Order order, boolean isBuyTree) {
        if (node == null)
            return new OrderTreeNode(order);

        // For BUY: higher price = higher priority → go left
        // For SELL: lower price = higher priority → go left
        if ((isBuyTree && order.getPrice() > node.price) ||
            (!isBuyTree && order.getPrice() < node.price)) {
            node.left = insert(node.left, order, isBuyTree);
        } else if (order.getPrice() == node.price) {
            node.orders.add(order);
        } else {
            node.right = insert(node.right, order, isBuyTree);
        }
        return node;
    }

    // 🏆 Get best BUY (highest price)
    public Order peekBestBuy() {
        if (rootBuy == null) return null;
        OrderTreeNode node = rootBuy;
        while (node.left != null) node = node.left;
        return node.orders.peekFirst();
    }

    // 💰 Get best SELL (lowest price)
    public Order peekBestSell() {
        if (rootSell == null) return null;
        OrderTreeNode node = rootSell;
        while (node.left != null) node = node.left;
        return node.orders.peekFirst();
    }

    // 📜 Display all orders in sorted order
    public void displayAllOrders() {
        System.out.println("\nBUY ORDERS (High → Low):");
        displayInOrder(rootBuy);

        System.out.println("\nSELL ORDERS (Low → High):");
        displayInOrder(rootSell);
    }

    // Recursive in-order traversal
    private void displayInOrder(OrderTreeNode node) {
        if (node == null) return;
        displayInOrder(node.left);
        for (Order order : node.orders) {
            System.out.println(order);
        }
        displayInOrder(node.right);
    }
}
