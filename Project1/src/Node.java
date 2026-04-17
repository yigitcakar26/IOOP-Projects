public class Node {
    private int customerNumber;
    private CustomerData customerData;
    private Node next;

    //No-argument constructor
    public Node() {
        this.customerNumber = 0;
        this.customerData = null;
        this.next = null;
    }

    // Constructor
    public Node(int customerNumber, CustomerData customerData) {
        this.customerNumber = customerNumber;
        this.customerData = customerData;
        this.next = null;
    }

    // Getter and setter methods
    public int getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }

    public CustomerData getCustomerData() {
        return customerData;
    }

    public void setCustomerData(CustomerData customerData) {
        this.customerData = customerData;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}