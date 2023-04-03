package jm.task.core.jdbc;

import com.mysql.fabric.jdbc.FabricMySQLDriver;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main {

    private static final String URL = "jdbc:mysql://localhost:3306/new_schema";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";


    public static void main(String[] args) throws SQLException {

        UserService userService = new UserServiceImpl();

        userService.dropUsersTable();
        userService.createUsersTable();
        userService.saveUser("Nik", "Park", (byte) 27);
        System.out.printf("User с именем – Nik добавлен в базу данных ");
        userService.saveUser("Pic", "Park1", (byte) 28);
        System.out.printf("User с именем – Pic добавлен в базу данных ");
        userService.saveUser("Bob", "Park2", (byte) 29);
        System.out.printf("User с именем – Bob добавлен в базу данных ");
        userService.saveUser("Tom", "Park3", (byte) 30);
        System.out.printf("User с именем – Tom добавлен в базу данных");

        System.out.println(userService.getAllUsers());

        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
