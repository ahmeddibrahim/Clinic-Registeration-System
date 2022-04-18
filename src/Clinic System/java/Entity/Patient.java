package Entity;

public class Patient extends AbstractUser{

    private String phoneNumber;
    public Patient(){
        this.userType = UserType.PATIENT;
    }

    public Patient(int id, String username, String password, String firstname, String lastname,String phoneNumber){

        super(id,username,password,firstname,lastname);
        this.phoneNumber = phoneNumber;
    }
    public Patient(String username, String password, String firstname, String lastname,String phoneNumber){

        super(username,password,firstname,lastname);
        this.phoneNumber = phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "Patient{" + "\n id = " + getUserId() + "\n username = " + getUsername() + "\n firstname = "+getFirstname()+
               "\n lastname = "+ getLastname() + "\n phoneNumber='" + phoneNumber +" \n }";
    }
}
