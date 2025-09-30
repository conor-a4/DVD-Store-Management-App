
public class DVDMovie extends DVD
{
   private int length;       // Movie length in minutes
   private String director;  // Director's name

    // Constructor
    public DVDMovie(String title, int price, int rentalPeriod, int overduePricePerDay, int length, String director) {
        super(title, price, rentalPeriod, overduePricePerDay); // Call the parent class constructor
        this.length = length;
        this.director = director;
    }

    // Getters
    public int getLength() {
        return length;
    }

    public String getDirector() {
        return director;
    }
    
    // setters
    public void setLength(int length) {
        this.length = length;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Override
    public String toString() {
        return super.toString() +"\n" +
               " DVDMovie{" +"\n" +
               " length=" + length +"\n" +
               " director='" + director + '\'' +
               '}' + "\n" ;
    }
}
