
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class RentalStore
{
    private List<DVD> availableDVDs; // list of dvd's in the store
    private List<DVD> unavailableDVDs; // List of dvd's not in store
    private List<Customer> customers; // list of registered customers in the store
    private List<Rental> rentals; // list of active rentals
    
    // Constructor 
    public RentalStore() {
        this.availableDVDs = new ArrayList<>(); 
        this.unavailableDVDs = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.rentals = new ArrayList<>();
    }
    
    // gets
    public List<DVD> getAvailableDVDs() {
        return availableDVDs;
    }
    
    public List<DVD> getUnavailableDVDs() {
        return unavailableDVDs;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<Rental> getRentals() {
        return rentals;
    }
    
    
    // adds a dvd to the stores database
    public void addDVD(DVD dvd) {
        availableDVDs.add(dvd); //adds the dvd to the list
        System.out.println(dvd.getTitle() + " has been added to the store."); //prints a message showing its done
    }
    
    public void removeDVD(String title) {
        DVD dvd = getDVDByTitle(title);  // Use the method to find the DVD by title
        if (dvd != null) {
            availableDVDs.remove(dvd);  // Remove the DVD from the list
            System.out.println(dvd.getTitle() + " has been removed from the store.");
        }   else {
            System.out.println("DVD with title " + title + " not found.");
        }
    }
    
    public void addRentedDVD(DVD dvd) {
        unavailableDVDs.add(dvd); //adds the dvd to the list
        System.out.println(dvd.getTitle() + " has been added to the store's rented dvd's."); //prints a message showing its done
    }

    // Adds a customer to the stores database
    public void registerCustomer(Customer customer) {
        customers.add(customer); //adds cust to the list
        System.out.println("Customer " + customer.getName() + " registered."); // msg
    }

    // Used for renting a dvd
    public void rentDVD(Customer customer, DVD dvd) {
        // If the dvd is in the system
        if (availableDVDs.contains(dvd)) {
            unavailableDVDs.add(dvd);// adds dvd to the list so its not lost 
            availableDVDs.remove(dvd);// removes from available dvd's
            Rental rental = new Rental(customer, dvd, LocalDate.now());// makes a rental with Customer and DVD and the current date
            rentals.add(rental);// adds the created rental to the rental list
            System.out.println(customer.getName() + " rented " + dvd.getTitle() + ".");// prints out the rental details
        } 
        // if the dvd isnt in the database
        else {
            System.out.println(dvd.getTitle() + " is not available for rent.");
        }
    }
    
    // Used for entering an existing rent, also an example of overloading
    public void rentDVD(Customer customer, DVD dvd, LocalDate rentalDate) {
        // Check if the DVD is in the system and available
        if (unavailableDVDs.contains(dvd)) {
            Rental rental = new Rental(customer, dvd, rentalDate); // Create a rental with the given date
            rentals.add(rental); // Add the rental to the rental list
            System.out.println(customer.getName() + " rented " + dvd.getTitle() + " on " + rentalDate + "."); // Print details
        } 
        // If the DVD isn't in the database or is unavailable
        else {
            System.out.println(dvd.getTitle() + " is not available for rent.");
        }
    }
    
    // used for returning a dvd
    public void returnDVD(Customer customer, DVD dvd) {
        Rental rentalToReturn = null;
        // checks all the "Rentals" in the list rentals and assigns it to rental (this naming scheme is kinda bad)
        // this basically finds the rental that is being returned using the given cust and dvd.
        for (Rental rental : rentals) {
            if (rental.customer.equals(customer) && rental.dvd.equals(dvd)) {
                // sets the found rental to rentalToReturn
                rentalToReturn = rental;
                break;
            }
        }
        
        // if a rental is found (see above) it will then check for being over due and add it back to the dvd list
        if (rentalToReturn != null) {
            double overduePrice = rentalToReturn.returnDVD(LocalDate.now());// gets the overdue price from rentals function
            rentals.remove(rentalToReturn);// removes the rental from the list of rentals, its returned now
            availableDVDs.add(dvd);// adds the dvd back to dvd's
            unavailableDVDs.remove(dvd);// removs it from rented dvd's list
            System.out.println(customer.getName() + " returned " + dvd.getTitle() + " with an overdue charge of: " + overduePrice);// prints out the return details
        } 
        // if the rental cant be found
        else {
            System.out.println("No active rental found for " + customer.getName() + " and " + dvd.getTitle() + ".");
        }
    }
    
    // goes through all the available dvd's and prints them
    public final void viewAvailableDVDs() {
        System.out.println("Available DVDs:");
        for (DVD dvd : availableDVDs) {
            System.out.println(dvd);
        }
    }
    
    // goes through all the unavailable dvd's and prints them
    public final void viewUnavailableDVDs() {
        System.out.println("Available DVDs:");
        for (DVD dvd : unavailableDVDs) {
            System.out.println(dvd);
        }
    }
    
    // goes through all the customers and prints them
    public final void viewCustomers() {
        System.out.println("Registered Customers:");
        for (Customer customer : customers) {
        System.out.println(customer);
        }   
    }
    
    // goes through all the rentals's and prints them
    public final void viewRentals() {
        System.out.println("Current Rentals:");
        for (Rental rental : rentals) {
            System.out.println(rental);
        }
    }
    
    // finds a customer using thier ID
    public Customer getCustomerById(String customerId) {
        // goes through all the customers and assigns it to customer
        for (Customer customer : customers) {
            // if the customerID of customer is the same as the ID parsed in the function it will return the customer that had that ID
            if (customer.getCustomerId().equalsIgnoreCase(customerId)) {
                return customer;
            }
        }
        return null; // Return null if the customer isn't found
    }
    
    // finds a DVD using its title
    public DVD getDVDByTitle(String title) {
        // goes through all the dvd's in available dvd's and assigns it to dvd
        for (DVD dvd : availableDVDs) {
            // if the title of the dvd is the same as the title parsed in the function it will return the dvd that had that title
            if (dvd.getTitle().equalsIgnoreCase(title)) { // Case-insensitive match
                return dvd;
            }
        }
        return null; // Return null if the DVD isn't found
    }
    
    // finds a rented DVD using its title
    public DVD getRentedDVDByTitle(String title) {
        // goes through all the rented dvd's in unavailable dvd's and assigns it to dvd
        for (DVD dvd : unavailableDVDs) {
            // if the title of the rented dvd is the same as the title parsed in the function it will return the rented dvd that had that title
            if (dvd.getTitle().equalsIgnoreCase(title)) { // Case-insensitive match
                return dvd;
            }
        }
        return null; // Return null if the DVD isn't found
    }
}
