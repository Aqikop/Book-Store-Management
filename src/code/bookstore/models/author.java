package code.bookstore.models;

public class author{
    private String authorId;
    private String authorName;
    
    public author(){
        this.authorId = null;
        this.authorName = null;
    }

    public author(String authorId, String authorName){
        this.authorId = authorId;
        this.authorName = authorName;
    }

    public void setId(String authorId){
        this.authorId = authorId;
    }

    public void setName(String authorName){
        this.authorName = authorName;
    }

    public String getInfo(){
        return "Author{" +
                "authorId:" + authorId + '\'' +
                ", authorName:" + authorName + 
                "}";
    }
}
