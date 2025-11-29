package code.bookstore.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import code.bookstore.utils.DbConnect;

public class customeracc {
    private customer customerId;
    private customer customerName;
    private customer email;
    private String password;

    public customeracc(){
        this.customerId = null;
        this.customerName = null;
        this.email = null;
        this.password = null;
    }

    public customeracc(customer customerId, customer customerName, customer email, String password){
        this.customerId = customerId;
        this.customerName = customerName;
        this.email = email;
        this.password = null;
    }

    public void setpassword(String password){
        this.password = password;
    }

    public static Map<String, Object> login(String email, String password){
        DbConnect newconn = new DbConnect();
        Connection conn = newconn.connect();

        Map<String, Object> res = new HashMap<>();

        String query = "SELECT ca.customer_id, ca.email_address, c.customer_name " +
                      "FROM customer_acc ca " +
                      "LEFT JOIN customer c ON ca.customer_id = c.customer_id " +
                      "WHERE ca.email_address = ? AND ca.password = ?";
        try{
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1, email);
            stm.setString(2, password);

            ResultSet rs = stm.executeQuery();

            if(rs.next()){
                res.put("state", true);
                res.put("customerId", rs.getString("customer_id"));
                res.put("email", rs.getString("email_address"));
                res.put("customerName", rs.getString("customer_name"));
                res.put("message", "Login successfully");
            }
            else{
                res.put("state", false);
                res.put("message", "Invalid email or password");
            }
            conn.close();
        } catch(SQLException e){
            e.printStackTrace();
            res.put("state", false);
            res.put("message", "Database error");
        }

        return res;
    }

    public static void Signup(String name, String email, String password){
        DbConnect newconn = new DbConnect();
        Connection conn = newconn.connect();
    }
}
