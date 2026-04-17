import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Menu option 8:
 * Writes the current Player records from the doubly linked list
 * to a temp file in CSV format.
 */
public final class PlayerFileSaver {

    private PlayerFileSaver() { /* utility class – no constructor */ }

    /**
     * Saves the list to the specified file.
     *
     * @param list      Player list to save
     * @param fileName  Output file name (e.g., "players_updated.txt")
     * @throws IOException  If a write error occurs
     */
    public static void saveListToFile(DoublyLinkedList list,
                                      String fileName) throws IOException {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {

            DoublyLinkedList.Iterator it = list.iterator();   // A → Z order

            while (it.hasNext()) {
                Player p = it.next();

                /* ---- Convert to CSV string ---- */
                StringBuilder sb = new StringBuilder();
                sb.append(p.getName()).append(',')
                  .append(p.getSurname()).append(',')
                  .append(p.getBirthDate().getDay()).append('/')
                  .append(p.getBirthDate().getMonth()).append('/')
                  .append(p.getBirthDate().getYear());

                List<String> clubs = p.getClubs();
                for (String c : clubs)
                    sb.append(',').append(c);

                /* ---- Write to file ---- */
                bw.write(sb.toString());
                bw.newLine();
            }
        }
    }
}