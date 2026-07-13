package ng.ourChemist.data.models;

public class User {
    private String password;
    private String username;
    private String fullName;
    private int id;

    public User(){}

    public User(String username, String password, String fullName){
        this.username = username;
        this.password = password;
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
