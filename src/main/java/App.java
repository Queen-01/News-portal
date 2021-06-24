import Dao.Sql2oDepartmentDao;
import Dao.Sql2oNewsDao;
import Dao.Sql2oUserDao;
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
//        con = sql2o.open();

        staticFileLocation("/public");

        get("/",(req,res)->{
            res.redirect("index.html"); return null;
        });

        get("/users", (req,res)->{
            return  gson.toJson(userDao.getAllUsers());
        });
        get("/users/:id",(req,res)->{
            int user_id = Integer.parseInt(req.params("id"));
            return gson.toJson(userDao.findUserById(user_id));
        });
        get("/departments/:id",(req,res)->{
            int dpt_id = Integer.parseInt(req.params("id"));
            return gson.toJson(departmentDao.findDepartById(0));
        });
        get("/departments/:id/users",(req,res)->{
            int dpt_id = Integer.parseInt(req.params("id"));
            return gson.toJson(departmentDao.getDepartUserById());
        });
        get("/news", (req,res)->{
            return  gson.toJson(newsDao.getAllNews());
        });
        get("/news/general", (req,res)->{
            return  gson.toJson(newsDao.getMainNews());
        });
        get("/news/department", (req,res)->{
            return  gson.toJson(newsDao.getDepartNews());
        });

        post("/Departments/new", "application/json", (req,res)->{
            Department dpt = gson.fromJson(req.body(),Department.class);

            departmentDao.addDepartment(dpt);
            res.status(201);
            res.type("application/json");
            res.redirect("/departments");
            return null;//gson.toJson(dpt);
        });
        post("/Users/new", "application/json", (req,res)->{
            User user = gson.fromJson(req.body(), User.class);

            userDao.addUser(user);
            res.status(201);
            res.type("application/json");

            res.redirect("/users");
            return null; //gson.toJson(user);
        });
        post("/News/new", "application/json", (req,res)->{
            News news = gson.fromJson(req.body(), News.class);

            newsDao.addMainNews(news);
            res.status(201);
            res.type("application/json");
            res.redirect("/news/general");
            return null; //gson.toJson(news);
        });
        post("/DepartmentNews/new", "application/json", (req,res)->{
            DepartNews dnews = gson.fromJson(req.body(), DepartNews.class);

            newsDao.addDepartNews(news);
            res.status(201);
            res.type("application/json");

            res.redirect("/news/department");
            return null; //gson.toJson(dnews);
        });

        //FILTERS
        after((req, res) ->{
            //res.type("application/json");
        });

    }
}
