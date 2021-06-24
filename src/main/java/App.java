import Dao.Sql2oDepartmentDao;
import Dao.Sql2oNewsDao;
import Dao.Sql2oUserDao;
//import Dao.UserDao;
import com.google.gson.Gson;
import models.DepartNews;
import models.Department;
import models.News;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static java.lang.Integer.parseInt;
import static spark.Spark.*;


public class App {
    private static DepartNews news;

    public static void main(String[] args) {
        Sql2oDepartmentDao departmentDao;
        Sql2oUserDao userDao;
        Sql2oNewsDao newsDao;
        Connection con;
        Gson gson = new Gson();

        ProcessBuilder process = new ProcessBuilder();

        Integer port = (process.environment().get("PORT") != null) ?
                parseInt(process.environment().get("PORT")):4567;
        port(port);

        String connectionString = "jdbc:postgresql://localhost:5432/news_portal";
        Sql2o sql2o = new Sql2o(connectionString, "moringa", "access");

        departmentDao = new Sql2oDepartmentDao(sql2o);
        userDao = new Sql2oUserDao(sql2o, departmentDao);
        newsDao = new Sql2oNewsDao(sql2o);
        con = sql2o.open();

        staticFileLocation("/public");

        get("/",(req,res)->{
            res.redirect("index.html"); return null;
        });
        get("/users", (req,res)->{
            return  gson.toJson(userDao.getAllUsers());
        });
        get("/departments", (req,res)->{
            return  gson.toJson(departmentDao.getDepartmentWithUserCount());
        });
        get ("/users/:id", "application/json", ((request, response) -> {
            int userId = Integer.parseInt (request.params ("id"));
            return gson.toJson (userDao.findUserById (userId));
        }));
        get("/departments/:id",(req,res)->{
            int departId = Integer.parseInt(req.params("id"));
            return gson.toJson(departmentDao.findDepartById(departId));
        });
        get("/departments/:id/users",(req,res)->{
            int departId = Integer.parseInt(req.params("id"));
            return gson.toJson(departmentDao.getDepartUserById());
        });
        get("/departments/:id/news",(req,res)->{
            int departId = Integer.parseInt(req.params("id"));
            return gson.toJson(departmentDao.getDepartNewsById());
        });
        get("/news", (req,res)->{
            return  gson.toJson(newsDao.getAllNews());
        });
        get("/news/Main", (req,res)->{
            return  gson.toJson(newsDao.getMainNews());
        });
        get("/news/department", (req,res)->{
            return  gson.toJson(newsDao.getDepartNews());
        });

        post("/departments/new", "application/json", ((request, response) -> {
            Department department = gson.fromJson (request.body (),Department.class);
            departmentDao.add (department);
            response.status (201);
            return gson.toJson (department);
        }));
        post("/users/new", "application/json", ((request, response) -> {
            User user = gson.fromJson (request.body (), User.class);
            userDao.add(user);
            response.status (201);
            return gson.toJson (user);
        }));
        post ("/news/new", "application/json", ((request, response) -> {
            News news = gson.fromJson (request.body (),News.class);
//            newsDao.add(news);
            response.status (201);
            return gson.toJson (news);
        }));
        post("/DepartmentNews/new", "application/json", (req,res)->{
            DepartNews departNews = gson.fromJson(req.body(), DepartNews.class);

            newsDao.addDepartNews(departNews);
            res.status(201);
            res.type("application/json");

            res.redirect("/news/department");
            return gson.toJson (departNews);
        });

        //FILTERS
        after((req, res) ->{
            res.type("application/json");
        });


    }
}
