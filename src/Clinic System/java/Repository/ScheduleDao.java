package Repository;

import Entity.Appointment;
import Entity.Schedule;
import Repository.Config.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ScheduleDao {
    private Connection connection = DatabaseConnection.getConnection();; // the database connection to be used to excute database commands
    /**
     * Singleton instance
     */
    private static ScheduleDao instance = null;

    public static ScheduleDao getInstance() {
        if (instance == null)
            instance = new ScheduleDao();
        return instance;
    }

    private final String CREATE_SCHEDULE_TABLE = "CREATE TABLE schedules ( " + "id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT, "
            + "startTime VARCHAR(255), " + "endTime VARCHAR(255), " + "days VARCHAR(255), "
            + "clinicId INTEGER, CONSTRAINT FK_ClinicSchedule FOREIGN KEY(clinicId) REFERENCES clinics(id))";
    private final String CREATE_SCHEDULE = "INSERT INTO schedules ( startTime, endTime, days, clinicId ) VALUES (?, ?, ?, ?)";
    private final String GET_ALL_SCHEDULES = "SELECT * FROM schedules";
    private final String GET_SCHEDULE_BY_ID = "SELECT * FROM schedules WHERE id = ?";
    private final String GET_CLINIC_SCHEDULE = "SELECT * FROM schedules WHERE clinicId = ?";
    private final String UPDATE_SCHEDULE = "UPDATE schedules SET startTime = ?, endTime = ?, days = ?, clinicId = ? "
            + "WHERE id = ?";
    private  final String DELETE_BY_ID = "DELETE schedules WHERE id = ?";

    public ScheduleDao(){}


    public void createScheduleTable() throws SQLException{
        Statement statement = connection.createStatement();
        statement.execute(CREATE_SCHEDULE_TABLE);
    }
    public boolean createSchedule(Schedule schedule) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(CREATE_SCHEDULE);
        preparedStatement.setString(1,schedule.getStartTime());
        preparedStatement.setString(2,schedule.getEndTime());
        preparedStatement.setString(3,schedule.getDays());
        preparedStatement.setInt(4,schedule.getClinicId());

        preparedStatement.executeUpdate();
        DatabaseConnection.closeStatement(preparedStatement);
        return true;
    }

    public Schedule rowMapper(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String startTime = resultSet.getString("startTime");
        String endTime = resultSet.getString("endTime");
        String days = resultSet.getString("days");
        int clinicId = resultSet.getInt("clinicId");
        return new Schedule(id,startTime,endTime,days,clinicId);
    }

    public Schedule getScheduleById(int id )throws SQLException{
        Schedule schedule = null;
        PreparedStatement preparedStatement = connection.prepareStatement(GET_SCHEDULE_BY_ID);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next())
             schedule = rowMapper(resultSet);
        DatabaseConnection.closeStatement(preparedStatement);

        return schedule;
    }

    public List<Schedule> getScheduleForClinic(int id )throws SQLException{
        List<Schedule> schedules = new ArrayList<Schedule>();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_CLINIC_SCHEDULE);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Schedule schedule = rowMapper(resultSet);
            schedules.add(schedule);
        }
        DatabaseConnection.closeStatement(preparedStatement);

        return schedules;
    }
    public List<Schedule> getAllSchedules() throws SQLException {
        List<Schedule> schedules = new ArrayList<Schedule>();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_SCHEDULES);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Schedule schedule = rowMapper(resultSet);
            schedules.add(schedule);
        }
        DatabaseConnection.closeStatement(preparedStatement);
        DatabaseConnection.closeResultSet(resultSet);

        return schedules;
    }

    public boolean updateSchedule(Schedule schedule) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SCHEDULE);
        preparedStatement.setString(1,schedule.getStartTime());
        preparedStatement.setString(2,schedule.getEndTime());
        preparedStatement.setString(3,schedule.getDays());
        preparedStatement.setInt(4,schedule.getClinicId());

        preparedStatement.executeUpdate();
        DatabaseConnection.closeStatement(preparedStatement);
        return true;
    }

    public boolean deleteSchedule(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID);
        preparedStatement.setInt(1, id);

        preparedStatement.executeUpdate();
        DatabaseConnection.closeStatement(preparedStatement);

        return true;
    }
}
