package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
   private UserDao userDaoJDBC = new UserDaoJDBCImpl();

    public void createUsersTable() {
        userDaoJDBC.createUsersTable();

    }

    public void dropUsersTable() {
        userDaoJDBC.dropUsersTable();

    }

    public void saveUser(String name, String lastName, byte age) {
        userDaoJDBC.saveUser(name, lastName, age);


    }

    public void removeUserById(long id) {
        userDaoJDBC.removeUserById(id);

    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try {
            userList = userDaoJDBC.getAllUsers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    public void cleanUsersTable() {
        userDaoJDBC.cleanUsersTable();

    }
}
