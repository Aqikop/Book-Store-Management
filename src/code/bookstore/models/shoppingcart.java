package code.bookstore.models;

abstract class shoppingcart extends extra{
    private int cartId;
    private books bookId;
    private double price;
    private String date;
    private int quantity;

    public shoppingcart(){
        this.cartId = 0;
        this.bookId = null;
        this.price = 0;
        this.date = null;
        this.quantity = 0;
    }

    public shoppingcart(int cardId, books bookId, double price, String date, int quantity){
        this.cartId = cardId;
        this.bookId = bookId;
        this.price = price;
        this.date = date;
        this.quantity = quantity;
    }

    public void setId(int cardId){
        this.cartId = cardId;
    }
    
    public void setbookId(books bookId){
        this.bookId = bookId;
    }

    public void setprice(double price){
        this.price = price;
    }

    public void setdate(String date){
        this.date = date;
    }

    public void setquantity(int quantity){
        this.quantity = quantity;
    }

    public String getInfo(){
        return "Shopping cart{" +
                "cartId:" + cartId + '\'' +
                ", bookId:" + bookId + '\'' +
                ", price:" + price + '\'' +
                ", date:" + date + '\'' +
                ", quantity:" + quantity + 
                "}";
    }
}
