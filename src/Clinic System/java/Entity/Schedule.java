package Entity;

public class Schedule {

    private int scheduleId;
    private String days;
    private String startTime;
    private String endTime;
    private int clinicId;

    public Schedule(){}
    public Schedule(int scheduleId, String days, String startTime, String endTime, int clinicId){
        this.scheduleId = scheduleId;
        this.days = days;
        this.startTime = startTime;
        this.endTime = endTime;
        this.clinicId = clinicId;
    }

    public Schedule(String days, String startTime, String endTime,int clinicId){
        this.days = days.toLowerCase();
        this.startTime = startTime;
        this.endTime = endTime;
        this.clinicId = clinicId;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public int getClinicId() {
        return clinicId;
    }

    public void setClinicId(int clinicId) {
        this.clinicId = clinicId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days.toLowerCase();
    }

    @Override
    public String toString() {
        return "Schedule : " +
                ", \nday ='" + days +
                ", \nstartTime ='" + startTime + '\'' +
                ", \nendTime ='" + endTime ;
    }
}
