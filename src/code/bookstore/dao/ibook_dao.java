package code.bookstore.dao;

import java.util.Map;
import java.util.List;

public interface ibook_dao {
    List<Map<String, Object>> get_all_books();
    List<Map<String, Object>> search_by_name(String title);
    boolean add_book(String bookId, String bookName, String authorId, double price, String publisherId);
}
