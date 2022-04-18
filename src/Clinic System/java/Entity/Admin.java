package Entity;

public class Admin extends AbstractUser {

    public Admin(){
        userType = UserType.ADMIN;
    }
    public Admin(int id, String username, String password, String firstname, String lastname){
        super(id,username,password,firstname,lastname);
    }
    public Admin(String username, String password, String firstname, String lastname){
        super(username,password,firstname,lastname);
    }


}
