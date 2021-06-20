package Dao;

import models.User;

import java.util.List;

public interface UserDao {
    List<User>getAllUsers();
    void addUser (User user);
    User findUserById(int id);
    Void update();
    void deleteById(int id);
    void clearAll();
}
