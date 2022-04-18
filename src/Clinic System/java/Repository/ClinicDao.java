package Repository;

import Entity.Clinic;
import Repository.Config.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClinicDao {

    private Connection connection = DatabaseConnection.getConnection();; // the database connection to be used to excute database commands
    /**
     * Singleton instance
     */
    private static ClinicDao instance = null;

    public static ClinicDao getInstance() {
        if (instance == null)
            instance = new ClinicDao();
        return instance;
    }


    private final String CREATE_CLINIC_TABLE = "CREATE TABLE clinics ( " + "id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT, "
            + "name VARCHAR(255), " + "address VARCHAR(255), "
            + "doctorId INTEGER, CONSTRAINT FK_ClinicDoctor FOREIGN KEY(doctorId) REFERENCES doctors(id))";
    private final String CREATE_CLINIC = "INSERT INTO clinics ( name, address, doctorId ) VALUES (?, ?, ?)";
    private final String GET_ALL_CLINICS = "SELECT * FROM clinics";
    private final String GET_CLINIC_BY_DOCTOR_ID = "SELECT * FROM clinics WHERE doctorId = ?";
    private final String GET_CLINIC_BY_ID = "SELECT * FROM CLINICS WHERE id = ?";
    private final String GET_BY_NAME = "SELECT * FROM clinics WHERE name LIKE ?";
    private final String UPDATE_CLINIC = "UPDATE clinics SET name = ?, address = ?, doctorId = ? "
            + "WHERE id = ?";
    private  final String DELETE_BY_ID = "DELETE clinics WHERE id = ?";

    public ClinicDao(){}

    public void createClinicTable() throws SQLException{
        Statement statement = connection.createStatement();
        statement.execute(CREATE_CLINIC_TABLE);
    }

    public boolean createClinic(Clinic clinic) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(CREATE_CLINIC);
        preparedStatement.setString(1,clinic.getName());
        preparedStatement.setString(2,clinic.getAddress());
        preparedStatement.setInt(3,clinic.getDoctorId());

        preparedStatement.executeUpdate();
        DatabaseConnection.closeStatement(preparedStatement);
        return true;
    }

    public Clinic rowMapper(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String address = resultSet.getString("address");
        int doctorId = resultSet.getInt("doctorId");

        return new Clinic(id,name,address,doctorId);
    }

    public Clinic getClinicById(int clinicId)throws SQLException{
        Clinic clinic = null;
        PreparedStatement preparedStatement = connection.prepareStatement(GET_CLINIC_BY_ID);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next())
            clinic = rowMapper(resultSet);

        DatabaseConnection.closeStatement(preparedStatement);
        DatabaseConnection.closeResultSet(resultSet);
        return clinic;
    }
    public Clinic getClinicByDoctorId(int doctorId)throws SQLException{
        Clinic clinic = null;
        PreparedStatement preparedStatement = connection.prepareStatement(GET_CLINIC_BY_DOCTOR_ID);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next())
            clinic = rowMapper(resultSet);

        DatabaseConnection.closeStatement(preparedStatement);
        DatabaseConnection.closeResultSet(resultSet);
        return clinic;
    }

    public List<Clinic> getAllClinics() throws SQLException {
        List<Clinic> clinics = new ArrayList<Clinic>();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_CLINICS);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Clinic clinic = rowMapper(resultSet);
            clinics.add(clinic);
        }
        DatabaseConnection.closeStatement(preparedStatement);
        DatabaseConnection.closeResultSet(resultSet);
        return clinics;
    }

    public List<Clinic> getByName(String name) throws SQLException {
        // search for a match of the given name by the firstname or lastname
        List<Clinic> clinics = new ArrayList<Clinic>();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_NAME);
        preparedStatement.setString(1,name.toLowerCase()+"%");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Clinic clinic = rowMapper(resultSet);
            clinics.add(clinic);
        }
        DatabaseConnection.closeStatement(preparedStatement);
        DatabaseConnection.closeResultSet(resultSet);
        return clinics;
    }

    public boolean updateClinic(Clinic clinic) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CLINIC);
        preparedStatement.setString(1,clinic.getName());
        preparedStatement.setString(2,clinic.getAddress());
        preparedStatement.setInt(3,clinic.getDoctorId());

        preparedStatement.executeUpdate();
        DatabaseConnection.closeStatement(preparedStatement);
        return true;
    }

    public boolean deleteClinic(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID);
        preparedStatement.setInt(1, id);

        preparedStatement.executeUpdate();
        DatabaseConnection.closeStatement(preparedStatement);

        return true;
    }
}
