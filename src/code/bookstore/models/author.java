package code.bookstore.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import code.bookstore.utils.DbConnect;

public class author extends extra{
    private String authorId;
    private String authorName;
    
    public author(){
        this.authorId = null;
        this.authorName = null;
    }

    public author(String authorId, String authorName){
        this.authorId = authorId;
        this.authorName = authorName;
    }

    @Override
    public String generateId(){
        DbConnect newconn = new DbConnect();
        Connection conn = newconn.connect();

        String new_id = null;
        String query = "SELECT 'A' || LPAD(nextval('auth_id_seq')::text, 4, '0') AS author_id";

        try {
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet rs = stm.executeQuery();

            if(rs.next()){
                new_id = rs.getString("author_id");
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new_id;
    }

    public void setId(String authorId){
        this.authorId = authorId;
    }

    public void setauthorName(String authorName){
        this.authorName = authorName;
    }

    public String getInfo(){
        return "Author{" +
                "authorId:" + authorId + '\'' +
                ", authorName:" + authorName + 
                "}";
    }

    public static String get_id_by_name(String authorName){
        DbConnect newconn = new DbConnect();
        Connection conn = newconn.connect();

        String query = "SELECT author_id FROM author WHERE LOWER(author_name) = LOWER(?)";
        try(PreparedStatement stm = conn.prepareStatement(query)){
            stm.setString(1, authorName);
            ResultSet rs = stm.executeQuery();
            
            if(rs.next()){
                String authorId = rs.getString("author_id");
                conn.close();
                return authorId;
            }
            else{ 
                conn.close();
                author new_author = new author(); 
                String new_id = new_author.generateId();
                
                if(new_id != null){
                    boolean added = add_author(new_id, authorName);
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

    public static boolean add_author(String authorId, String authorName){
        DbConnect newconn = new DbConnect();
        Connection conn = newconn.connect();

        String query = "INSERT INTO author (author_id, author_name) VALUES (?, ?)";
        try {
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1, authorId);
            stm.setString(2, authorName);
            int rs = stm.executeUpdate();

            conn.close();
            return rs > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
