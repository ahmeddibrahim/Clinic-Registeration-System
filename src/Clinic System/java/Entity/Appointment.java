package Entity;

public class Appointment {
    private int appointmentId;
    private String time;
    private String day;
    private int clinicId;
    private int patientId;


    public Appointment(String time, String day, int clinicId, int patientId){
        this.time = time.toLowerCase();
        this.day = day.toLowerCase();
        this.clinicId = clinicId;
        this.patientId = patientId;
    }

    public Appointment(int appointmentId, String time, String day, int clinicId, int patientId){
        this.appointmentId = appointmentId;
        this.time = time.toLowerCase();
        this.day = day.toLowerCase();
        this.clinicId = clinicId;
        this.patientId = patientId;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time.toLowerCase();
    }

    public String getDay() {
        return day.toLowerCase();
    }

    public void setDay(String day) {
        this.day = day.toLowerCase();
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getClinicId() {
        return clinicId;
    }

    public void setClinicId(int clinicId) {
        this.clinicId = clinicId;
    }

    @Override
    public String toString() {
        return "Appointment : " +
                "time='" + time +
                ", day='" + day +
                ", clinicId=" + clinicId;

    }
}
