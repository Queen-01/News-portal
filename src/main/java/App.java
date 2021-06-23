import Dao.Sql2oDepartmentDao;
import Dao.Sql2oNewsDao;
import Dao.Sql2oUserDao;
import com.google.gson.Gson;
import models.Department;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import static spark.Spark.*;


public class App {
    public static void main(String[] args) {
        Sql2oDepartmentDao departmentDao;
        Sql2oUserDao userDao;
        Sql2oNewsDao newsDao;
        Connection con;
        Gson gson = new Gson();

        ProcessBuilder process = new ProcessBuilder();

        Integer port = (process.environment().get("PORT") != null) ?
                Integer.parseInt(process.environment().get("PORT")):4567;
        port(port);

        String connectionString = "jdbc:postgresql://localhost:4567/news_portal";
        Sql2o sql2o = new Sql2o(connectionString, "moringa", "access");

        departmentDao = new Sql2oDepartmentDao(sql2o);
        userDao = new Sql2oUserDao(sql2o);
        newsDao = new Sql2oNewsDao(sql2o);
//        con = sql2o.open();

        post("/departments/new", "application/json", ((request, response) -> {
            Department department = gson.fromJson(request.body(), Department.class);
            departmentDao.add(department);
            response.status(201);
            response.type("application/json");
            return gson.toJson(department);
        }));
        get("/departments", "application/json", ((request, response) -> {
            response.type("application/json");
            return gson.toJson(departmentDao.getAllDepart());
        }));
        get("/departments/:id", "application/json", ((request, response) -> {
            response.type("application/json");
            int departId = Integer.parseInt(request.params("id"));
            response.type("application/json");
            return gson.toJson(departmentDao.findDepartById(departId));
        }));
    }
}
