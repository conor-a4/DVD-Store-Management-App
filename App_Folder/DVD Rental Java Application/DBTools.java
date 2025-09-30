
import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class DBTools {
    // This is for loaded an existing DB File (check ReadMe)
    public static void loadDatabase(RentalStore store, String filename) {
        // uses try BufferedReader to open a file by its name
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line; // the line we are reading
            String section = ""; // Track which section we're in
            
            // uses a while loop to reads the lines and stops when it reads a NULL line, this is different from a blank line and means EOF
            while ((line = reader.readLine()) != null) {
                //Removes whitespace
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) {
                    // adds the line to the section string if it starts with # or is blank
                    section = line.startsWith("#") ? line : section; // Update the section to #... if conditions are correct, E.g. "# Customers"
                    continue;// stops the code from trying to use "#..." or blank space as data in the DB
                    // the point of this code is to know wether to skip the line or use it as data and for the switch case to work
                }
                
                // gets section from above code
                switch (section) {
                    case "# Customers":
                        String[] customerData = line.split(",");// this is used to split the data into different segments on the line so they can be looked at e.g. "customerData[0]"
                        //Creates customer using DB data
                        Customer customer = new Customer(
                            customerData[0], // customerId
                            customerData[1], // name
                            customerData[2], // email
                            Long.parseLong(customerData[3]), // phoneNumber
                            customerData[4] // subscriptionType
                            );
                        //Adds the created customer to the stores List
                        store.registerCustomer(customer);
                        break;// breaks the switch case so the next line can be read

                    case "# DVDs":
                        String[] dvdData = line.split(",");// this is used to split the data into different segments on the line so they can be looked at e.g. "Integer.parseInt(dvdData[2])"
                        // Checks if the identifyer for the DVD type is correct, ignoring letter case
                        if (dvdData[0].equalsIgnoreCase("Movie")) {
                            //Creates a new DVDMovie using DB data
                            DVDMovie movie = new DVDMovie(
                                dvdData[1], // title
                                Integer.parseInt(dvdData[2]), // price
                                Integer.parseInt(dvdData[3]), // rentalperiod
                                Integer.parseInt(dvdData[4]), // PricePerDay
                                Integer.parseInt(dvdData[5]), // Length
                                dvdData[6] // director
                            );
                            //Adds the new data to stores list
                            store.addDVD(movie);
                        } 
                        // Checks if the identifyer is correct ignoring letter case
                        else if (dvdData[0].equalsIgnoreCase("Series")) {
                            //Creates a new DVDSeries using the DB data
                            DVDSeries series = new DVDSeries(
                                dvdData[1], // title
                                Integer.parseInt(dvdData[2]), // price
                                Integer.parseInt(dvdData[3]), // rentalperiod
                                Integer.parseInt(dvdData[4]), // PricePerDay
                                Integer.parseInt(dvdData[5]), // episodes
                                Integer.parseInt(dvdData[6]) // ep Length
                            );
                            //adds it to the stores list
                            store.addDVD(series);
                        }
                        break;// breaks so the next line can be read
                        
                    case "# RentedDVDs":
                        String[] dvdRentData = line.split(",");// this is used to split the data into different segments on the line so they can be looked at e.g. "Integer.parseInt(dvdData[2])"
                        // Checks if the identifyer for the DVD type is correct, ignoring letter case
                        if (dvdRentData[0].equalsIgnoreCase("Movie")) {
                            //Creates a new DVDMovie using DB data
                            DVDMovie movie = new DVDMovie(
                                dvdRentData[1], // title
                                Integer.parseInt(dvdRentData[2]), // price
                                Integer.parseInt(dvdRentData[3]), // rentalperiod
                                Integer.parseInt(dvdRentData[4]), // PricePerDay
                                Integer.parseInt(dvdRentData[5]), // Length
                                dvdRentData[6] // director
                            );
                            //Adds the new data to stores list
                            store.addRentedDVD(movie);
                        } 
                        // Checks if the identifyer is correct ignoring letter case
                        else if (dvdRentData[0].equalsIgnoreCase("Series")) {
                            //Creates a new DVDSeries using the DB data
                            DVDSeries series = new DVDSeries(
                                dvdRentData[1], // title
                                Integer.parseInt(dvdRentData[2]), // price
                                Integer.parseInt(dvdRentData[3]), // rentalperiod
                                Integer.parseInt(dvdRentData[4]), // PricePerDay
                                Integer.parseInt(dvdRentData[5]), // episodes
                                Integer.parseInt(dvdRentData[6]) // ep Length
                            );
                            //adds it to the stores list
                            store.addRentedDVD(series);
                        }
                        break;// breaks so the next line can be read
                    

                    case "# Rentals":
                        String[] rentalData = line.split(",");
                        Customer rentingCustomer = store.getCustomerById(rentalData[0]);
                        DVD rentedDvd = store.getRentedDVDByTitle(rentalData[1]);
                        LocalDate rentalDate = LocalDate.parse(rentalData[2]);
                        if (rentingCustomer != null && rentedDvd != null) {
                            store.rentDVD(rentingCustomer, rentedDvd, rentalDate);
                        }
                        break;
                }
            }
            System.out.println("Database loaded successfully from " + filename);
        } catch (IOException e) {
            System.out.println("Error loading database: " + e.getMessage());
        }
    }
    
    // this is used for saving a database
    public static void saveDatabase(RentalStore store, String filename) {
        // overwrites or creates a file using filename
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            // Write Customers
            writer.write("# Customers\n");
            for (Customer customer : store.getCustomers()) {
                writer.write(String.join(",",
                    customer.getCustomerId(),
                    customer.getName(),
                    customer.getEmail(),
                    String.valueOf(customer.getPhoneNum()),
                    customer.getMembershipStatus()
                ));
                writer.newLine();
            }

            // Write DVDs
            writer.write("\n# DVDs\n");
            for (DVD dvd : store.getAvailableDVDs()) {
                if (dvd instanceof DVDMovie) {
                    DVDMovie movie = (DVDMovie) dvd;
                    writer.write(String.join(",",
                        "Movie",
                        movie.getTitle(),
                        String.valueOf(movie.getPrice()),
                        String.valueOf(movie.getRentalPeriod()), 
                        String.valueOf(movie.getOverduePricePerDay()),
                        String.valueOf(movie.getLength()),
                        movie.getDirector()
                    ));
                } else if (dvd instanceof DVDSeries) {
                    DVDSeries series = (DVDSeries) dvd;
                    writer.write(String.join(",",
                        "Series",
                        series.getTitle(),
                        String.valueOf(series.getPrice()),
                        String.valueOf(series.getRentalPeriod()),
                        String.valueOf(series.getOverduePricePerDay()),
                        String.valueOf(series.getEpNum()),
                        String.valueOf(series.getEpLength())
                    ));
                }
                writer.newLine();
            }
            
            // Write rented DVDs
            writer.write("\n# RentedDVDs\n");
            for (DVD dvd : store.getUnavailableDVDs()) {
                if (dvd instanceof DVDMovie) {
                    DVDMovie movie = (DVDMovie) dvd;
                    writer.write(String.join(",",
                        "Movie",
                        movie.getTitle(),
                        String.valueOf(movie.getPrice()),
                        String.valueOf(movie.getRentalPeriod()), 
                        String.valueOf(movie.getOverduePricePerDay()),
                        String.valueOf(movie.getLength()),
                        movie.getDirector()
                    ));
                } else if (dvd instanceof DVDSeries) {
                    DVDSeries series = (DVDSeries) dvd;
                    writer.write(String.join(",",
                        "Series",
                        series.getTitle(),
                        String.valueOf(series.getPrice()),
                        String.valueOf(series.getRentalPeriod()),
                        String.valueOf(series.getOverduePricePerDay()),
                        String.valueOf(series.getEpNum()),
                        String.valueOf(series.getEpLength())
                    ));
                }
                writer.newLine();
            }
            
            // Write Rentals
            writer.write("\n# Rentals\n");
            for (Rental rental : store.getRentals()) {
                writer.write(String.join(",",
                    rental.getCust().getCustomerId(),
                    rental.getDVD().getTitle(),
                    rental.getRentalDate().toString()
                ));
                writer.newLine();
            }

            System.out.println("Database saved successfully to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving database: " + e.getMessage());
        }
    }
}
