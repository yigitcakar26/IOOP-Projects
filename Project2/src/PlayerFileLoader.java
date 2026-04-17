import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads the lines in players.txt and returns
 * a DoublyLinkedList filled in alphabetical order.
 *
 * File format:  firstName,surname,dd/MM/yyyy,club1,club2, ...
 */
public final class PlayerFileLoader {

    private PlayerFileLoader() { /* Utility class – no constructor */ }

    /**
     * @param fileName  Path to players.txt file
     * @return          A sorted DoublyLinkedList filled with players
     * @throws IOException  If reading the file fails
     */
    public static DoublyLinkedList buildListFromFile(String fileName) throws IOException {
        DoublyLinkedList list = new DoublyLinkedList();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty())           // skip empty lines
                    continue;

                String[] parts = line.split(",");
                if (parts.length < 4)         // need at least name, surname, date + 1 club
                    continue;                 // skip invalid line

                /* --- Basic Info --- */
                String name    = parts[0].trim();
                String surname = parts[1].trim();

                /* --- Parse Date --- */
                String[] dmy = parts[2].split("/");
                int day   = Integer.parseInt(dmy[0]);
                int month = Integer.parseInt(dmy[1]);
                int year  = Integer.parseInt(dmy[2]);
                Date birthDate = new Date(month, day, year);

                /* --- Clubs --- */
                List<String> clubs = new ArrayList<>();
                for (int i = 3; i < parts.length; i++)
                    clubs.add(parts[i].trim());

                /* --- Create Player and add to list --- */
                Player p = new Player(name, surname, birthDate, clubs);
                list.insertSorted(p);         // insert in alphabetical order
            }
        }
        return list;
    }
}