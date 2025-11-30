package code.bookstore.controllers;

import java.util.Map;
import java.util.HashMap;
import code.bookstore.models.customeracc;
import code.bookstore.utils.sessionManager;

public class user_controller {
    private sessionManager session_Manager;

    public user_controller(){
        this.session_Manager = sessionManager.get_inst();
    }

    public Map<String, Object> login_user(String email, String password){
        if(email == null || password == null || email.trim().isEmpty() || password.trim().isEmpty()){
            Map<String, Object> login_failed = new HashMap<>();
            login_failed.put("state", false);
            login_failed.put("message", "Email and password are needed");
            return login_failed;
        }

        Map<String, Object> login_res = customeracc.login(email.trim(), password);
        if((Boolean)login_res.get("state")){
            String customerId = (String) login_res.get("customerId");
            String customerName = (String) login_res.get("customerName");
            session_Manager.login_user(customerId, email, customerName);
        }

        return login_res;
    }

    public Map<String, Object> signup_user(String name, String email, String password){
        if(name == null || email == null || password == null || 
            name.trim().isEmpty() || email.trim().isEmpty() || password.trim().isEmpty()){
            Map<String, Object> signup_failed = new HashMap<>();
            signup_failed.put("state", false);
            signup_failed.put("message", "Enter all fields to continue");
            return signup_failed;
        }

        // Email check
        if(!email.contains("@") || !email.contains(".")){
            Map<String, Object> signup_failed = new HashMap<>();
            signup_failed.put("state", false);
            signup_failed.put("message", "Enter a valid email address");
            return signup_failed;
        }

        return customeracc.signup(name.trim(), email.trim(), password);
    }

    public void logout_user(){
        session_Manager.logout();
    }

    public boolean is_logged_in(){
        return session_Manager.is_user_login();
    }

    public String get_curr_id(){
        return session_Manager.get_curr_user_id();
    }

    public String get_curr_email(){
        return session_Manager.get_curr_user_email();
    }

    public String get_curr_name(){
        return session_Manager.get_curr_user_name();
    }
}
