package code.bookstore.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import code.bookstore.utils.DbConnect;


public class shippingtype {
    private String ship_type;
    private double ship_price;

    public shippingtype(){
        this.ship_type = null;
        this.ship_price = 0.0;
    }

    public shippingtype(String ship_type, Double ship_price){
        this.ship_type = ship_type;
        this.ship_price = ship_price;
    }

    public static Map<String, Double> get_shipping_types(){
        DbConnect newconn = new DbConnect();
        Connection conn = newconn.connect();

        Map<String, Double> ship_options = new HashMap<>();
        String query = "SELECT shipping_type, shipping_price FROM shipping_type ORDER BY shipping_price";

        try {
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet rs = stm.executeQuery();

            while(rs.next()){
                String type = rs.getString("shipping_type");
                Double price = rs.getDouble("shipping_price");
                ship_options.put(type, price);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ship_options;
    }
}
