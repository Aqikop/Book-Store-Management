package code.bookstore.models;

public class history {
    private customer customerId;
    private order orderId;

    public history(){
        this.customerId = null;
        this.orderId = null;
    }

    public history(customer customerId, order orderId){
        this.customerId = customerId;
        this.orderId = orderId;
    }

    public void setcustomerId(customer customerId){
        this.customerId = customerId;
    }

    public void setorderId(order orderId){
        this.orderId = orderId;
    }

    public String getInfo(){
        return "Book{" +
                "customerId:" + customerId + '\'' +
                ", orderId:" + orderId + 
                "}";
    }
}
