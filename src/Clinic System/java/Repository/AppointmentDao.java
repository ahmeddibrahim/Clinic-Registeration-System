package Repository;

import Entity.Appointment;
import Repository.Config.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDao {
    private Connection connection = DatabaseConnection.getConnection();; // the database connection to be used to excute database commands
    /**
     * Singleton instance
     */
    private static AppointmentDao instance = null;

    public static AppointmentDao getInstance() {
        if (instance == null)
            instance = new AppointmentDao();
        return instance;
    }

    private final String CREATE_APPOINTMENT_TABLE = "CREATE TABLE appointments ( " + "id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT, "
            + "time VARCHAR(255), " + "days VARCHAR(255), "
            + "clinicId INTEGER, CONSTRAINT FK_AppointmentClinic FOREIGN KEY(clinicId) REFERENCES clinics(id), "
            +"patientId INTEGER, CONSTRAINT FK_AppointmentPatient FOREIGN KEY(patientId) REFERENCES patients(id))";

    private final String CREATE_APPOINTMENT = "INSERT INTO appointments ( time, day, clinicId, patientId ) VALUES (?, ?, ?, ?)";
    private final String GET_ALL_APPOINTMENTS = "SELECT * FROM appointments";
    private final String CHECK_SPECIFIC_APPOINTMENT_FOR_PATIENT = "SELECT * FROM appointments WHERE patientId = ? AND time = ? AND day = ?";
    private final String CHECK_SPECIFIC_APPOINTMENT_FOR_CLINIC = "SELECT * FROM appointments clinicId = ? AND time = ? AND day = ?";
    private final String GET_ALL_APPOINTMENTS_FOR_PATIENT = "SELECT * FROM appointments WHERE patientId = ?";
    private final String GET_ALL_APPOINTMENTS_FOR_CLINIC = "SELECT * FROM appointments WHERE clinicId = ?";
    private final String GET_APPOINTMENT_FOR_PATIENT_AND_CLINIC = "SELECT * FROM appointments WHERE patientId = ? AND clinicId = ?";
    private final String UPDATE_APPOINTMENT = "UPDATE appointments SET time = ?, day = ?, clinicId = ?, patientId = ? "
            + "WHERE id = ?";
    private  final String DELETE_BY_ID = "DELETE appointments WHERE id = ?";

    public AppointmentDao(){}

    public void createAppointmentTable() throws SQLException{
        Statement statement = connection.createStatement();
        statement.execute(CREATE_APPOINTMENT_TABLE);
    }
    public boolean createAppointment(Appointment appointment) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(CREATE_APPOINTMENT);
        preparedStatement.setString(1,appointment.getTime());
        preparedStatement.setString(2,appointment.getDay().toLowerCase());
        preparedStatement.setInt(3,appointment.getClinicId());
        preparedStatement.setInt(4,appointment.getPatientId());

        preparedStatement.executeUpdate();
        DatabaseConnection.closeStatement(preparedStatement);
        return true;
    }

    public Appointment rowMapper(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String time = resultSet.getString("time");
        String date = resultSet.getString("day");
        int clinicId = resultSet.getInt("clinicId");
        int patientId = resultSet.getInt("patientId");

        return new Appointment(id,time,date,clinicId,patientId);
    }

    public Appointment getSpecificAppointmentForPatient(String time, String day, int patientId ) throws SQLException{
        Appointment appointment = null;
        PreparedStatement preparedStatement = connection.prepareStatement(CHECK_SPECIFIC_APPOINTMENT_FOR_PATIENT);
        preparedStatement.setString(1,time);
        preparedStatement.setString(2,day.toLowerCase());
        preparedStatement.setInt(3,patientId);

        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next())
            appointment = rowMapper(resultSet);

        return appointment;
    }
    public Appointment getSpecificAppointmentForClinic(String time, String day, int clinicId ) throws SQLException{
        Appointment appointment = null;
        PreparedStatement preparedStatement = connection.prepareStatement(CHECK_SPECIFIC_APPOINTMENT_FOR_CLINIC);
        preparedStatement.setString(1,time);
        preparedStatement.setString(2,day.toLowerCase());
        preparedStatement.setInt(3,clinicId);

        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next())
            appointment = rowMapper(resultSet);

        return appointment;
    }
    public List<Appointment> getAllAppointments() throws SQLException {
        List<Appointment> appointments = new ArrayList<Appointment>();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_APPOINTMENTS);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Appointment appointment = rowMapper(resultSet);
            appointments.add(appointment);
        }
        DatabaseConnection.closeStatement(preparedStatement);
        DatabaseConnection.closeResultSet(resultSet);

        return appointments;
    }

    public List<Appointment> getAllAppointmentsForPatient(int patientId) throws SQLException {

        List<Appointment> appointments = new ArrayList<Appointment>();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_APPOINTMENTS_FOR_PATIENT);
        preparedStatement.setInt(1,patientId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Appointment appointment = rowMapper(resultSet);
            appointments.add(appointment);
        }
        DatabaseConnection.closeStatement(preparedStatement);
        DatabaseConnection.closeResultSet(resultSet);

        return appointments;
    }

    public List<Appointment> getAllAppointmentsForClinic(int clinicId) throws SQLException {

        List<Appointment> appointments = new ArrayList<Appointment>();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_APPOINTMENTS_FOR_CLINIC);
        preparedStatement.setInt(1,clinicId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Appointment appointment = rowMapper(resultSet);
            appointments.add(appointment);
        }
        DatabaseConnection.closeStatement(preparedStatement);
        DatabaseConnection.closeResultSet(resultSet);

        return appointments;
    }

    public Appointment getAppointmentForPatientAndClinic(int patientId,int clinicId) throws SQLException{
        Appointment appointment = null;
        PreparedStatement preparedStatement = connection.prepareStatement(GET_APPOINTMENT_FOR_PATIENT_AND_CLINIC);
        preparedStatement.setInt(1,patientId);
        preparedStatement.setInt(2,clinicId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next())
            appointment = rowMapper(resultSet);

        return appointment;
    }

    public boolean updateAppointment(Appointment appointment) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_APPOINTMENT);
        preparedStatement.setString(1,appointment.getTime());
        preparedStatement.setString(2,appointment.getDay());
        preparedStatement.setInt(3,appointment.getClinicId());
        preparedStatement.setInt(4,appointment.getPatientId());

        preparedStatement.executeUpdate();
        DatabaseConnection.closeStatement(preparedStatement);
        return true;
    }

    public boolean deleteAppointment(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID);
        preparedStatement.setInt(1, id);

        preparedStatement.executeUpdate();
        DatabaseConnection.closeStatement(preparedStatement);

        return true;
    }
}
