package code.bookstore.controllers;

import java.util.*;

import code.bookstore.models.publisher;

public class checkout_controller {
    private Map<String, Object> buy_now_item;
    private List<Map<String, Object>> cart_items;

    public checkout_controller(){
        this.buy_now_item = new HashMap<>();
        this.cart_items = new ArrayList<>();
    }

    public void set_buy_now_item(Map<String,Object> item){
        this.buy_now_item = new HashMap<>(item);
    }

    public void set_cart_items(List<Map<String, Object>> items){
        this.cart_items = new ArrayList<>();
        for(Map<String, Object> i : items){
            this.cart_items.add(new HashMap<>(i));
        }
    }

    public Map<String, Object> get_buy_now_item(){
        return buy_now_item;
    }

    public List<Map<String, Object>> get_cart_items(){
        return cart_items;
    }
}
