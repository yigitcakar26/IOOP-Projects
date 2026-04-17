import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Player implements Comparable<Player> {

    /* ---------- Fields ---------- */
    private String name;
    private String surname;
    private Date birthDate;
    private ArrayList<String> clubs;   // insertion order preserved

    /* ---------- Constructors ---------- */

    /**
     * Full-argument constructor.
     * @param name      Player's first name (null/empty check applied)
     * @param surname   Player's last name (null/empty check applied)
     * @param birthDate Date object (must not be null)
     * @param clubs     List of club names (if null, a new empty list is created)
     */
    public Player(String name, String surname, Date birthDate, List<String> clubs) {
        setName(name);
        setSurname(surname);
        setBirthDate(birthDate);
        // Deep copy – do not reference the input list directly
        this.clubs = (clubs == null)
                ? new ArrayList<>()
                : new ArrayList<>(clubs);
    }

    /**
     * Copy constructor (deep copy).
     */
    public Player(Player other) {
        if (other == null)
            throw new IllegalArgumentException("Player copy source is null.");
        this.name = other.name;
        this.surname = other.surname;
        this.birthDate = new Date(other.birthDate); // Date class's copy constructor
        this.clubs = new ArrayList<>(other.clubs);
    }

    /* ---------- Getter & Setter ---------- */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        validateString(name, "Name");
        this.name = name.trim();
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        validateString(surname, "Surname");
        this.surname = surname.trim();
    }

    public Date getBirthDate() {
        // If Date is mutable, returning a copy is safer
        return new Date(birthDate);
    }

    public void setBirthDate(Date birthDate) {
        if (birthDate == null)
            throw new IllegalArgumentException("BirthDate cannot be null.");
        this.birthDate = new Date(birthDate); // deep copy
    }

    public List<String> getClubs() {
        // Return an unmodifiable list to the outside
        return Collections.unmodifiableList(clubs);
    }

    /**
     * Adds a club. Null or empty strings are not allowed,
     * and the same club will not be added twice.
     */
    public void addClub(String clubName) {
        validateString(clubName, "Club name");
        if (!clubs.contains(clubName.trim()))
            clubs.add(clubName.trim());
    }

    /* ---------- compareTo, equals, hashCode ---------- */

    /**
     * Alphabetical ordering: first by name, if equal then by surname.
     */
    @Override
    public int compareTo(Player other) {
        int cmp = this.name.compareToIgnoreCase(other.name);
        return (cmp != 0) ? cmp
                          : this.surname.compareToIgnoreCase(other.surname);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player p = (Player) o;
        return name.equalsIgnoreCase(p.name) &&
               surname.equalsIgnoreCase(p.surname) &&
               birthDate.equals(p.birthDate) &&
               clubs.equals(p.clubs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name.toLowerCase(), surname.toLowerCase(),
                            birthDate, clubs);
    }

    /* ---------- toString ---------- */

    /**
     * Example output:
     *   Fernando MUSLERA (16/06/1986) - [Wanderers, Lazio, Galatasaray]
     */
    @Override
    public String toString() {
        return String.format("%s %s (%02d/%02d/%04d) - %s",
                name,
                surname.toUpperCase(),
                birthDate.getDay(),
                birthDate.getMonth(),
                birthDate.getYear(),
                clubs);
    }

    /* ---------- Helper ---------- */

    private static void validateString(String s, String fieldName) {
        if (s == null || s.trim().isEmpty())
            throw new IllegalArgumentException(fieldName + " cannot be null/empty.");
    }
}