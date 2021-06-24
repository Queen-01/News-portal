package Dao;

import models.DepartNews;
import models.Department;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;
import java.util.stream.Collectors;
//import interface DepartmentDao;

public class Sql2oDepartmentDao implements DepartmentDao {
    private final Sql2o sql2o;
    private final Sql2oUserDao userDao;
    private NewsDao newsDao;
//    private final Sql2oUserDao userDao;

    public Sql2oDepartmentDao(Sql2o sql2o) {
        this.sql2o = sql2o;
        Sql2oDepartmentDao departmentDao = null;
        this.userDao = new Sql2oUserDao(sql2o, departmentDao);
    }

    @Override
    public void updateUser(User user, String name, String role, String position, int departId) {

    }
    @Override
    public void add(Department department){
        String sql = "INSERT INTO department (name,description) VALUES ( :name, :description);";
        try (Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql, true)
                    .bind(department)
                    .executeUpdate()
                    .getKey();
            department.setId(id);
        }catch (Exception e){
            System.out.println(e);
        }
    }
    @Override
    public List<Department> getAllDepart() {
       try (Connection con = sql2o.open()){
           return  con.createQuery("SELECT * FROM department")
                   .executeAndFetch(Department.class);
       }
    }

//    @Override
    public List<User> getDepartUserById() {
        int id = 0;
        return userDao.getAllUsers().stream()
                .filter(user -> user.getDepartId() == id)
                .collect(Collectors.toList());
    }


    @Override
    public List<DepartNews> getDepartNewsById() {
        int id = 0;
        return newsDao.getDepartNews().stream()
                .filter(department->department.getDepartId()==id)
                .collect(Collectors.toList());
    }

    @Override
    public void addDepartment(Department department) {

    }

    @Override
    public int findDepartById(int id) {
        String sql = "SELECT * FROM department WHERE id = :id";
        try (Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Department.class);

        }
        return id;
    }

    @Override
    public void updateDepartment(Department department, String name, String description) {
        String sql = "UPDATE user SET (name,description) = :name, :description WHERE id = :id;";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("description",description)
                    .executeUpdate();
            department.setName(name);
            department.setDescription(description);
        }

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void clearAll() {

    }

    public List<Department> getDepartmentWithUserCount(){
        return getAllDepart().stream()
                .map(dpt->
                        new Department.DepartmentWithUserCount(
                                dpt.getId(),
                                dpt.getName(),
                                dpt.getDescription(),
                                getDepartUserById().size()
                        )).collect(Collectors.toList());
    }

//    public void setDepartmentWithUserCount(void departmentWithUserCount) {
//        this.departmentWithUserCount = departmentWithUserCount;
//    }
}
