
public class DVDSeries extends DVD {
    private int epNum; // amount of episodes 
    private int epLength; // ep length in mins
    
    // Constructor
    public DVDSeries (String title, int price, int rentalPeriod, int overduePricePerDay, int epNum, int epLength){
        super(title, price, rentalPeriod, overduePricePerDay);
        this.epNum = epNum;
        this.epLength = epLength;
    }
    
    // gets
    
    public final int getEpNum() {
        return epNum;
    }

    public final int getEpLength() {
        return epLength;
    }
    
    // sets
    public void setEpNum(int epNum) {
        this.epNum = epNum;
    }

    public void setEpLength(int epLength) {
        this.epLength = epLength;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +// Include the parent class's toString() output
                " DVDsSeries{" +"\n" +
                " epNum=" + epNum +"\n" +
                " epLength=" + epLength + 
                '}' + "\n";
    }
}
