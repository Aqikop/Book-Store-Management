package code.bookstore.models;

abstract class books extends extra{
    private String bookId;
    private String bookName;
    private String authorId;
    private double price;
    private String publisherId;

    public books() {
        this.bookId = null;
        this.bookName = null;
        this.authorId = null;
        this.price = 0;
        this.publisherId = null;
    }

    public books(String bookId, String bookName, String authorId, double price, String publisherId){
        this.bookId = bookId;
        this.bookName = bookName;
        this.authorId = authorId;
        this.price = price;
        this.publisherId = publisherId;
    }

    public void setId(String bookId){
        this.bookId = bookId;
    }

    public void setbookName(String bookName){
        this.bookName = bookName;
    }

    public void setauthorId(String authorId){
        this.authorId = authorId;
    }

    public void setprice(double price){
        this.price = price;
    }

    public void setpublisherId(String publisherId){
        this.publisherId = publisherId;
    }

    public String getInfo(){
        return "Book{" +
                "bookId:" + bookId + '\'' +
                ", bookName:" + bookName + '\'' +
                ", authorId:" + authorId + '\'' +
                ", publisherId:" + publisherId + '\'' +
                ", price:" + price + 
                "}";
    }
}
