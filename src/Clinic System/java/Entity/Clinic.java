package Entity;

import javax.print.Doc;
import java.util.ArrayList;

public class Clinic {
     private int clinicId;
     private String name;
     private String address;
     private int doctorId;

    public Clinic(String name, String address, int doctorId){
        this.name = name.toLowerCase();
        this.address = address.toLowerCase();
        this.doctorId = doctorId;
    }

     public Clinic(int clinicId, String name, String address, int doctorId){
         this.clinicId = clinicId;
         this.name = name.toLowerCase();
         this.address = address.toLowerCase();
         this.doctorId = doctorId;
     }

     public void setName(String name) {
        this.name = name.toLowerCase();
    }

    public String getName() {
        return name;
    }

    public void setAddress(String address) {
        this.address = address.toLowerCase();
    }

    public String getAddress() {
        return address.toLowerCase();
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getClinicId() {
        return clinicId;
    }

    @Override
    public String toString() {
        return "Clinic:" +
                " name='" + name +
                "\n address='" + address;
    }
}
