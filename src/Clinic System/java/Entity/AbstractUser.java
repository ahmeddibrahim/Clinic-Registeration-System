package Entity;

import java.util.Locale;

public abstract class AbstractUser {
    private int userId;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    protected UserType userType;

    public AbstractUser(){}

    public AbstractUser(int userId, String username, String password, String firstname, String lastname){
        this.userId = userId;
        this.username = username.toLowerCase();
        this.password = password;
        this.firstname = firstname.toLowerCase();
        this.lastname = lastname.toLowerCase();
    }
    public AbstractUser(String username, String password, String firstname, String lastname){
        this.username = username.toLowerCase();
        this.password = password;
        this.firstname = firstname.toLowerCase();
        this.lastname = lastname.toLowerCase();
    }



    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }


    public int getUserId() {
        return userId;
    }

}
