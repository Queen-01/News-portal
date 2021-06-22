package Dao;

import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import java.util.List;

public class Sql2oUserDao implements UserDao {
    private final Sql2o sql2o;
    private final  Sql2oDepartmentDao departmentDao;

    public Sql2oUserDao(Sql2o sql2o) {
        this.sql2o = sql2o;
        this.departmentDao = new Sql2oDepartmentDao(sql2o) {
            @Override
            public List<User> getDepartUserById() {
                return null;
            }
        };
    }

    //    @Override
    public void add(User user) {
        String sql = "INSERT INTO user (id,name,role,position,departmentId) VALUES (:id, :name, :role, :position, :departmentId);";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(user)
                    .executeUpdate()
                    .getKey();
            user.setId(id);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM user")
                    .executeAndFetch(User.class);
        }
    }

    @Override
    public void addUser(User user) {

    }

    @Override
    public User findUserById(int id) {
        return null;
    }

    @Override
    public void updateUser(User user, String name, String role, String position, int departId) {
        String sql = "UPDATE user SET (name,role,position,departId) = :name, :role, :position, :departId WHERE id = :id;";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("role", role)
                    .addParameter("position", position)
                    .addParameter("departId", departId)
                    .addParameter("id", user.getId())
                    .executeUpdate();
            user.setName(name);
            user.setRole(role);
            user.setPosition(position);
            user.setDepartId(departId);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM user WHERE id = :id";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }catch (Exception e){
            System.out.println(e);
        }

    }

    @Override
    public void clearAll() {
        String sql = "DELETE FROM user";
        try (Connection con = sql2o.open()){
            con.createQuery(sql)
                    .executeUpdate();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
