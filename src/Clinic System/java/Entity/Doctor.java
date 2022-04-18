package Entity;

import java.util.ArrayList;

public class Doctor extends AbstractUser {
    private String speciality;

    public Doctor(){
        userType = UserType.DOCTOR;
    }

    public Doctor(int id,String username, String password, String firstname, String lastname,String speciality){
        super(id,username,password,firstname,lastname);
        this.speciality = speciality.toLowerCase();
    }
    public Doctor(String username, String password, String firstname, String lastname,String speciality){
        super(username,password,firstname,lastname);
        this.speciality = speciality.toLowerCase();
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality.toLowerCase();
    }

}


