package code.bookstore.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import code.bookstore.utils.DbConnect;

public class publisher extends extra{
    private String publisherId;
    private String publisherName;

    public publisher(){
        this.publisherId = null;
        this.publisherName = null;
    }

    public publisher(String publisherId, String publisherName){
        this.publisherId = publisherId;
        this.publisherName = publisherName;
    }

    @Override
    public String generateId(){
        DbConnect newconn = new DbConnect();
        Connection conn = newconn.connect();

        String new_id = null;
        String query = "SELECT 'P' || LPAD(nextval('pub_id_seq')::text, 4, '0') AS publisher_id";

        try {
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet rs = stm.executeQuery();

            if(rs.next()){
                new_id = rs.getString("publisher_id");
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new_id;
    }

    public void setId(String publisherId){
        this.publisherId = publisherId;
    }

    public void setpublisherName(String publisherName){
        this.publisherName = publisherName;
    }

    public String getInfo(){
        return "Publisher{" +
                "publisherId:" + publisherId + '\'' +
                ", publisherName:" + publisherName + 
                "}";
    }

    public static String get_id_by_name(String publisherName){
        DbConnect newconn = new DbConnect();
        Connection conn = newconn.connect();

        String query = "SELECT publisher_id FROM publisher WHERE LOWER(publisher_name) = LOWER(?)";
        try(PreparedStatement stm = conn.prepareStatement(query)){
            stm.setString(1, publisherName);
            ResultSet rs = stm.executeQuery();

            if(rs.next()){
                String publisherId = rs.getString("publisher_id");
                conn.close();
                return publisherId;
            }else{
                conn.close();
                publisher new_publisher = new publisher();
                String new_id = new_publisher.generateId();
                if(new_id != null){
                    boolean added = add_publisher(new_id, publisherName);
                    if(added){
                        return new_id;
                    }
                }
                return null;
            }
            
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static boolean add_publisher(String publisherId, String publisherName){
        DbConnect newconn = new DbConnect();
        Connection conn = newconn.connect();

        String query = "INSERT INTO publisher (publisher_id, publisher_name) VALUES (?, ?)";
        try {
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1, publisherId);
            stm.setString(2, publisherName);
            int rs = stm.executeUpdate();

            conn.close();
            return rs > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}