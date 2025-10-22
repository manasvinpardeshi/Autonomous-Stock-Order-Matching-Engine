AUTONOMOUS STOCK ORDER MATCHING ENGINE (MINI PROJECT)
OVERVIEW

This project simulates the core of a stock exchange — the Order Matching Engine.
It continuously receives buy and sell orders, matches compatible ones based on price-time priority, and executes trades.
This is the fundamental mechanism used by real-world exchanges such as NSE, NYSE, and Binance.

The project demonstrates efficient use of data structures to implement a simplified but realistic market microstructure.

FEATURES

PRICE-TIME PRIORITY MATCHING

Buy orders are matched with the lowest available sell price.

Sell orders are matched with the highest available buy price.

For orders at the same price, earlier timestamps get priority (FIFO).

ORDER TYPES

Limit Orders (price-specified).

Optional: Market Orders (for immediate execution).

EFFICIENT DATA STRUCTURE DESIGN

BST or Array for sorted price levels.

Linked List or Queue for FIFO at each price level.

HashMap for fast order/trade lookup.

TRADE LOG AND ANALYTICS

Maintains chronological record of executed trades.

Allows O(1) trade lookup by ID.

Basic reporting (total volume, number of trades, etc.).

WORKFLOW

ORDER CREATION
The Market Simulator generates BUY and SELL orders containing price, quantity, type, and timestamp.

ORDER SUBMISSION
Orders are passed to the Order Matching Engine for processing.

MATCHING LOGIC

A BUY order matches if its price is greater than or equal to the best SELL price.

A SELL order matches if its price is less than or equal to the best BUY price.

Otherwise, the order is stored in the Order Book.

TRADE EXECUTION

Quantities are updated and fully matched orders are removed from the order book.

Partial fills are supported.

Each trade is recorded in the Trade Log.

TRADE LOG DISPLAY
The Trade Log displays all executed trades and analytics such as total volume and number of trades.
