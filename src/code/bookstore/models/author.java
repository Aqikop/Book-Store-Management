package code.bookstore.models;

abstract class author extends extra{
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

    public void setauthorName(String authorName){
        this.authorName = authorName;
    }

    public String getInfo(){
        return "Author{" +
                "authorId:" + authorId + '\'' +
                ", authorName:" + authorName + 
                "}";
    }
}
