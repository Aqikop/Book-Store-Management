package code.bookstore.controllers;

import java.util.Map;
import java.util.HashMap;

import code.bookstore.models.books;
import code.bookstore.models.author;
import code.bookstore.models.publisher;

public class book_controller {
    public Map<String, Object> addBook(String title, String author_name, String publisher_name, Double price){
        if(title == null || title.trim().isEmpty() ||
            author_name == null || author_name.trim().isEmpty() ||
            publisher_name == null || publisher_name.trim().isEmpty() ||
            price <= 0){
                Map<String, Object> addBook_failed = new HashMap<>();
                addBook_failed.put("state", false);
                addBook_failed.put("message", "Empty or Invalid fields");
                return addBook_failed;
            }
        
        String author_id = author.get_id_by_name(author_name);
        if(author_id == null){
            Map<String, Object> addBook_failed = new HashMap<>();
            addBook_failed.put("state", false);
            addBook_failed.put("message", "Failed to create or get author ID");
            return addBook_failed;
        }

        String publisher_id = publisher.get_id_by_name(publisher_name);
        if(publisher_id == null){
            Map<String, Object> addBook_failed = new HashMap<>();
            addBook_failed.put("state", false);
            addBook_failed.put("message", "Failed to create or get publisher ID");
            return addBook_failed;
        }

        books new_books = new books();
        String book_id = new_books.generateId();
        if(book_id == null){
            Map<String, Object> addBook_failed = new HashMap<>();
            addBook_failed.put("state", false);
            addBook_failed.put("message", "Failed to create or get book ID");
            return addBook_failed;
        }

        boolean is_added = new_books.insert_book(book_id, title.trim(), author_id, price, publisher_id);
        Map<String, Object> res = new HashMap<>();
        res.put("state", is_added);
        res.put("message", is_added ? "Book added success" : "Failed to add book");
        return res;
    }
}
