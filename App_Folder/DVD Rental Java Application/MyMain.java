
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Scanner;

public class MyMain {
    public static void main(String[] args) {
        RentalStore store = new RentalStore();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        
        System.out.println("Welcome to the DVD Rental Store Database Management System!");
        System.out.print("Do you want to load an existing database? (yes/no): ");
        String choiceBoot = scanner.nextLine();

        if (choiceBoot.equalsIgnoreCase("yes")) {
            System.out.print("Enter the filename of the database: ");
            String filename = scanner.nextLine();
            DBTools.loadDatabase(store, filename);
        } else {
            System.out.println("We dont recommend filling the entire database from the application");
            System.out.println("Please see the ReadMe to see how to write directly into the database file about to be created");
            System.out.println("Enter a name for the new database file: ");
            String filename = scanner.nextLine();
            DBTools.saveDatabase(store, filename); // Create a blank file
        }

        while (running) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add a DVD");
            System.out.println("2. Remove a DVD");
            System.out.println("3. Register a Customer");
            System.out.println("4. Rent a DVD");
            System.out.println("5. Return a DVD");
            System.out.println("6. View Available DVDs");
            System.out.println("7. View Unavailable DVDs");
            System.out.println("8. View Current Rentals");
            System.out.println("9. View All Customers");
            System.out.println("10. Save DataBase");
            System.out.println("11. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Add a DVD
                    System.out.println("Enter DVD title:");
                    String title = scanner.nextLine();
                    System.out.println("Enter price:");
                    int price = scanner.nextInt();
                    System.out.println("Enter rental period (days):");
                    int rentalPeriod = scanner.nextInt();
                    System.out.println("Enter overdue charge per day:");
                    int overdueCharge = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.println("Is this a Movie (M) or Series (S)?");
                    char type = scanner.nextLine().toUpperCase().charAt(0);

                    if (type == 'M') {
                        System.out.println("Enter movie length (minutes):");
                        int length = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.println("Enter director name:");
                        String director = scanner.nextLine();
                        DVDMovie newMovie = new DVDMovie(title, price, rentalPeriod, overdueCharge, length, director);
                        store.addDVD(newMovie);
                        System.out.println("DVD Movie added successfully!");
                    } else if (type == 'S') {
                        System.out.println("Enter number of episodes:");
                        int episodes = scanner.nextInt();
                        System.out.println("Enter average episode length (minutes):");
                        int episodeLength = scanner.nextInt();
                        DVDSeries newSeries = new DVDSeries(title, price, rentalPeriod, overdueCharge, episodes, episodeLength);
                        store.addDVD(newSeries);
                        System.out.println("DVD Series added successfully!");
                    } else {
                        System.out.println("Invalid type!");
                    }
                    break;
                case 2:
                    System.out.println("Enter the title of the dvd you want to remove:");
                    String removeTitle;
                    removeTitle = scanner.nextLine();
                    store.removeDVD(removeTitle);
                    break;
                    
                case 3:
                    // Register a customer
                    System.out.println("Enter customer ID:");
                    String customerId = scanner.nextLine();
                    System.out.println("Enter customer name:");
                    String name = scanner.nextLine();
                    System.out.println("Enter customer email:");
                    String email = scanner.nextLine();
                    System.out.println("Enter customer phone number:");
                    long phone = scanner.nextLong();
                    scanner.nextLine(); // Consume newline
                    String membership;
                    do {
                        System.out.println("Enter membership type (Copper/Silver/Gold):");
                        membership = scanner.nextLine().trim();
                    } while (!membership.equalsIgnoreCase("Copper") &&
                             !membership.equalsIgnoreCase("Silver") &&
                             !membership.equalsIgnoreCase("Gold"));
                    Customer newCustomer = new Customer(customerId, name, email, phone, membership);
                    store.registerCustomer(newCustomer);
                    System.out.println("Customer registered successfully!");
                    break;

                case 4:
                    // Rent a DVD
                    System.out.println("Enter customer ID:");
                    String rentCustomerId = scanner.nextLine();
                    System.out.println("Enter DVD title:");
                    String rentTitle = scanner.nextLine();
                    Customer rentCustomer = store.getCustomerById(rentCustomerId);
                    DVD rentDVD = store.getDVDByTitle(rentTitle);
                    if (rentCustomer != null && rentDVD != null) {
                        store.rentDVD(rentCustomer, rentDVD);
                    } else {
                        System.out.println("Invalid customer or DVD!");
                    }
                    break;

                case 5:
                    // Return a DVD
                    System.out.println("Enter customer ID:");
                    String returnCustomerId = scanner.nextLine();
                    System.out.println("Enter DVD title:");
                    String returnTitle = scanner.nextLine();
                    Customer returnCustomer = store.getCustomerById(returnCustomerId);
                    DVD returnDVD = store.getRentedDVDByTitle(returnTitle);
                    if (returnCustomer != null && returnDVD != null) {
                        store.returnDVD(returnCustomer, returnDVD);
                    } else {
                        System.out.println("Invalid customer or DVD!");
                    }
                    break;

                case 6:
                    // View available DVDs
                    store.viewAvailableDVDs();
                    break;
                    
                case 7:
                    //view unavailable dvd's
                    store.viewUnavailableDVDs();
                    break;
                    
                case 8:
                    // View current rentals
                    store.viewRentals();
                    break;
                    
                case 9:
                    // Prints all the customers
                    store.viewCustomers();
                    break;
                    
                case 10:
                    // Saves the current store to a file DB of the users choice, creating a new one if its not a thing
                    String filename;
                    System.out.print("Enter the name for the Database file: ");
                    filename = scanner.nextLine();
                    DBTools.saveDatabase(store, filename);
                    break;
                    
                case 11:
                    // Exit
                    String exitChoice;
                    System.out.println("Are you sure, there is no autosaving (yes/no)");
                    exitChoice = scanner.nextLine();
                    if(exitChoice.trim().equals("yes")){
                        running = false;
                        System.out.println("Exiting the system. Goodbye!");
                        break;
                    }
                    else{
                        break;
                    }

                default:
                    System.out.println("Invalid choice. Please try again.");
                    scanner.nextLine(); // Consume invalid input
                    break;
            }
        }

        scanner.close();
    }
}

