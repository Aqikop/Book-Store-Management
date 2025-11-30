package code.bookstore.models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import code.bookstore.utils.DbConnect;
import code.bookstore.utils.getTime;

public class order extends extra{
    private int orderId;
    private customer customerId;
    private shoppingcart cartId;
    private String orderdate;
    

    public order(){
        this.orderId = 0;
        this.customerId = null;
        this.cartId = null;
        this.orderdate = null;
    }

    public order(int orderId, customer customerId, shoppingcart cartId, String orderdate){
        this.orderId = orderId;
        this.customerId = customerId;
        this.cartId = cartId;
        this.orderdate = orderdate;
    }

    @Override
    public String generateId(){
        DbConnect newconn = new DbConnect();
        Connection conn = newconn.connect();

        String new_id = null;
        String query = "SELECT nextval('od_id_seq') AS order_id";

        try {
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet rs = stm.executeQuery();

            if(rs.next()){
                new_id = rs.getString("order_id");
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new_id;
    }

    public void setorderId(int orderId){
        this.orderId = orderId;
    }

    public void setcustomerId(customer customerId){
        this.customerId = customerId;
    }

    public void setcartId(shoppingcart cardId){
        this.cartId = cardId;
    }

    public void setorderdate(String orderdate){
        this.orderdate = orderdate;
    }

    public String getInfo(){
        return "Order{" +
                "orderId:" + orderId + '\'' +
                ", customerId:" + customerId + '\'' +
                ", cartId:" + cartId + '\'' +
                ", orderdate:" + orderdate +
                "}";
    }

    public static int create_order(String customerId, String shippingType, int shoppingCartId){
        DbConnect newconn = new DbConnect();
        Connection conn = newconn.connect();

        order new_order = new order();
        int orderId = Integer.parseInt(new_order.generateId());

        getTime new_time = new getTime();
        String current_date = new_time.getCurrentTime();
        Date sql_format = Date.valueOf(current_date);
        
        String query = "INSERT INTO order_details (order_id, customer_id, shipping_type, date_of_purchase, shopping_cart_id) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setInt(1, orderId);
            stm.setString(2, customerId);
            stm.setString(3, shippingType);
            stm.setDate(4, sql_format);
            stm.setInt(5, shoppingCartId);

            int res = stm.executeUpdate();
            conn.close();

            if(res > 0){
                return orderId;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public static Map<String, Object> get_order(int orderId){
        DbConnect newconn = new DbConnect();
        Connection conn = newconn.connect();

        Map<String, Object> order_detail = new HashMap<>();
        String query = "SELECT od.*, c.customer_name, c.street_address, c.city, c.zip, c.state " +
                      "FROM order_details od " +
                      "JOIN customer c ON od.customer_id = c.customer_id " +
                      "WHERE od.order_id = ?";

        try {
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setInt(1, orderId);
            ResultSet rs = stm.executeQuery();

            if(rs.next()){
                order_detail.put("orderId", rs.getInt("order_id"));
                order_detail.put("customerId", rs.getString("customer_id"));
                order_detail.put("customerName", rs.getString("customer_name"));
                order_detail.put("address", rs.getString("street_address"));
                order_detail.put("city", rs.getString("city"));
                order_detail.put("zip", rs.getString("zip"));
                order_detail.put("state", rs.getString("state"));
                order_detail.put("shippingType", rs.getString("shipping_type"));
                order_detail.put("dateOfPurchase", rs.getDate("date_of_purchase"));
                order_detail.put("shoppingCartId", rs.getInt("shopping_cart_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();   
        }
        return order_detail;
    }
}
