package code.bookstore.models;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import code.bookstore.utils.DbConnect;

public class customer extends extra{
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

    @Override
    public String generateId(){
        DbConnect newconn = new DbConnect();
        Connection conn = newconn.connect();

        String new_id = null;
        String query = "SELECT 'c' || LPAD(nextval('cust_id_seq')::text, 4, '0') AS customer_id";

        try {
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet rs = stm.executeQuery();

            if(rs.next()){
                new_id = rs.getString("customer_id");
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new_id;
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

    public static Map<String, Object> find_info_by_email(String email){
        DbConnect newconn = new DbConnect();
        Connection conn = newconn.connect();

        Map<String, Object> cus_info = new HashMap<>();
        String query = "SELECT * FROM customer where email_address = ?";

        try {
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1, email);
            ResultSet rs = stm.executeQuery();

            if(rs.next()){
                cus_info.put("customerId", rs.getString("customer_id"));
                cus_info.put("customerName", rs.getString("customer_name"));
                cus_info.put("address", rs.getString("street_address"));
                cus_info.put("city", rs.getString("city"));
                cus_info.put("zip", rs.getString("zip"));
                cus_info.put("state", rs.getString("state"));
                cus_info.put("email", rs.getString("email_address"));
                cus_info.put("phoneNumber", rs.getString("phone_number"));
                cus_info.put("creditCard", rs.getString("credit_card_number"));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cus_info;
    }

    public static boolean update_cus_info(String customerId, String fname, String lname, String address, String city, String zip, String state, String phone_num, String cc){
        DbConnect newconn = new DbConnect();
        Connection conn = newconn.connect();

        String name = fname + " " + lname;
        String query = "UPDATE customer SET customer_name = ?, street_address = ?, city = ?, zip = ?, state = ?, phone_number = ?, credit_card_number = ? WHERE customer_id = ?";

        try {
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1, name);
            stm.setString(2, address);
            stm.setString(3, city);
            stm.setString(4, zip);
            stm.setString(5, state);
            stm.setLong(6, Long.parseLong(phone_num));
            stm.setLong(7, Long.parseLong(cc));
            stm.setString(8, customerId);

            int res = stm.executeUpdate();
            conn.close();
            return res > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}