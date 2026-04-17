/**
 * MyStack: Simple array-based stack structure
 *
 * NOTE: The internal array (data) is of type Object[]. Thus, Player, String, etc.
 * all reference types can be stored in this stack.
 */
public class MyStack {

    /* ---------- Fields ---------- */
    private Object[] data;   // Array holding the stack
    private int top;         // Index of the top element (empty = -1)
    private int capacity;    // Current capacity of the array

    /* ---------- Constant ---------- */
    private static final int DEFAULT_CAPACITY = 16;

    /* ---------- Constructors ---------- */

    /** Creates an empty stack with the default capacity (16) */
    public MyStack() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Creates an empty stack with the desired capacity.
     * @param capacity Initial capacity (must be >=1)
     */
    public MyStack(int capacity) {
        if (capacity <= 0)
            throw new IllegalArgumentException("Capacity must be positive.");
        this.capacity = capacity;
        this.data     = new Object[capacity];
        this.top      = -1;
    }

    /* ---------- Basic Methods ---------- */

    /**
     * Pushes an element onto the stack.
     * If the array is full, doubles the capacity (amortized O(1)).
     */
    public void push(Object item) {
        if (item == null)
            throw new IllegalArgumentException("Null item cannot be pushed.");
        if (top == capacity - 1)           // array full -> expand
            expandCapacity();
        data[++top] = item;                // increment index, then store
    }

    /**
     * Removes and returns the last added element (pop).
     * Throws IllegalStateException if the stack is empty.
     */
    public Object pop() {
        if (isEmpty())
            throw new IllegalStateException("Stack is empty.");
        Object item = data[top];
        data[top] = null;                  // avoid memory leak
        top--;
        return item;
    }

    /** Is the stack empty? */
    public boolean isEmpty() {
        return top == -1;
    }

    /* ---------- Helper ---------- */

    /** Doubles the capacity */
    private void expandCapacity() {
        capacity *= 2;
        Object[] newArr = new Object[capacity];
        System.arraycopy(data, 0, newArr, 0, top + 1);
        data = newArr;
    }
}