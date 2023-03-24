package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    Connection connection = getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = "CREATE TABLE users " +
                "(id BIGINT not NULL AUTO_INCREMENT, " +
                " name VARCHAR(40), " +
                " lastName VARCHAR(40), " +
                " age TINYINT, " +
                " PRIMARY KEY ( id ))";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Database has been created!");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (name, lastName, age) VALUES ('" + name + "','" + lastName + "'," + age + ")";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE from users where id =" + id;
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * from users";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery(sql);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return userList;
    }

    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE users";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
