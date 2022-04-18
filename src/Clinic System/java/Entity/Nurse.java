package Entity;

public class Nurse {
    private int nurseId;
    private String firstname;
    private String lastname;
    private int doctorId; // represents doctor's id for each nurse
    public Nurse(){}

    public Nurse(String firstname, String lastname,int doctorId){
        this.firstname = firstname;
        this.lastname = lastname;
        this.doctorId = doctorId;
    }

    public Nurse(int nurseId, String firstname, String lastname,int doctorId){
       this.nurseId = nurseId;
       this.firstname = firstname;
       this.lastname = lastname;
       this.doctorId = doctorId;
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

    public int getNurseId() {
        return nurseId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    @Override
    public String toString() {
        return "Nurse{" +
                "\n nurseId=" + nurseId +
                ", \n firstname='" + firstname +
                ", \n lastname='" + lastname +
                ", \n doctorId=" + doctorId +
                "\n }";
    }
}

