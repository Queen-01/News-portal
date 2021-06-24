package Dao;

import models.DepartNews;
import models.Department;
import models.User;

import java.util.List;

public interface DepartmentDao {
    void updateUser(User user, String name, String role, String position, int departId);

    void add(Department department);

    List<Department>getAllDepart();
    List<User>getDepartUserById();
    List<DepartNews>getDepartNewsById();

    void addDepartment(Department department);
    int findDepartById(int id);
    void updateDepartment(Department department, String name, String description);

    void deleteById(int id);
    void clearAll();
}
