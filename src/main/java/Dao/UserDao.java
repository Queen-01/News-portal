package Dao;

import models.User;

import java.util.List;

public interface UserDao {
    List<User>getAllUsers();
    void addUser (User user);
    User findUserById(int id);
    void update();

    void update(User user, String name, String role, String position, int departId);

    void deleteById(int id);
    void clearAll();
}
