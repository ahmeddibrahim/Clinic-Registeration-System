package Repository;

import Entity.Patient;
import Repository.Config.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDao implements UserDAO<Patient> {

    private Connection connection = DatabaseConnection.getConnection();; // the database connection to be used to excute database commands
    /**
     * Singleton instance
     */
    private static PatientDao instance = null;

    public static PatientDao getInstance() {
        if (instance == null)
            instance = new PatientDao();
        return instance;
    }

    private final String CREATE_PATIENT_TABLE = "CREATE TABLE patients ( " + "id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT, "
            + "username VARCHAR(255) UNIQUE, " + "password VARCHAR(255), " + "firstname VARCHAR(255), " + "lastname VARCHAR(255), " + "phoneNumber VARCHAR(255))";

    private final String ADD_PATIENT = "INSERT INTO patients ( username, password, firstname, lastname, phoneNumber ) VALUES (?, ?, ?, ?, ?)";
    private final String GET_ALL_PATIENTS = "SELECT * FROM patients";
    private final String VALIDATE_PATIENT= "SELECT * FROM patients WHERE username = ? AND password = ?";
    private final String GET_BY_USERNAME = "SELECT * FROM patients WHERE username = ?";
    private final String GET_BY_NAME = "SELECT * FROM patients WHERE firstname LIKE ? OR lastname LIKE ?";
    private final String UPDATE_PATIENT = "UPDATE patients SET username = ?, password = ?, firstname = ?, lastname = ?, phoneNumber = ? "
            + "WHERE id = ?";
    private final String DELETE_BY_ID = "DELETE FROM patients WHERE id = ?";

    public PatientDao(){}

    public void createPatientTable() throws SQLException{

        Statement statement = connection.createStatement();
        statement.execute(CREATE_PATIENT_TABLE);
    }

    @Override
    public Patient validateLogin(String username, String password) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(VALIDATE_PATIENT);
        preparedStatement.setString(1,username);
        preparedStatement.setString(2,password);

        ResultSet resultSet = preparedStatement.executeQuery();

        Patient patient = null;
        if(resultSet.next())
            patient = rowMapper(resultSet);

        DatabaseConnection.closeStatement(preparedStatement);
        DatabaseConnection.closeResultSet(resultSet);

        return patient;
    }

    @Override
    public boolean insertUser(Patient patient) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(ADD_PATIENT);
        preparedStatement.setString(1,patient.getUsername());
        preparedStatement.setString(2,patient.getPassword());
        preparedStatement.setString(3,patient.getFirstname());
        preparedStatement.setString(4,patient.getLastname());
        preparedStatement.setString(5,patient.getPhoneNumber());

        preparedStatement.executeUpdate();
        DatabaseConnection.closeStatement(preparedStatement);
       return true;
    }

    @Override
    public Patient rowMapper(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String username = resultSet.getString("username");
        String password = resultSet.getString("password");
        String firstname = resultSet.getString("firstname");
        String lastname = resultSet.getString("lastname");
        String phoneNumber = resultSet.getString("phoneNumber");
        return new Patient(id,username,password,firstname,lastname,phoneNumber);
    }

    @Override
    public List<Patient> getAllUsers() throws SQLException {
        List<Patient> patients = new ArrayList<Patient>();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_PATIENTS);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Patient patient = rowMapper(resultSet);
            patients.add(patient);
        }
        DatabaseConnection.closeStatement(preparedStatement);
        DatabaseConnection.closeResultSet(resultSet);
        return patients;
    }

    @Override
    public Patient getByUsername(String username) throws SQLException {
        // Search by Username and return the patient with such username.
        // If not found, this function returns null.
        // NOTE : usernames are unique!

        PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_USERNAME);
        preparedStatement.setString(1,username.toLowerCase());
        ResultSet resultSet = preparedStatement.executeQuery();

        Patient patient = null;
        if(resultSet.next())
            patient = rowMapper(resultSet);

        DatabaseConnection.closeStatement(preparedStatement);
        DatabaseConnection.closeResultSet(resultSet);

        return patient;

    }

    @Override
    public List<Patient> getByName(String name) throws SQLException {
        // search for a match of the given name by the firstname or lastname
        List<Patient> patients = new ArrayList<Patient>();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_NAME);
        preparedStatement.setString(1,name.toLowerCase()+"%");
        preparedStatement.setString(2,name.toLowerCase()+"%");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Patient patient = rowMapper(resultSet);
            patients.add(patient);
        }
        DatabaseConnection.closeStatement(preparedStatement);
        DatabaseConnection.closeResultSet(resultSet);
        return patients;
    }

    @Override
    public boolean updateUser(Patient patient) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PATIENT);
        preparedStatement.setString(1,patient.getUsername());
        preparedStatement.setString(2,patient.getPassword());
        preparedStatement.setString(3,patient.getFirstname());
        preparedStatement.setString(4,patient.getLastname());
        preparedStatement.setString(5,patient.getPhoneNumber());
        preparedStatement.setInt(6,patient.getUserId());


        preparedStatement.executeUpdate();
        DatabaseConnection.closeStatement(preparedStatement);
        return true;
    }

    @Override
    public boolean deleteUser(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID);
        preparedStatement.setInt(1, id);

        preparedStatement.executeUpdate();
        DatabaseConnection.closeStatement(preparedStatement);

        return true;
    }
}
