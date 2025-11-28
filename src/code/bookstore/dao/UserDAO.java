package code.bookstore.dao;

import code.bookstore.models.User;

public class UserDAO {

    public User findByEmail(String email) {
        // TODO: Replace with real DB query later
        if (email.equals("test@gmail.com")) {
            return new User("Test", "User", "test@gmail.com", "123456");
        }
        return null;
    }
}
