import java.util.Scanner;

/**
 * Menu option 7:
 *    Copies all Player objects from the doubly linked list to a MyStack
 *    and then prints the stack contents.
 */
public final class PlayerStackUtil {

    private PlayerStackUtil() { /* utility class – no constructor */ }

    /**
     * @param list  Source DoublyLinkedList (containing Player items)
     */
    public static void copyListToStackAndPrint(DoublyLinkedList list) {
        if (list.isEmpty()) {
            System.out.println("⚠️  List is empty—no elements to copy to stack.");
            return;
        }

        /* --- 1) Create stack --- */
        MyStack stack = new MyStack(list.size());   // capacity = list size

        /* --- 2) Traverse list from start and push --- */
        DoublyLinkedList.Iterator it = list.iterator();
        while (it.hasNext())
            stack.push(it.next());                  // Player object as Object

        /* --- 3) Pop stack and print --- */
        System.out.println("\n--- Stack (LIFO) Output ---");
        while (!stack.isEmpty()) {
            Player p = (Player) stack.pop();        // Object -> Player
            System.out.println(p);                  // Player.toString()
        }
    }
}