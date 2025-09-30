
public abstract class DVD
{
    protected String title; // used for string tostring in rental so prot
    private int price; // in euro
    private int rentalPeriod; // in days
    private int overduePricePerDay; // price per day overdue
    
    // constructor
    public DVD (String title, int price, int rentalPeriod, int overduePricePerDay){
        this.title = title;
        this.price = price;
        this.rentalPeriod = rentalPeriod;
        this.overduePricePerDay = overduePricePerDay;
    }
    
    // gets 
     public final String getTitle() {
        return title;
    }

    public final int getPrice() {
        return price;
    }

    public final int getRentalPeriod() {
        return rentalPeriod;
    }
    
    public final int getOverduePricePerDay(){
        return overduePricePerDay;
    }
    
    // sets
    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
    // base for children to override
    @Override
    public String toString() {
        return "\n" +"DVD{" +
               " title='" + title + '\'' + "\n" +
               " price=" + price +"\n" +
               " rentalPeriod=" + rentalPeriod +" days" + "\n" +
               " overduePricePerDay=" + overduePricePerDay + "}";
    }
}
