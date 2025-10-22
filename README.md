# Autonomous-Stock-Order-Matching-Engine
Match buy/sell orders using price-time priority, simulate market microstructure.

 Autonomous Stock Order Matching Engine (Mini Project)
Overview

This project simulates the core of a stock exchange — the Order Matching Engine — which continuously receives buy and sell orders, matches them based on price-time priority, and executes trades.

It models how real-world exchanges like NSE, NYSE, or Binance process orders at high speed using efficient data structures and matching algorithms.

Features

Price-Time Priority Matching

Buy orders match with the lowest sell price and vice versa.

Earlier orders at the same price have higher priority (FIFO).

Order Types

Limit orders (price-specified).

Optional: Market orders.

Efficient Data Structure Design

BST / Array for sorted price levels.

Linked List / Queue for FIFO orders at each price.

HashMap for fast lookup of orders/trades.

Trade Logging and Analytics

Chronological record of executed trades.

Fast retrieval of trades using HashMap.

Basic analytics: total traded volume, number of trades, etc.
