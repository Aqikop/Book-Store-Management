package code.bookstore.models;

public class order {
    private int orderId;
    private customer customerId;
    private shoppingcart cartId;
    private String orderdate;

    public order(){
        this.orderId = 0;
        this.customerId = null;
        this.cartId = null;
        this.orderdate = null;
    }

    public order(int orderId, customer customerId, shoppingcart cartId, String orderdate){
        this.orderId = orderId;
        this.customerId = customerId;
        this.cartId = cartId;
        this.orderdate = orderdate;
    }

    public void setorderId(int orderId){
        this.orderId = orderId;
    }

    public void setcustomerId(customer customerId){
        this.customerId = customerId;
    }

    public void setcartId(shoppingcart cardId){
        this.cartId = cardId;
    }

    public void setorderdate(String orderdate){
        this.orderdate = orderdate;
    }

    public String getInfo(){
        return "Order{" +
                "orderId:" + orderId + '\'' +
                ", customerId:" + customerId + '\'' +
                ", cartId:" + cartId + '\'' +
                ", orderdate:" + orderdate +
                "}";
    }
}
