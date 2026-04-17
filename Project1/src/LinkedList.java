public class LinkedList {
    private Node head;

    // Constructor
    public LinkedList() {
        this.head = null;   // Setting head point to null
    }

    // Method that adds a new customer to the linked list
    public void addNode(int customerNumber, CustomerData customerData) {
        // Creating a new "Node" object
        Node newNode = new Node(customerNumber, customerData);
        // If the list is empty or the new customer's number is smaller than the head's number
        if (head == null || customerNumber < head.getCustomerNumber()) {
            // Setting the new customer to the head and next pointer starts to point previous head
            newNode.setNext(head);
            head = newNode;
        } else {
            // If customer number greater than head's, traverse the list until finding the right position
            Node current = head;
            while (current.getNext() != null && current.getNext().getCustomerNumber() < customerNumber) {
                current = current.getNext();
            }
            newNode.setNext(current.getNext());
            current.setNext(newNode);
        }
    }

    // Method that prints the linked list
    public void printList() {
        Node current = head;
        while (current != null) {
            System.out.println("Customer Number: " + current.getCustomerNumber());
            System.out.println(current.getCustomerData());
            current = current.getNext();
        }
    }

    // getter method for the head
    public Node getHead() {
        return head;
    }
}
