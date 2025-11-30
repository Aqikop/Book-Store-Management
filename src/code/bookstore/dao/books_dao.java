package code.bookstore.dao;

import java.sql.*;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

import code.bookstore.utils.DbConnect;

public class books_dao implements ibook_dao{
    @Override
    public List<Map<String, Object>> get_all_books(){
        DbConnect newconn = new DbConnect();
        Connection conn = newconn.connect();

        List<Map<String, Object>> result_list = new ArrayList<>();
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
        return result_list;
    };

    @Override
    public List<Map<String, Object>> search_by_name(String title){
        DbConnect newconn = new DbConnect();
        Connection conn = newconn.connect();

        List<Map<String, Object>> result_list = new ArrayList<>();
        if(title != null){
            String query = "SELECT b.book_id, b.book_name, b.price, a.author_name, p.publisher_name " +
                "FROM books b " +
                "JOIN author a ON b.author_id = a.author_id " +
                "JOIN publisher p ON b.publisher_id = p.publisher_id " +
                "WHERE LOWER(b.book_name) LIKE LOWER(?) ";

            try (PreparedStatement  stm = conn.prepareStatement(query)){
                stm.setString(1, "%" + title + "%");
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
        } else{
            result_list = get_all_books();
        }
        return result_list;
    };

    @Override 
    public boolean add_book(String bookId, String bookName, String authorId, double price, String publisherId){
        DbConnect newconn = new DbConnect();
        Connection conn = newconn.connect();

        String query = "INSERT INTO books (book_id, book_name, author_id, price, publisher_id) VALUES (?, ?, ?, ?, ?)";
        try(PreparedStatement stm = conn.prepareStatement(query)) {
            stm.setString(1, bookId);
            stm.setString(2, bookName);
            stm.setString(3, authorId);
            stm.setDouble(4, price);
            stm.setString(5, publisherId);

            int res = stm.executeUpdate();
            conn.close();

            return res > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;
        }
    };
}
