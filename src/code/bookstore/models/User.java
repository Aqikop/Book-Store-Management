package code.bookstore.models;

public class User {
    private String fname;
    private String lname;
    private String email;
    private String password;

    public User(String fname, String lname, String email, String password) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.password = password;
    }

    public String getFname() { return fname; }
    public String getLname() { return lname; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
}
