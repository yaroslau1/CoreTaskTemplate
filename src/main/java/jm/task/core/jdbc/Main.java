package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
//        userService.dropUsersTable();
//        userService.createUsersTable();
        userService.saveUser("Nik", "Mike", (byte) 23);
//        System.out.println(userService.getAllUsers());
    }
}
