package Repository.Config;

import java.sql.*;


public class DatabaseConnection {
    public static Connection getConnection() {
        String jdbcURL = "jdbc:h2:~/test";
        String username = "sa";
        String password = "";

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcURL, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection Failed!");
        }
        return connection;
    }

    public static void closeConnection(Connection connection){
        try{
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Closing connection failed");
        }
    }
    public static void closeStatement(Statement statement){
        try{
            statement.close();
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Close statement failed");
        }
    }

    public static void closeResultSet(ResultSet resultSet){
        try{
            resultSet.close();
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Close resultSet failed");
        }
    }

}
