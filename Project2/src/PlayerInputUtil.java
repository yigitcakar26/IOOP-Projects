import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * Creates a Player object from keyboard input
 * and adds it to the given DoublyLinkedList (in alphabetical order).
 *
 * Flow:
 *   1) First name
 *   2) Last name
 *   3) Birth date -> dd/mm/yyyy
 *   4) Clubs -> Comma separated, at least 1 club
 */
public final class PlayerInputUtil {

    private PlayerInputUtil() { }   // Helper class – no instance

    /**
     * @param kb   Shared Scanner
     * @param list DoublyLinkedList containing Player objects
     */
    public static void addPlayerFromKeyboard(Scanner kb, DoublyLinkedList list) {

        /* Clear the remaining newline */
        kb.nextLine();

        /* ---------- First Name ---------- */
        String name;
        do {
            System.out.print("First Name: ");
            name = kb.nextLine().trim();
        } while (name.isEmpty());

        /* ---------- Last Name ---------- */
        String surname;
        do {
            System.out.print("Last Name: ");
            surname = kb.nextLine().trim();
        } while (surname.isEmpty());

        /* ---------- Birth Date ---------- */
        Date birthDate = null;
        while (birthDate == null) {
            System.out.print("Birth date (dd/mm/yyyy): ");
            String dateStr = kb.nextLine().trim();
            String[] dmy = dateStr.split("/");
            if (dmy.length == 3) {
                try {
                    int day   = Integer.parseInt(dmy[0]);
                    int month = Integer.parseInt(dmy[1]);
                    int year  = Integer.parseInt(dmy[2]);
                    birthDate = new Date(month, day, year);
                } catch (NumberFormatException ex) {
                    System.out.println("❌  Day, month, and year must be numbers. Try again.");
                } catch (IllegalArgumentException ex) {
                    // Date constructor throws exception if invalid
                    System.out.println("❌  Invalid date range. Try again.");
                }
            } else {
                System.out.println("❌  Format must be dd/mm/yyyy. Try again.");
            }
        }

        /* ---------- Clubs ---------- */
        String clubsLine;
        do {
            System.out.print("Enter clubs separated by commas: ");
            clubsLine = kb.nextLine().trim();
        } while (clubsLine.isEmpty());

        String[] clubsArr = clubsLine.split(",");
        List<String> clubs = new ArrayList<>();
        for (String c : clubsArr) {
            if (!c.trim().isEmpty())
                clubs.add(c.trim());
        }

        /* ---------- Create Player and add to list ---------- */
        Player newPlayer = new Player(name, surname, birthDate, clubs);
        list.insertSorted(newPlayer);

        System.out.println("✅  Player added to the list: " + newPlayer);
    }
}