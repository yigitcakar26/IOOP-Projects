# Introduction to Object-Oriented Programming Projects

Two Java projects developed for the **Introduction to OOP** course at Ege University.

## Project 1 – Customer Rating Analysis System

A console-based application that manages customer data and product ratings using a custom **Linked List** implementation.

**Features:**
- Read customer data from file
- Add new customers
- Compute average ratings per product (overall, by country, by profession)
- Print customer list and 2D ratings matrix

**Key Concepts:** Linked Lists, File I/O, Arrays

## Project 2 – Player Information Management System

A menu-driven application for managing football player records using custom **Doubly Linked List** and **Stack** data structures.

**Features:**
- Load/save player data from/to file
- Add, search, and delete players
- Sort and display records (A-Z / Z-A)
- Stack-based operations

**Key Concepts:** Doubly Linked Lists, Stacks, File I/O, Sorting

## Project Structure

```
Project1/
├── src/
│   ├── Main.java           # Entry point
│   ├── CustomerData.java   # Customer model
│   ├── LinkedList.java     # Custom linked list
│   ├── Node.java           # List node
│   └── Firma.txt           # Sample data
└── docs/
    └── Rapor.pdf
Project2/
├── src/
│   ├── Main.java               # Entry point
│   ├── Player.java             # Player model
│   ├── DoublyLinkedList.java   # Custom doubly linked list
│   ├── MyStack.java            # Custom stack
│   ├── Date.java               # Date utility
│   ├── Player*Util.java        # Utility classes
│   ├── players.txt             # Input data
│   └── players_updated.txt     # Output data
└── docs/
    ├── Project Report.pdf
    └── results.xlsx
```

## How to Run

```bash
cd ProjectX/src/
javac *.java
java Main
```

## Authors

Ege University - Computer Engineering, 2024-2025
