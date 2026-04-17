import java.io.IOException;
import java.util.Scanner;

/**
 * Main class – Menu-based player information application
 *
 * 1) Create list from file
 * 2) Add player from keyboard
 * 3) Show player information by first and last name
 * 4) Delete player by first and last name
 * 5) Print all records A → Z
 * 6) Print all records Z → A
 * 7) Copy list to stack and print stack
 * 8) Exit and save list to file
 */
public class Main {

    private static final String DEFAULT_INPUT_FILE  = "players.txt";
    private static final String DEFAULT_OUTPUT_FILE = "players_updated.txt";

    public static void main(String[] args) {

        Scanner kb = new Scanner(System.in);
        DoublyLinkedList playerList = new DoublyLinkedList();

        boolean exit = false;
        while (!exit) {
            printMenu();
            System.out.print("Your choice (1-8): ");
            String choiceStr = kb.nextLine().trim();

            switch (choiceStr) {
                case "1":
                    // Load list from file
                    System.out.print("File path (leave empty for default: " + DEFAULT_INPUT_FILE + "): ");
                    String path = kb.nextLine().trim();
                    if (path.isEmpty()) path = DEFAULT_INPUT_FILE;
                    try {
                        playerList = PlayerFileLoader.buildListFromFile(path);
                        System.out.println("\u2705  List loaded from file. Total records: "
                                           + playerList.size());
                    } catch (IOException ex) {
                        System.err.println("\u274c  File could not be read: " + ex.getMessage());
                    }
                    break;

                case "2":
                    // Add player from keyboard
                    PlayerInputUtil.addPlayerFromKeyboard(kb, playerList);
                    break;

                case "3":
                    // Show player information
                    PlayerQueryUtil.printPlayerInfo(kb, playerList);
                    kb.nextLine(); // clear end of line from Scanner
                    break;

                case "4":
                    // Delete player
                    PlayerDeleteUtil.deletePlayer(kb, playerList);
                    kb.nextLine(); // clear end of line
                    break;

                case "5":
                    // Print A → Z
                    PlayerPrintUtil.printAscending(playerList);
                    break;

                case "6":
                    // Print Z → A
                    PlayerPrintUtil.printDescending(playerList);
                    break;

                case "7":
                    // Copy list to stack and print
                    PlayerStackUtil.copyListToStackAndPrint(playerList);
                    break;

                case "8":
                    // Exit – save list to file
                    try {
                        PlayerFileSaver.saveListToFile(playerList, DEFAULT_OUTPUT_FILE);
                        System.out.println("\uD83D\uDCBE  List saved to '" + DEFAULT_OUTPUT_FILE +
                                           "'.");
                    } catch (IOException ex) {
                        System.err.println("\u274c  File write error: " + ex.getMessage());
                    }
                    exit = true;
                    System.out.println("\uD83D\uDC4B  Program terminating...");
                    break;

                default:
                    System.out.println("\u26A0\uFE0F  Invalid choice. Please enter a number between 1 and 8.");
            }
            System.out.println(); // blank line between menus
        }
        kb.close();
    }

    /** Prints the menu header */
    private static void printMenu() {
        System.out.println("======= Player Information System =======");
        System.out.println("1) Create list from file");
        System.out.println("2) Add player from keyboard");
        System.out.println("3) Show player information");
        System.out.println("4) Delete player");
        System.out.println("5) Print all records A \u2192 Z");
        System.out.println("6) Print all records Z \u2192 A");
        System.out.println("7) Copy list to stack and print");
        System.out.println("8) Exit (save list)");
        System.out.println("====================================");
    }
}