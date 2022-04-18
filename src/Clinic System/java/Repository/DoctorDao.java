package Repository;

import Entity.Doctor;
import Repository.Config.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDao implements UserDAO<Doctor> {
    private Connection connection = DatabaseConnection.getConnection();; // the database connection to be used to excute database commands
    /**
     * Singleton instance
     */
    private static DoctorDao instance = null;

    public static DoctorDao getInstance() {
        if (instance == null)
            instance = new DoctorDao();
        return instance;
    }

    private final String CREATE_DOCTOR_TABLE = "CREATE TABLE doctors ( " + "id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT, "
            + "username VARCHAR(255) UNIQUE, " + "password VARCHAR(255), " + "firstname VARCHAR(255), " + "lastname VARCHAR(255), " + "speciality VARCHAR(255))";

    private final String ADD_DOCTOR = "INSERT INTO doctors ( username, password, firstname, lastname, speciality ) VALUES (?, ?, ?, ?, ?)";
    private final String GET_ALL_DOCTORS = "SELECT * FROM doctors";
    private final String VALIDATE_DOCTOR= "SELECT * FROM doctors WHERE username = ? AND password = ?";
    private final String GET_DOCTOR_BY_ID = "SELECT * FROM doctors Where id = ?";
    private final String GET_BY_USERNAME = "SELECT * FROM doctors WHERE username = ?";
    private final String GET_BY_NAME = "SELECT * FROM doctors WHERE firstname LIKE ? OR lastname LIKE ?";
    private final String GET_BY_SPECIALITY = "SELECT * FROM doctors WHERE speciality = ?";
    private final String GET_BY_ADDRESS = "SELECT * FROM doctors WHERE address = ?";
    private final String UPDATE_DOCTOR = "UPDATE doctors SET username = ?, password = ?, firstname = ?, lastname = ?, speciality = ? "
            + "WHERE id = ?";
    private final String DELETE_BY_ID = "DELETE FROM doctors WHERE id = ?";

    public DoctorDao(){}


    public void createDoctorTable()throws SQLException{
        Statement statement = connection.createStatement();
        statement.execute(CREATE_DOCTOR_TABLE);
    }
    @Override
    public Doctor validateLogin(String username, String password) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(VALIDATE_DOCTOR);
        preparedStatement.setString(1,username.toLowerCase());
        preparedStatement.setString(2,password);

        ResultSet resultSet = preparedStatement.executeQuery();

        Doctor doctor = null;
        if(resultSet.next())
            doctor = rowMapper(resultSet);

        DatabaseConnection.closeStatement(preparedStatement);
        DatabaseConnection.closeResultSet(resultSet);

        return doctor;
    }

    @Override
    public boolean insertUser(Doctor doctor) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(ADD_DOCTOR);
        preparedStatement.setString(1,doctor.getUsername());
        preparedStatement.setString(2,doctor.getPassword());
        preparedStatement.setString(3,doctor.getFirstname());
        preparedStatement.setString(4,doctor.getLastname());
        preparedStatement.setString(5,doctor.getSpeciality());

        preparedStatement.executeUpdate();
        DatabaseConnection.closeStatement(preparedStatement);
        return true;
    }

    @Override
    public Doctor rowMapper(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String username = resultSet.getString("username");
        String password = resultSet.getString("password");
        String firstname = resultSet.getString("firstname");
        String lastname = resultSet.getString("lastname");
        String speciality = resultSet.getString("speciality");
        return new Doctor(id,username,password,firstname,lastname,speciality);
    }

    @Override
    public List<Doctor> getAllUsers() throws SQLException {
        List<Doctor> doctors = new ArrayList<Doctor>();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_DOCTORS);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Doctor doctor = rowMapper(resultSet);
            doctors.add(doctor);
        }
        DatabaseConnection.closeStatement(preparedStatement);
        DatabaseConnection.closeResultSet(resultSet);
        return doctors;
    }

    public Doctor getDoctorById(int doctorId) throws SQLException {
        // Search by id
        // If not found,returns null.

        PreparedStatement preparedStatement = connection.prepareStatement(GET_DOCTOR_BY_ID);
        preparedStatement.setInt(1,doctorId);
        ResultSet resultSet = preparedStatement.executeQuery();

        Doctor doctor = null;
        if(resultSet.next())
            doctor = rowMapper(resultSet);

        DatabaseConnection.closeStatement(preparedStatement);
        DatabaseConnection.closeResultSet(resultSet);

        return doctor;

    }
    @Override
    public Doctor getByUsername(String username) throws SQLException {
        // Search by Username and return the doctor with such username.
        // If not found, returns null.
        // NOTE : usernames are unique!

        PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_USERNAME);
        preparedStatement.setString(1,username.toLowerCase());
        ResultSet resultSet = preparedStatement.executeQuery();

        Doctor doctor = null;
        if(resultSet.next())
            doctor = rowMapper(resultSet);

        DatabaseConnection.closeStatement(preparedStatement);
        DatabaseConnection.closeResultSet(resultSet);

        return doctor;

    }

    @Override
    public List<Doctor> getByName(String name) throws SQLException {
        // search for a match of the given name by the firstname or lastname
        List<Doctor> doctors = new ArrayList<Doctor>();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_NAME);
        preparedStatement.setString(1,name.toLowerCase()+"%");
        preparedStatement.setString(2,name.toLowerCase()+"%");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Doctor doctor = rowMapper(resultSet);
            doctors.add(doctor);
        }
        DatabaseConnection.closeStatement(preparedStatement);
        DatabaseConnection.closeResultSet(resultSet);
        return doctors;
    }

    public List<Doctor> searchBySpeciality(String speciality) throws SQLException {
        // search for a match of the given name by the firstname or lastname
        List<Doctor> doctors = new ArrayList<Doctor>();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_SPECIALITY);
        preparedStatement.setString(1,speciality.toLowerCase()+"%");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Doctor doctor = rowMapper(resultSet);
            doctors.add(doctor);
        }
        DatabaseConnection.closeStatement(preparedStatement);
        DatabaseConnection.closeResultSet(resultSet);
        return doctors;
    }

    public List<Doctor> searchByAddress(String address) throws SQLException {
        // search for a match of the given name by the firstname or lastname
        List<Doctor> doctors = new ArrayList<Doctor>();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ADDRESS);
        preparedStatement.setString(1,address.toLowerCase()+"%");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Doctor doctor = rowMapper(resultSet);
            doctors.add(doctor);
        }
        DatabaseConnection.closeStatement(preparedStatement);
        DatabaseConnection.closeResultSet(resultSet);
        return doctors;
    }

    @Override
    public boolean updateUser(Doctor doctor) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DOCTOR);
        preparedStatement.setString(1,doctor.getUsername());
        preparedStatement.setString(2,doctor.getPassword());
        preparedStatement.setString(3,doctor.getFirstname());
        preparedStatement.setString(4,doctor.getLastname());
        preparedStatement.setString(5,doctor.getSpeciality());
        preparedStatement.setInt(6,doctor.getUserId());

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
