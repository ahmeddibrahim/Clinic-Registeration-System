package Repository;

import Entity.Admin;
import Repository.Config.DatabaseConnection;
import Service.AdminService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDao implements UserDAO<Admin> {
    private final String CREATE_ADMIN_TABLE = "CREATE TABLE admins ( " + "id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT, "
            + "username VARCHAR(255) UNIQUE, " + "password VARCHAR(255), " + "firstname VARCHAR(255), " + "lastname VARCHAR(255))";

    private final String ADD_ADMIN = "INSERT INTO admins ( username, password, firstname, lastname ) VALUES (?, ?, ?, ?)";
    private final String GET_ALL_ADMINS = "SELECT * FROM admins";
    private final String VALIDATE_ADMIN= "SELECT * FROM admins WHERE username = ? AND password = ?";
    private final String GET_BY_USERNAME = "SELECT * FROM admins WHERE username = ?";
    private final String GET_BY_NAME = "SELECT * FROM admins WHERE firstname LIKE ? OR lastname LIKE ?";
    private final String UPDATE_ADMIN = "UPDATE admins SET username = ?, password = ?, firstname = ?, lastname = ? "
            + "WHERE id = ?";
    private final String DELETE_BY_ID = "DELETE FROM admins WHERE id = ?";

    private Connection connection = DatabaseConnection.getConnection();; // the database connection to be used to excute database commands
    /**
     * Singleton instance
     */
    private static AdminDao instance = null;

    public static AdminDao getInstance() {
        if (instance == null)
            instance = new AdminDao();
        return instance;
    }
    public AdminDao(){};

    public void createAdminTable() throws SQLException{
        Statement statement = connection.createStatement();
        statement.execute(CREATE_ADMIN_TABLE);
    }

    @Override
    public Admin validateLogin(String username, String password)throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(VALIDATE_ADMIN);
        preparedStatement.setString(1,username.toLowerCase());
        preparedStatement.setString(2,password);

        ResultSet resultSet = preparedStatement.executeQuery();

        Admin admin = null;
        if(resultSet.next())
            admin = rowMapper(resultSet);

        DatabaseConnection.closeStatement(preparedStatement);
        DatabaseConnection.closeResultSet(resultSet);

        return admin;
    }

    @Override
    public boolean insertUser(Admin admin) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(ADD_ADMIN);
        preparedStatement.setString(1,admin.getUsername());
        preparedStatement.setString(2,admin.getPassword());
        preparedStatement.setString(3,admin.getFirstname());
        preparedStatement.setString(4,admin.getLastname());

        preparedStatement.executeUpdate();
        DatabaseConnection.closeStatement(preparedStatement);
        return true;
    }

    @Override
    public Admin rowMapper(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String username = resultSet.getString("username");
        String password = resultSet.getString("password");
        String firstname = resultSet.getString("firstname");
        String lastname = resultSet.getString("lastname");
        return new Admin(id,username,password,firstname,lastname);
    }

    @Override
    public List<Admin> getAllUsers() throws SQLException {
        List<Admin> admins = new ArrayList<Admin>();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_ADMINS);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Admin admin = rowMapper(resultSet);
            admins.add(admin);
        }
        DatabaseConnection.closeStatement(preparedStatement);
        DatabaseConnection.closeResultSet(resultSet);
        return admins;
    }

    @Override
    public Admin getByUsername(String username) throws SQLException {
        // Search by Username and return the admin with such username.
        // If not found, this function returns null.
        // NOTE : usernames are unique!

        PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_USERNAME);
        preparedStatement.setString(1,username.toLowerCase());
        ResultSet resultSet = preparedStatement.executeQuery();

        Admin admin = null;
        if(resultSet.next())
            admin = rowMapper(resultSet);

        DatabaseConnection.closeStatement(preparedStatement);
        DatabaseConnection.closeResultSet(resultSet);

        return admin;

    }

    @Override
    public List<Admin> getByName(String name) throws SQLException {
        // search for a match of the given name by the firstname or lastname
        List<Admin> admins = new ArrayList<Admin>();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_NAME);
        preparedStatement.setString(1,name.toLowerCase()+"%");
        preparedStatement.setString(2,name.toLowerCase()+"%");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Admin admin = rowMapper(resultSet);
            admins.add(admin);
        }
        DatabaseConnection.closeStatement(preparedStatement);
        DatabaseConnection.closeResultSet(resultSet);
        return admins;
    }

    @Override
    public boolean updateUser(Admin admin) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ADMIN);
        preparedStatement.setString(1,admin.getUsername());
        preparedStatement.setString(2,admin.getPassword());
        preparedStatement.setString(3,admin.getFirstname());
        preparedStatement.setString(4,admin.getLastname());
        preparedStatement.setInt(5,admin.getUserId());


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
