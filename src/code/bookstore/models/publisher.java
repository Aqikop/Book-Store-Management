package code.bookstore.models;

public class publisher {
    private String publisherId;
    private String publisherName;

    public publisher(){
        this.publisherId = null;
        this.publisherName = null;
    }

    public publisher(String publisherId, String publisherName){
        this.publisherId = publisherId;
        this.publisherName = publisherName;
    }

    public void setpublisherId(String publisherId){
        this.publisherId = publisherId;
    }

    public void setpublisherName(String publisherName){
        this.publisherName = publisherName;
    }

    public String getInfo(){
        return "Publisher{" +
                "publisherId:" + publisherId + '\'' +
                ", publisherName:" + publisherName + 
                "}";
    }
}
