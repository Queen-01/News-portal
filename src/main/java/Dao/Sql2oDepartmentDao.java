package Dao;

import models.DepartNews;
import models.Department;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import java.util.List;

public class Sql2oDepartmentDao implements DepartmentDao{
    private final Sql2o sql2o;

    public Sql2oDepartmentDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Department department){
        String sql = "INSERT INTO department (id,name,description) VALUES (:id, :name, :description);";
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

    @Override
    public List<User> getDepartUserById() {
        return null;
    }

    @Override
    public List<DepartNews> getDepartNewsById() {
        return null;
    }

    @Override
    public void addDepartment(Department department) {

    }

    @Override
    public Department findDepartById(int id) {
        return null;
    }

    @Override
    public void updateDepartment(Department department, String name, String description) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void clearAll() {

    }
}
