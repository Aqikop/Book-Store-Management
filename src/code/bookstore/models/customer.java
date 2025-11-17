package code.bookstore.models;

public class customer {
    private String customerId;
    private String customerName;
    private String address;
    private String city;
    private String zip;
    private String email;
    private String phonenum;

    public customer() {
        this.customerId = null;
        this.customerName = null;
        this.address = null;
        this.city = null;
        this.zip = null;
        this.email = null;
        this.phonenum = null;
    }

    public customer(String customerId, String customerName, String address, String city, String zip, String email, String phonenum){
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.city = city;
        this.zip = zip;
        this.email = email;
        this.phonenum = phonenum;
    }

}
