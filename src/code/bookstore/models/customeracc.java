package code.bookstore.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import code.bookstore.utils.DbConnect;

public class customeracc extends extra{
    private String customerId;
    private customer customerName;
    private customer email;
    private String password;

    public customeracc(){
        this.customerId = null;
        this.customerName = null;
        this.email = null;
        this.password = null;
    }

    public customeracc(String customerId, customer customerName, customer email, String password){
        this.customerId = customerId;
        this.customerName = customerName;
        this.email = email;
        this.password = null;
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

    public void setId(){
        this.customerId = generateId();
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

    public static Map<String, Object> signup(String name, String email, String password){
        DbConnect newconn = new DbConnect();
        Connection conn = newconn.connect();
        
        Map<String, Object> res = new HashMap<>();
        try{
            String email_check = "SELECT email_address FROM customer_acc WHERE email_address = ?";
            PreparedStatement email_check_stm = conn.prepareStatement(email_check);
            email_check_stm.setString(1, email);
            ResultSet check_rs = email_check_stm.executeQuery();

            if(check_rs.next()){
                res.put("state", false);
                res.put("message", "Email already taken");
                return res;
            }

            conn.setAutoCommit(false);

            String customerId = null;
            customeracc new_customeracc = new customeracc();
            customerId = new_customeracc.generateId();

            if(customerId == null){
                conn.rollback();
                res.put("state", false);
                res.put("message", "Generate customer ID failed");
                return res;
            }

            //Insert value to customer table
            String cus_query = "INSERT INTO customer (customer_id, customer_name, email_address) VALUES (?, ?, ?)";
            PreparedStatement cus_stm = conn.prepareStatement(cus_query);
            cus_stm.setString(1, customerId);
            cus_stm.setString(2, name);
            cus_stm.setString(3, email);
            cus_stm.executeUpdate();

            //Insert value to customer_acc table
            String acc_query = "INSERT INTO customer_acc (customer_id, email_address, password) VALUES (?, ?, ?)";
            PreparedStatement acc_stm = conn.prepareStatement(acc_query);
            acc_stm.setString(1, customerId);
            acc_stm.setString(2, email);
            acc_stm.setString(3, password);
            acc_stm.executeUpdate();

            conn.commit();

            res.put("state", true);
            res.put("customerId", customerId);
            res.put("message", "New account created successful");

        } catch(SQLException e){
            try{
                conn.rollback();
            } catch(SQLException rollback_e){
                rollback_e.printStackTrace();
            }
            e.printStackTrace();
            res.put("state", false);
            res.put("message", "Failed: " + e.getMessage());
        } finally{
            try {
                conn.setAutoCommit(true);
                if(conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return res;
    }
}
