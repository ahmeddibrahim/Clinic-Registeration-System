package Repository;

import Entity.Nurse;
import Entity.Schedule;
import Repository.Config.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NurseDao {

    private Connection connection = DatabaseConnection.getConnection();; // the database connection to be used to excute database commands
    /**
     * Singleton instance
     */
    private static NurseDao instance = null;

    public static NurseDao getInstance() {
        if (instance == null)
            instance = new NurseDao();
        return instance;
    }

    private final String CREATE_NURSE_TABLE = "CREATE TABLE nurses ( " + "id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT, "
            + "firstname VARCHAR(255), " + "lastname VARCHAR(255), " + "doctorId INTEGER, CONSTRAINT FK_NurseDoctor FOREIGN KEY(doctorId) REFERENCES doctors(id))";

    private final String ADD_NURSE = "INSERT INTO nurses ( firstname, lastname, doctorId ) VALUES (?, ?, ?)";
    private final String GET_ALL_NURSES = "SELECT * FROM nurses";
    private final String GET_NURSE_BY_ID = "SELECT * FROM nurses WHERE id = ?";
    private final String GET_ALL_NURSES_FOR_DOCTOR = "SELECT * FROM nurses WHERE doctorId = ?";
    private final String GET_BY_NAME = "SELECT * FROM nurses WHERE firstname LIKE ? OR lastname LIKE ?";
    private final String UPDATE_NURSE = "UPDATE nurses SET firstname = ?, lastname = ?, doctorId = ? "
            + "WHERE id = ?";
    private final String DELETE_BY_ID = "DELETE FROM nurses WHERE id = ?";

    public NurseDao(){}

    public void createNurseTable()throws SQLException{
        Statement statement = connection.createStatement();
        statement.execute(CREATE_NURSE_TABLE);
    }

    public boolean insertNurse(Nurse nurse) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(ADD_NURSE);
        preparedStatement.setString(1,nurse.getFirstname());
        preparedStatement.setString(2,nurse.getLastname());
        preparedStatement.setInt(3,nurse.getDoctorId());

        preparedStatement.executeUpdate();
        DatabaseConnection.closeStatement(preparedStatement);
        return true;
    }

    public Nurse rowMapper(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String firstname = resultSet.getString("firstname");
        String lastname = resultSet.getString("lastname");
        int doctorId = resultSet.getInt("doctorId");
        return new Nurse(id,firstname,lastname,doctorId);
    }

    public Nurse getNurseById(int id) throws SQLException{
        Nurse nurse = null;
        PreparedStatement preparedStatement = connection.prepareStatement(GET_NURSE_BY_ID);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next())
            nurse = rowMapper(resultSet);
        DatabaseConnection.closeStatement(preparedStatement);

        return nurse;
    }

    public List<Nurse> getAllNursesForDoctor(int doctorId) throws SQLException {
        List<Nurse> nurses = new ArrayList<Nurse>();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_NURSES_FOR_DOCTOR);
        preparedStatement.setInt(1,doctorId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Nurse nurse = rowMapper(resultSet);
            nurses.add(nurse);
        }
        DatabaseConnection.closeStatement(preparedStatement);
        DatabaseConnection.closeResultSet(resultSet);
        return nurses;
    }

    public List<Nurse> getAllNurses() throws SQLException {
        List<Nurse> nurses = new ArrayList<Nurse>();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_NURSES);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Nurse nurse = rowMapper(resultSet);
            nurses.add(nurse);
        }
        DatabaseConnection.closeStatement(preparedStatement);
        DatabaseConnection.closeResultSet(resultSet);
        return nurses;
    }

    public List<Nurse> getByName(String name) throws SQLException {
        // search for a match of the given name by the firstname or lastname
        List<Nurse> nurses = new ArrayList<Nurse>();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_NAME);
        preparedStatement.setString(1,name.toLowerCase()+"%");
        preparedStatement.setString(2,name.toLowerCase()+"%");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Nurse nurse = rowMapper(resultSet);
            nurses.add(nurse);
        }
        DatabaseConnection.closeStatement(preparedStatement);
        DatabaseConnection.closeResultSet(resultSet);
        return nurses;
    }

    public boolean updateNurse(Nurse nurse) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_NURSE);
        preparedStatement.setString(1,nurse.getFirstname());
        preparedStatement.setString(2,nurse.getLastname());
        preparedStatement.setInt(3,nurse.getDoctorId());
        preparedStatement.setInt(4,nurse.getNurseId());

        preparedStatement.executeUpdate();
        DatabaseConnection.closeStatement(preparedStatement);
        return true;
    }

    public boolean deleteNurse(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID);
        preparedStatement.setInt(1, id);

        preparedStatement.executeUpdate();
        DatabaseConnection.closeStatement(preparedStatement);

        return true;
    }
}
