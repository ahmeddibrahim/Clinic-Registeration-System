package Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface UserDAO<T>{

    T validateLogin(String username, String password)throws SQLException;
    boolean insertUser(T user) throws SQLException;
    List<T> getAllUsers() throws SQLException;
    T getByUsername(String username) throws SQLException;
    List<T> getByName(String name) throws SQLException;
    boolean updateUser(T user) throws SQLException;
    boolean deleteUser(int id) throws SQLException;

    T rowMapper(ResultSet resultSet) throws SQLException;

}
