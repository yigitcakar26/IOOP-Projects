import java.util.NoSuchElementException;

/**
 * Doubly linked list:
 * - Stores data of type Player.
 * - With head and tail references,
 *   --> traversal from start (A→Z) and end (Z→A) is possible.
 */
public class DoublyLinkedList {

    /* ---------- Inner Node Class ---------- */
    private class Node {
        Player data;
        Node prev;
        Node next;

        Node(Player data) {
            this.data = data;
        }
    }

    /* ---------- Fields ---------- */
    private Node head;   // head of the list (smallest: front)
    private Node tail;   // tail of the list (largest: back)
    private int size;   // node count (for tracking)

    /* ---------- Basic Queries ---------- */
    public boolean isEmpty() { return size == 0; }
    public int size()        { return size; }

    /* ---------- Sorted Insertion (alphabetical) ---------- */
    public void insertSorted(Player p) {
        Node newNode = new Node(p);

        // 1) If the list is empty
        if (isEmpty()) {
            head = tail = newNode;
        }
        // 2) Insert before head
        else if (p.compareTo(head.data) <= 0) {
            newNode.next = head;
            head.prev   = newNode;
            head        = newNode;
        }
        // 3) Insert after tail
        else if (p.compareTo(tail.data) >= 0) {
            tail.next   = newNode;
            newNode.prev = tail;
            tail        = newNode;
        }
        // 4) Insert in between
        else {
            Node cur = head.next;
            while (cur != null && p.compareTo(cur.data) > 0) {
                cur = cur.next;
            }
            // cur is now the node to the right of the new node
            Node left = cur.prev;
            left.next = newNode;
            newNode.prev = left;
            newNode.next = cur;
            cur.prev = newNode;
        }
        size++;
    }

    /* ---------- Search ---------- */
    public Player search(String name, String surname) {
        Node cur = head;
        while (cur != null) {
            if (cur.data.getName().equalsIgnoreCase(name) &&
                cur.data.getSurname().equalsIgnoreCase(surname))
                return cur.data;             // found
            cur = cur.next;
        }
        return null; // not found
    }

    /* ---------- Deletion ---------- */
    public boolean delete(String name, String surname) {
        Node cur = head;
        while (cur != null) {
            if (cur.data.getName().equalsIgnoreCase(name) &&
                cur.data.getSurname().equalsIgnoreCase(surname)) {

                // Single node
                if (head == tail) {
                    head = tail = null;
                }
                // Node at head
                else if (cur == head) {
                    head = head.next;
                    head.prev = null;
                }
                // Node at tail
                else if (cur == tail) {
                    tail = tail.prev;
                    tail.next = null;
                }
                // Node in middle
                else {
                    cur.prev.next = cur.next;
                    cur.next.prev = cur.prev;
                }
                size--;
                return true; // deleted
            }
            cur = cur.next;
        }
        return false; // not found
    }

    /* ---------- Printing ---------- */
    public void printAscending() {            // A → Z
        Node cur = head;
        while (cur != null) {
            System.out.println(cur.data);
            cur = cur.next;
        }
    }

    public void printDescending() {           // Z → A
        Node cur = tail;
        while (cur != null) {
            System.out.println(cur.data);
            cur = cur.prev;
        }
    }

    /* ---------- Simple Iterator (optional) ---------- */
    public class Iterator {
        private Node position = head;
        public boolean hasNext() { return position != null; }
        public Player next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Player p = position.data;
            position = position.next;
            return p;
        }
        public void restart() { position = head; }
    }

    public Iterator iterator() { return new Iterator(); }
}