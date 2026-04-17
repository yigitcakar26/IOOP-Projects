/**
 * Menu options 5 and 6:
 *    - printAscending: A → Z
 *    - printDescending: Z → A
 *
 * Calls the printAscending() / printDescending() functions
 * defined in DoublyLinkedList.
 */
public final class PlayerPrintUtil {

    private PlayerPrintUtil() { /* utility class – no constructor */ }

    /* 5. Item – A → Z */
    public static void printAscending(DoublyLinkedList list) {
        if (list.isEmpty()) {
            System.out.println("⚠️  List is empty—no records to print.");
            return;
        }
        System.out.println("\n--- Player List (A → Z) ---");
        list.printAscending();
    }

    /* 6. Item – Z → A */
    public static void printDescending(DoublyLinkedList list) {
        if (list.isEmpty()) {
            System.out.println("⚠️  List is empty—no records to print.");
            return;
        }
        System.out.println("\n--- Player List (Z → A) ---");
        list.printDescending();
    }
}
