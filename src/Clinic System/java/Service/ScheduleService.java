package Service;

import Entity.Schedule;
import Repository.ScheduleDao;

import java.sql.SQLException;
import java.util.List;

public class ScheduleService {

    ScheduleDao scheduleDao = ScheduleDao.getInstance();
    /**
     * Singleton instance
     */
    private static ScheduleService instance = null;

    public static ScheduleService getInstance() {
        if (instance == null)
            instance = new ScheduleService();
        return instance;
    }
    public ScheduleService(){
        scheduleDao = scheduleDao;
        try {
            scheduleDao.createScheduleTable();
        } catch (SQLException e) {
            if(e.getErrorCode() != 42101 )
                e.printStackTrace();
        }
    }

    public boolean addScheduleToClinic(String days, String startTime, String endTime,int clinicId ){
        try{
            scheduleDao.createSchedule(new Schedule(days,startTime,endTime,clinicId));
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Schedule getScheduleById(int id ){
        Schedule schedule = null;
        try {
            schedule = scheduleDao.getScheduleById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schedule;
    }

    public boolean updateSchedule(Schedule schedule){
        try{
            scheduleDao.updateSchedule(schedule);
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteSchedule(int scheduleId){
        try{
            scheduleDao.deleteSchedule(scheduleId);
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<Schedule> getSchedulesForClinic(int clinicId){
        List<Schedule> schedules = null;
        try {
            schedules = scheduleDao.getScheduleForClinic(clinicId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schedules;
    }

    public List<Schedule> getAllSchedules(){
        List<Schedule> schedules = null;
        try {
            schedules = scheduleDao.getAllSchedules();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schedules;
    }
}
