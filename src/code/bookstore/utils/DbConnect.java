package code.bookstore.utils;

import java.sql.*;

public class DbConnect {
    private final String url = System.getenv("DB_URL");
    private final String username = System.getenv("DB_USERNAME");
    private final String password = System.getenv("DB_PASSWORD");

    public Connection connect(){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connect to database successfully");
        } catch(SQLException e){
            e.printStackTrace();
        }
        return conn;
    }
    
    public static void main(String args[]) throws Exception{
        DbConnect newconn = new DbConnect();
        Connection conn = newconn.connect();

        String query = "Select * From books join author using (author_name) join publisher using (publisher_id)";


        Statement stm = null;
        try{
            stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);

            while(rs.next()){
                String id = rs.getString("book_id");
                String name = rs.getString("book_name");
                String author_name = rs.getString("author_name");
                double price = rs.getDouble("price");
                String pub_name = rs.getString("publisher_name");

                System.out.println(id + " - " + name + " - " + author_name + " - " + price + " - " + pub_name);
            }
            conn.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
