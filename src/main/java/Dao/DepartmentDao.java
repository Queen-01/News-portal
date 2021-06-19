package Dao;

import models.DepartNews;
import models.Department;
import models.User;

import java.util.List;

public interface DepartmentDao {
    List<Department>getAllDepart();
    List<User>getDepartUserById();
    List<DepartNews>getDepartNewsById();
}
