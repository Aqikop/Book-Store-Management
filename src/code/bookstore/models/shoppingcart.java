package code.bookstore.models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

import code.bookstore.utils.DbConnect;
import code.bookstore.utils.getTime;

public class shoppingcart extends extra{
    private int cardId;
    private books bookId;
    private double price;
    private String date;
    private int quantity;

    public shoppingcart(){
        this.cardId = 0;
        this.bookId = null;
        this.price = 0;
        this.date = null;
        this.quantity = 0;
    }

    public shoppingcart(int cardId, books bookId, double price, String date, int quantity){
        this.cardId = cardId;
        this.bookId = bookId;
        this.price = price;
        this.date = date;
        this.quantity = quantity;
    }

    @Override
    public String generateId(){
        DbConnect newconn = new DbConnect();
        Connection conn = newconn.connect();

        String new_id = null;
        String query = "SELECT nextval('shop_cart_id_seq') AS shopping_cart_id";

        try {
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet rs = stm.executeQuery();

            if(rs.next()){
                new_id = rs.getString("shopping_cart_id");
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new_id;
    }

    public void setcardId(int cardId){
        this.cardId = cardId;
    }
    
    public void setbookId(books bookId){
        this.bookId = bookId;
    }

    public void setprice(double price){
        this.price = price;
    }

    public void setdate(String date){
        this.date = date;
    }

    public void setquantity(int quantity){
        this.quantity = quantity;
    }

    public String getInfo(){
        return "Shopping cart{" +
                "cardId:" + cardId + '\'' +
                ", bookId:" + bookId + '\'' +
                ", price:" + price + '\'' +
                ", date:" + date + '\'' +
                ", quantity:" + quantity + 
                "}";
    }

    public static int save_checkout_items(List<Map<String, Object>> checkoutItems) {
        DbConnect newconn = new DbConnect();
        Connection conn = newconn.connect();
        
        shoppingcart new_shoppingcart = new shoppingcart();
        int cartId = Integer.parseInt(new_shoppingcart.generateId());

        getTime new_time = new getTime();
        String current_date = new_time.getCurrentTime();
        Date sql_format = Date.valueOf(current_date);
        
        String query = "INSERT INTO shopping_cart (shopping_cart_id, book_id, price, shopping_date, quantity) VALUES (?, ?, ?, ?, ?)";
        try {
            for(Map<String, Object> i : checkoutItems){
                PreparedStatement stm = conn.prepareStatement(query);
                stm.setInt(1, cartId);
                stm.setString(2, (String) i.get("bookId"));
                stm.setDouble(3, (Double) i.get("price"));
                stm.setDate(4, sql_format);
                stm.setInt(5, (Integer) i.get("quantity"));

                stm.executeUpdate();
            }
            conn.close();
            System.out.println("shopping cart saved, cart ID: " + cartId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return cartId;
    }

    public static List<Map<String, Object>> get_cart_items(int cartId) {
        DbConnect newconn = new DbConnect();
        Connection conn = newconn.connect();
        
        List<Map<String, Object>> cartItems = new ArrayList<>();
        String query = "SELECT sc.*, b.book_name, b.author_name, b.publisher_name " +
                      "FROM shopping_cart sc " +
                      "JOIN books b ON sc.book_id = b.book_id " +
                      "WHERE sc.shopping_cart_id = ?";
        
        try {
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setInt(1, cartId);
            ResultSet rs = stm.executeQuery();
            
            while(rs.next()) {
                Map<String, Object> item = new HashMap<>();
                item.put("bookId", rs.getString("book_id"));
                item.put("bookName", rs.getString("book_name"));
                item.put("authorName", rs.getString("author_name"));
                item.put("publisherName", rs.getString("publisher_name"));
                item.put("price", rs.getDouble("price"));
                item.put("quantity", rs.getInt("quantity"));
                item.put("totalPrice", rs.getDouble("price") * rs.getInt("quantity"));
                cartItems.add(item);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return cartItems;
    }
}