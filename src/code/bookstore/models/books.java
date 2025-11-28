package code.bookstore.models;

import java.sql.*;
import java.util.*;

import code.bookstore.utils.DbConnect;

public class books extends extra{
    private String bookId;
    private String bookName;
    private String authorId;
    private double price;
    private String publisherId;

    public books() {
        this.bookId = null;
        this.bookName = null;
        this.authorId = null;
        this.price = 0;
        this.publisherId = null;
    }

    public books(String bookId, String bookName, String authorId, double price, String publisherId){
        this.bookId = bookId;
        this.bookName = bookName;
        this.authorId = authorId;
        this.price = price;
        this.publisherId = publisherId;
    }

    @Override
    public void setId(){

    }

    public void setId(String bookId){
        this.bookId = bookId;
    }

    public void setbookName(String bookName){
        this.bookName = bookName;
    }

    public void setauthorId(String authorId){
        this.authorId = authorId;
    }

    public void setprice(double price){
        this.price = price;
    }

    public void setpublisherId(String publisherId){
        this.publisherId = publisherId;
    }

    public String getInfo(){
        return "Book{" +
                "bookId:" + bookId + '\'' +
                ", bookName:" + bookName + '\'' +
                ", authorId:" + authorId + '\'' +
                ", publisherId:" + publisherId + '\'' +
                ", price:" + price + 
                "}";
    }

    public static List<Map<String, Object>> search_books(String keyword){
        DbConnect newconn = new DbConnect();
        Connection conn = newconn.connect();

        List<Map<String, Object>> result_list = new ArrayList<>();

        if(keyword != null){
            String query = "SELECT b.book_id, b.book_name, b.price, a.author_name, p.publisher_name " +
                "FROM books b " +
                "JOIN author a ON b.author_id = a.author_id " +
                "JOIN publisher p ON b.publisher_id = p.publisher_id " +
                "WHERE b.book_name LIKE ?";

            try (PreparedStatement  stm = conn.prepareStatement(query)){
                stm.setString(1, "%" + keyword + "%");
                ResultSet rs = stm.executeQuery();

                while(rs.next()){
                    Map<String, Object> book = new HashMap<>();
                    book.put("bookId", rs.getString("book_id"));
                    book.put("bookName", rs.getString("book_name"));
                    book.put("authorName", rs.getString("author_name"));
                    book.put("publisherName", rs.getString("publisher_name"));
                    book.put("price", rs.getDouble("price"));
                    result_list.add(book);
                }
                conn.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }
        else{
            String query = "SELECT b.book_id, b.book_name, b.price, a.author_name, p.publisher_name " +
                "FROM books b " +
                "JOIN author a ON b.author_id = a.author_id " +
                "JOIN publisher p ON b.publisher_id = p.publisher_id ";

            try (PreparedStatement  stm = conn.prepareStatement(query)){
                ResultSet rs = stm.executeQuery();

                while(rs.next()){
                    Map<String, Object> book = new HashMap<>();
                    book.put("bookId", rs.getString("book_id"));
                    book.put("bookName", rs.getString("book_name"));
                    book.put("authorName", rs.getString("author_name"));
                    book.put("publisherName", rs.getString("publisher_name"));
                    book.put("price", rs.getDouble("price"));
                    result_list.add(book);
                }
                conn.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }
        return result_list;
    }
}