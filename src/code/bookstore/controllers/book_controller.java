package code.bookstore.controllers;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import code.bookstore.dao.*;
import code.bookstore.models.books;
import code.bookstore.models.author;
import code.bookstore.models.publisher;

public class book_controller implements ibook_dao{
    public ibook_dao bookDao;

    public book_controller(){
        this.bookDao = new books_dao();
    }

    public book_controller(ibook_dao bookDao){
        this.bookDao = bookDao;
    }

    @Override
    public List<Map<String, Object>> get_all_books(){
        try {
            return bookDao.get_all_books();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<Map<String, Object>> search_by_name(String title){
        try{
            if(title == null || title.trim().isEmpty()){
                return get_all_books();
            }
            return bookDao.search_by_name(title);
        } catch(Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public boolean add_book(String bookId, String bookName, String authorId, double price, String publisherId){
        try {
            return bookDao.add_book(bookId, bookName, authorId, price, publisherId);
        } catch (Exception e) {
           e.printStackTrace();
           return false;
        }
    }

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

        boolean is_added = bookDao.add_book(book_id, title.trim(), author_id, price, publisher_id);
        Map<String, Object> res = new HashMap<>();
        res.put("state", is_added);
        res.put("message", is_added ? "Book added success" : "Failed to add book");
        return res;
    }
}
