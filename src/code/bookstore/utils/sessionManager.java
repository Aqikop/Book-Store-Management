package code.bookstore.utils;

import java.util.Map;
import java.util.HashMap;

public class sessionManager {
    private static sessionManager inst;
    private Map<String, Object> curr_user;
    private boolean is_login;

    private int curr_order_id = 0;

    private sessionManager(){
        this.curr_user = new HashMap<>();
        this.is_login = false;
    }

    public static sessionManager get_inst(){
        if(inst == null){
            inst = new sessionManager();
        }
        return inst;
    }

    public void set_order_id(int orderId){
        this.curr_order_id = orderId;
    }

    public int get_curr_order_id(){
        return curr_order_id;
    }

    public void login_user(String customerId, String email, String customerName){
        curr_user.put("customerId", customerId);
        curr_user.put("email", email);
        curr_user.put("customerName", customerName);
        is_login = true;
        System.out.println("login with:" + email);
    }

    public void logout(){
        curr_user.clear();
        is_login = false;
        System.out.println("Logged out");
    }

    public boolean is_user_login(){
        return is_login;
    }

    public String get_curr_user_id(){
        return is_login ? (String)curr_user.get("customerId") : null;
    }

    public String get_curr_user_email(){
        return is_login ? (String)curr_user.get("email") : null;
    }

    public String get_curr_user_name(){
        return is_login ? (String)curr_user.get("customerName") : null;
    }

    public Map<String, Object> get_curr_user(){
        return new HashMap<>(curr_user);
    }
}
