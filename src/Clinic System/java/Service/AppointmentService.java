package Service;

import Entity.Appointment;
import Repository.AdminDao;
import Repository.AppointmentDao;

import java.sql.SQLException;
import java.util.List;

public class AppointmentService {

    AppointmentDao appointmentDao = AppointmentDao.getInstance();
    /**
     * Singleton instance
     */
    private static AppointmentService instance = null;

    public static AppointmentService getInstance() {
        if (instance == null)
            instance = new AppointmentService();
        return instance;
    }

    public AppointmentService(){
        try {
            appointmentDao.createAppointmentTable();
        } catch (SQLException e) {
            if(e.getErrorCode() != 42101 )
                e.printStackTrace();
        }
    }
    /**
     * @param time
     * @param day
     * @param clinicId
     * @param patientId
     * @return true if the appointment is registered/created.
     */
    public boolean bookAppointment(String time, String day, int clinicId,int patientId){
        try {
//            if(appointmentDao.getSpecificAppointmentForClinic(time,day,clinicId)==null
//                    && appointmentDao.getSpecificAppointmentForPatient(time,day,patientId)==null )
                appointmentDao.createAppointment(new Appointment(time, day, clinicId, patientId));

        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * @param patientId
     * @param clinicId
     * @implNote gets patient's appointment with a specific clinic
     * @return null if there's no appointment with this clinic
     */
    public Appointment getAppointmentsForPatientWithClinic(int patientId,int clinicId){
        Appointment appointment = null;
        try {
            appointment = appointmentDao.getAppointmentForPatientAndClinic(patientId,clinicId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointment;
    }

    /**
     * @param patientId
     * @implNote gets all patient's booked appointments
     * @return List of Appointment or null if nothing was found
     */
    public List<Appointment> getAllAppointmentsForPatient(int patientId){
        List<Appointment> appointments = null;
        try {
            appointments = appointmentDao.getAllAppointmentsForPatient(patientId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointments;
    }

    /**
     * @param clinicId
     * @implNote gets all clinic's booked appointments
     * @return ArrayList of the Appointments or null if nothing was found
     */
    public List<Appointment> getAllAppointmentsForClinic(int clinicId){
        List<Appointment> appointments = null;
        try {
            appointments = appointmentDao.getAllAppointmentsForClinic(clinicId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointments;
    }

    /**
     *
     * @implNote gets all clinic's booked appointments
     * @return ArrayList of  Appointments or null if nothing was found
     */
    public List<Appointment> getAllAppointments(){
        List<Appointment> appointments = null;
        try {
            appointments = appointmentDao.getAllAppointments();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointments;
    }

    public boolean updateAppointment(Appointment appointment){
        try {
            appointmentDao.updateAppointment(appointment);
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteAppointment(int appointmentId){
        try {
            appointmentDao.deleteAppointment(appointmentId);
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
