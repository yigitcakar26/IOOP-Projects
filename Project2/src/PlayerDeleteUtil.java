import java.util.Scanner;

/**
 * Menu option 4: Deletes a player by first name and surname.
 */
public final class PlayerDeleteUtil {

    private PlayerDeleteUtil() { /* utility class – no constructor */ }

    /**
     * @param kb   Open Scanner
     * @param list Doubly linked list of players
     */
    public static void deletePlayer(Scanner kb, DoublyLinkedList list) {
        if (list.isEmpty()) {
            System.out.println("⚠️  List is already empty—cannot delete.");
            return;
        }

        System.out.print("Name of player to delete: ");
        String name = kb.next().trim();

        System.out.print("Surname: ");
        String surname = kb.next().trim();

        boolean removed = list.delete(name, surname);

        if (removed) {
            System.out.println("🗑️  '" + name + " " + surname +
                               "' successfully deleted from the list.");
        } else {
            System.out.println("❌  Player named '" + name + " " + surname +
                               "' not found in the list.");
        }
    }
}