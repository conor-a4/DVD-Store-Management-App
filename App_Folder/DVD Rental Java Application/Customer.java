
public class Customer
{
    private String customerId;// CXXX
    private String name; // full name
    private String email; // email for cust
    private long phoneNum; // phone number for cust
    private String membershipStatus; // either copper, silver, gold
    
    // Constructork
    public Customer(String customerId, String name, String email, long phoneNum, String membershipStatus) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.phoneNum = phoneNum;
        this.membershipStatus = membershipStatus;
    }
    
    // gets
    public String getCustomerId() {
        return customerId;
    }
    
    public String getName() {
        return name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public long getPhoneNum() {
        return phoneNum;
    }
    
    public String getMembershipStatus() {
        return membershipStatus;
    }
    
    // sets
    public void setMembershipStatus(String membershipStatus) {
        this.membershipStatus = membershipStatus;
    }
    
    //polymorphism
    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", Phone Number=" + phoneNum + '\'' +
                ", membershipStatus='" + membershipStatus + '\'' +
                '}';
    }
}


