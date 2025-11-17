package code.bookstore.models;

public class customer {
    private String customerId;
    private String customerName;
    private String address;
    private String city;
    private String zip;
    private String email;
    private String phonenum;
    private String cardnum;

    public customer() {
        this.customerId = null;
        this.customerName = null;
        this.address = null;
        this.city = null;
        this.zip = null;
        this.email = null;
        this.phonenum = null;
        this.cardnum = null;
    }

    public customer(String customerId, String customerName, String address, String city, String zip, String email, String phonenum, String cardnum){
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.city = city;
        this.zip = zip;
        this.email = email;
        this.phonenum = phonenum;
        this.cardnum = cardnum;
    }

    public void setcustomerId(String customerId){
        this.customerId = customerId;
    }

    public void setcustomerName(String customerName){
        this.customerName = customerName;
    }

    public void setaddress(String address){
        this.address = address;
    }

    public void setcity(String city){
        this.city = city;
    }

    public void setzip(String zip){
        this.zip = zip;
    }

    public void setemail(String email){
        this.email = email;
    }

    public void setphonenumber(String phonenum){
        this.phonenum = phonenum;
    }

    public void setcardnum(String cardnum){
        this.cardnum = cardnum;
    }

    public String getInfo(){
        return "Customer{" +
                "customerId:" + customerId + '\'' +
                ", customerName:" + customerName + '\'' +
                ", customerName:" + customerName + '\'' +
                ", address:" + address + '\'' +
                ", city:" + city + '\'' +
                ", zip code:" + zip + '\'' +
                ", email:" + email + '\'' +
                ", phone number:" + phonenum + '\'' +
                ", credit card number:" + cardnum +
                "}";
    }
}
