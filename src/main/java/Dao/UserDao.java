package Dao;

import models.User;

import java.util.List;

public interface UserDao {
    List<User> getDepartUserById();

    List<User>getAllUsers();
    void addUser (User user);
    int findUserById(int id);

    void updateUser(User user, String name, String role, String position, int departId);

    void deleteById(int id);
    void clearAll();

    List getDepartUserById(int id);
}
