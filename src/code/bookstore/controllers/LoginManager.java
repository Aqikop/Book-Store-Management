package code.bookstore.controllers;

import code.bookstore.models.User;
import code.bookstore.dao.UserDAO;

// This class handles all login-related logic.
public class LoginManager {

    private UserDAO userDAO;

    public LoginManager() {
        this.userDAO = new UserDAO();
    }

    // Validate and attempt login
    public boolean login(String email, String password) {

        // Basic validation
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            return false;
        }

        // Check if the email exists in database
        User user = userDAO.findByEmail(email);
        if (user == null) {
            return false;
        }

        // Compare password
        if (user.getPassword().equals(password)) {
            Session.currentUser = user; // Save session
            return true;
        }

        return false;
    }
}
