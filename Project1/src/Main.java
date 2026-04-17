// Ege University - Computer Engineering, IOOP Project 1
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static final int MAX_CUSTOMERS = 200;
    private static LinkedList customerList = new LinkedList();
    private static int[][] ratingsMatrix;
    private static int productCount;
    private static String[] productNames;

    public static void main(String[] args) {
        // Show the console menu
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1) Read the data from the file and create data sets");
            System.out.println("2) Add new customer");
            System.out.println("3) Print Average Ratings for All Products");
            System.out.println("4) Print Average Ratings for All Products for Turkey");
            System.out.println("5) Print Average Ratings for Countries Other Than Turkey");
            System.out.println("6) Print Average Ratings for All Products for Doctors");
            System.out.println("7) Print the List of Customer Information");
            System.out.println("8) Print the Two-Dimensional Array");
            System.out.println("0) Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();  // cleaning the buffer

            switch (choice) {
                case 1:
                    // Read the data from the file and create data sets
                    try {
                        readFromFile("Firma.txt");
                    } catch (IOException e) {
                        System.out.println("File not found: " + e.getMessage());
                    }
                    break;
                case 2:
                    // Add new customer
                    addNewCustomer(scanner);
                    break;
                case 3:
                    // Print average ratings for all products
                    printAverageRatings();
                    break;
                case 4:
                    // Print average ratings for all products for Turkey
                    printAverageRatingsForTurkey();
                    break;
                case 5:
                    // Print average ratings for other countries (Turkey is not included)
                    printAverageRatingsForNonTurkey();
                    break;
                case 6:
                    // Print average ratings for doctors
                    printAverageRatingsForDoctors();
                    break;
                case 7:
                    // Print the list of customer information
                    System.out.println("List of customer information:");
                    // Check if the customer list is empty
                    if (customerList.getHead() == null) {
                        System.out.println("No customer data available.\n");
                    }
                    customerList.printList();
                    break;
                case 8:
                    // Print the two-dimensional array
                    printRatingsMatrix();
                    break;
                case 0:
                    // Exit the program
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.\n");
            }
        }
    }

    private static void readFromFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line = reader.readLine();

        if (line != null) {
            String[] headerParts = line.split(",");
            productCount = Integer.parseInt(headerParts[0]);
            productNames = new String[productCount];
            System.arraycopy(headerParts, 1, productNames, 0, productCount);
            ratingsMatrix = new int[MAX_CUSTOMERS][productCount + 1];
        } else {
            throw new IOException("First line of the file not read.");
        }

        int customerIndex = 0;
        while ((line = reader.readLine()) != null) {
            String[] customerParts = line.split(",");
            if (customerParts.length < 6) {
                System.out.println("Missing customer information: " + line);
                continue;
            }

            int customerNumber = Integer.parseInt(customerParts[0]);
            String name = customerParts[1];
            String surname = customerParts[2];
            String country = customerParts[3];
            String city = customerParts[4];
            String occupation = customerParts[5];
            CustomerData customerData = new CustomerData(name, surname, country, city, occupation);
            customerList.addNode(customerNumber, customerData);

            line = reader.readLine();
            if (line != null) {
                String[] ratingParts = line.split(",");
                if (ratingParts.length < productCount) {
                    System.out.println("Missin rating information: " + line);
                    continue;
                }

                ratingsMatrix[customerIndex][0] = customerNumber;
                for (int i = 0; i < productCount; i++) {
                    ratingsMatrix[customerIndex][i + 1] = Integer.parseInt(ratingParts[i]);
                }
                customerIndex++;
            }
        }
        reader.close();
        System.out.println("File read\n");
    }

    private static void addNewCustomer(Scanner scanner) {
        if (productCount == 0 || productNames == null) {
            System.out.println("Product information is missing. Read the data from the file first.\n");
            return;
        }
        System.out.println("Enter the information for the new customer:");
        System.out.print("Customer number: ");
        int customerNumber = scanner.nextInt();
        scanner.nextLine(); // buffer cleaning

        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Surname: ");
        String surname = scanner.nextLine();
        System.out.print("Country: ");
        String country = scanner.nextLine();
        System.out.print("City: ");
        String city = scanner.nextLine();
        System.out.print("Occupation: ");
        String occupation = scanner.nextLine();

        // Create new customer information
        CustomerData newCustomer = new CustomerData(name, surname, country, city, occupation);
        customerList.addNode(customerNumber, newCustomer);

        System.out.println("Enter the ratings for the products:");
        int[] newRatings = new int[productCount];
        for (int i = 0; i < productCount - 1; i++) {
            System.out.print(productNames[i] + " rating: ");
            newRatings[i] = scanner.nextInt();
        }

        // Find the most similar customer and calculate the estimated rating for the last product
        int mostSimilarCustomerIndex = findMostSimilarCustomer(newRatings);
        if (mostSimilarCustomerIndex != -1) {
            int estimatedRating = ratingsMatrix[mostSimilarCustomerIndex][productCount];
            System.out.println("Estimated rating for " + productNames[productCount - 1] + ": " + estimatedRating + "\n");
            newRatings[productCount - 1] = estimatedRating;
        }

        // Add the new customer and ratings to the data structures
        int customerIndex = 0;
        while (ratingsMatrix[customerIndex][0] != 0) {
            customerIndex++;
        }
        ratingsMatrix[customerIndex][0] = customerNumber;
        System.arraycopy(newRatings, 0, ratingsMatrix[customerIndex], 1, productCount);
    }

    private static int findMostSimilarCustomer(int[] newRatings) {
        // Initialize variables to store the minimum difference and the index of the most similar customer
        int minDifference = Integer.MAX_VALUE;
        int mostSimilarCustomerIndex = -1;

        // Loop through each existing customer's ratings and calculate the difference from the new ratings
        for (int i = 0; i < MAX_CUSTOMERS && ratingsMatrix[i][0] != 0; i++) {
            int difference = 0;
            // Calculate the absolute difference for each product rating
            for (int j = 1; j < productCount; j++) {
                difference += Math.abs(newRatings[j - 1] - ratingsMatrix[i][j]);
            }
            // Update the minimum difference and the index of the most similar customer if a smaller difference is found
            if (difference < minDifference) {
                minDifference = difference;
                mostSimilarCustomerIndex = i;
            }
        }

        // Return the index of the most similar customer or -1 if no similar customer is found
        return mostSimilarCustomerIndex;
    }

    private static void printAverageRatings() {
        System.out.println("Average ratings for all products:");
        // Check if the customer list is empty
        if (customerList.getHead() == null) {
            System.out.println("No customer data available.\n");
            return;
        }
        // Iterate over each product
        for (int i = 1; i <= productCount; i++) {
            double total = 0;
            int count = 0;
            // Iterate over each customer's ratings
            for (int j = 0; j < MAX_CUSTOMERS && ratingsMatrix[j][0] != 0; j++) {
                // Accumulate total ratings and count the number of customers
                total += ratingsMatrix[j][i];
                count++;
            }
            // Calculate the average rating for the current product
            double average = total / count;
            // Print the average rating for the current product
            System.out.println(productNames[i - 1] + ": " + average);
        }
        System.out.println("\n");
    }

    private static void printAverageRatingsForTurkey() {
        System.out.println("Average ratings for all products for customers from Turkey:");
        // Check if the customer list is empty
        if (customerList.getHead() == null) {
            System.out.println("No customer data available.\n");
            return;
        }
        // Iterate over each product
        for (int i = 1; i <= productCount; i++) {
            double total = 0;
            int count = 0;
            Node current = customerList.getHead();
            // Iterate over each customer
            while (current != null) {
                // Check if the customer is from Turkey
                if (current.getCustomerData().getCountry().equals("Turkey")) {
                    // Iterate over each customer's ratings
                    for (int j = 0; j < MAX_CUSTOMERS && ratingsMatrix[j][0] != 0; j++) {
                        // Find the matching customer in the ratings matrix
                        if (ratingsMatrix[j][0] == current.getCustomerNumber()) {
                            // Accumulate total ratings and count the number of customers
                            total += ratingsMatrix[j][i];
                            count++;
                            break;
                        }
                    }
                }
                current = current.getNext();
            }
            // Calculate the average rating for the current product
            double average = total / count;
            // Print the average rating for the current product
            System.out.println(productNames[i - 1] + ": " + average);
        }
        System.out.println("\n");
    }

    private static void printAverageRatingsForNonTurkey() {
        System.out.println("Average ratings for all products for customers from countries other than Turkey:");
        // Check if the customer list is empty
        if (customerList.getHead() == null) {
            System.out.println("No customer data available.\n");
            return;
        }
        // Iterate over each product
        for (int i = 1; i <= productCount; i++) {
            double total = 0;
            int count = 0;
            Node current = customerList.getHead();
            // Iterate over each customer
            while (current != null) {
                // Check if the customer is not from Turkey
                if (!current.getCustomerData().getCountry().equals("Turkey")) {
                    // Iterate over each customer's ratings
                    for (int j = 0; j < MAX_CUSTOMERS && ratingsMatrix[j][0] != 0; j++) {
                        // Find the matching customer in the ratings matrix
                        if (ratingsMatrix[j][0] == current.getCustomerNumber()) {
                            // Accumulate total ratings and count the number of customers
                            total += ratingsMatrix[j][i];
                            count++;
                            break;
                        }
                    }
                }
                current = current.getNext();
            }
            // Calculate the average rating for the current product
            double average = total / count;
            // Print the average rating for the current product
            System.out.println(productNames[i - 1] + ": " + average);
        }
        System.out.println("\n");
    }

    private static void printAverageRatingsForDoctors() {
        System.out.println("Average ratings for all products for customers whose occupation is 'Doctor':");
        // Check if the customer list is empty
        if (customerList.getHead() == null) {
            System.out.println("No customer data available.\n");
            return;
        }
        // Iterate over each product
        for (int i = 1; i <= productCount; i++) {
            double total = 0;
            int count = 0;
            Node current = customerList.getHead();
            // Iterate over each customer
            while (current != null) {
                // Check if the customer's occupation is 'Doctor'
                if (current.getCustomerData().getOccupation().equals("Doctor")) {
                    // Iterate over each customer's ratings
                    for (int j = 0; j < MAX_CUSTOMERS && ratingsMatrix[j][0] != 0; j++) {
                        // Find the matching customer in the ratings matrix
                        if (ratingsMatrix[j][0] == current.getCustomerNumber()) {
                            // Accumulate total ratings and count the number of customers
                            total += ratingsMatrix[j][i];
                            count++;
                            break;
                        }
                    }
                }
                current = current.getNext();
            }
            // Calculate the average rating for the current product
            double average = total / count;
            // Print the average rating for the current product
            System.out.println(productNames[i - 1] + ": " + average);
        }
        System.out.println("\n");
    }

    private static void printRatingsMatrix() {
        System.out.println("Two-dimensional ratings matrix:");
        // Check if the customer list is empty
        if (customerList.getHead() == null) {
            System.out.println("No customer data available.\n");
            return;
        }
        // Iterate through each customer's ratings
        for (int i = 0; i < MAX_CUSTOMERS && ratingsMatrix[i][0] != 0; i++) {
            System.out.print("Customer No: " + ratingsMatrix[i][0] + " -> ");
            // Print ratings for each product
            for (int j = 1; j <= productCount; j++) {
                System.out.print(productNames[j - 1] + ": " + ratingsMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}

