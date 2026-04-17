import java.util.Scanner;

/**
 * Menu option 3: Prints player information by first name and surname.
 */
public final class PlayerQueryUtil {

    private PlayerQueryUtil() { /* utility class – no constructor */ }

    /**
     * @param kb   Open Scanner instance
     * @param list Doubly linked list storing players
     */
    public static void printPlayerInfo(Scanner kb, DoublyLinkedList list) {
        if (list.isEmpty()) {
            System.out.println("⚠️  List is currently empty.");
            return;
        }

        System.out.print("Enter first name to search: ");
        String name = kb.next().trim();

        System.out.print("Surname: ");
        String surname = kb.next().trim();

        Player found = list.search(name, surname);

        if (found != null) {
            System.out.println("\n🔎  Player found:");
            System.out.println(found);           // Player.toString()
        } else {
            System.out.println("\n❌  Player named '" + name + " " + surname +
                               "' not found in the list.");
        }
    }
}