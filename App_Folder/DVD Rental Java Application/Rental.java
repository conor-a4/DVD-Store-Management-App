
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Rental
{
    protected Customer customer; // a customer to tie the rental to (from Customer) || Protected so rentalStore can use it
    protected DVD dvd; // the dvd thats rented (from DVD) || Protected so rentalStore can use it
    private LocalDate rentalDate; // date the rent happens
    private LocalDate returnDate; // date its returned
    
    // Constructor 
    public Rental(Customer customer, DVD dvd, LocalDate rentalDate) {
        this.customer = customer;
        this.dvd = dvd;
        this.rentalDate = rentalDate;
        this.returnDate = null; // Not returned yet
    }
    
    // gets 
    public Customer getCust(){
        return customer;
    }
    
    public DVD getDVD(){
        return dvd;
    }
    
    public LocalDate getRentalDate(){
        return rentalDate;
    }
    
    public LocalDate getReturnDate(){
        return returnDate;
    }
    
    // Used for calculating if they are returned late and the fees
    public double returnDVD(LocalDate returnDate) {
        this.returnDate = returnDate; // sets the date now that its returned

        // Calculate the number of days the DVD was rented
        long rentalDuration = ChronoUnit.DAYS.between(rentalDate, returnDate);

        // Check if it's overdue and if it is calculate the overdue charge
        if (rentalDuration > dvd.getRentalPeriod()) {
            // Calculate the overdue fee
            long overdueDays = rentalDuration - dvd.getRentalPeriod();
            double overduePrice = overdueDays * dvd.getOverduePricePerDay();
            System.out.println("Overdue! You owe " + overduePrice + " for " + overdueDays + " overdue day(s).");
            return overduePrice;
        } else {
            System.out.println("No overdue charges.");
            return 0.0;
        }
    }

    @Override
    public String toString() {
        return "Rental{" +
               "customer=" + customer +
               ", dvd=" + dvd.title +
               ", rentalDate=" + rentalDate +
               ", returnDate=" + returnDate +
               '}';
    }
}
