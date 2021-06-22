package Dao;

import models.DepartNews;
import models.News;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Sql2oNewsDao implements NewsDao {
//    public static final String DEPARTMENT_NEWS = ;
    private final Sql2o sql2o;
    public static final String Main_news = "main";
    public static final String Depart_news = "depart";
    private int id;
    private int departId;
    private String content;
    private User news;

    public Sql2oNewsDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public List<News> getAllNews() {
        List<News> news = new ArrayList<>();
        news.addAll(getMainNews());
//        news.addAll(getDepartNews());
        return news;
    }

    @Override
    public List<News> getMainNews() {
        String sql = "SELECT * FROM news WHERE type = :type";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .addParameter("type", Main_news)
                    .executeAndFetch(News.class);
        }
    }

    @Override
    public List<DepartNews> getDepartNews() {
        String sql = "SELECT * FROM news WHERE type = :type";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("type", Depart_news)
                    .executeAndFetch(DepartNews.class);
        }
    }

    @Override
    public void addMainNews(News news) {
        String sql = "INSERT INTO news (userId, type, content, posday) VALUES (:userId, :type, :content, now());";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .addParameter("userId", news.getUserId())
                    .addParameter("type", news.getType())
                    .addParameter("content", news.getContent())
                    .executeUpdate()
                    .getKey();
            news.setId(id);
        }

    }

    @Override
    public void addDepartNews(DepartNews departNews) {
        String sql = "INSERT INTO news (userId,type,content,posdate,departId) VALUES (:userId,:type,:content,now(),:departId);";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .addParameter("userId", departNews.getUserId())
                    .addParameter("type", departNews.getType())
                    .addParameter("content", departNews.getContent())
                    .addParameter("departId", departNews.getDepartId())
                    .executeUpdate()
                    .getKey();
            departNews.setDepartId(id);
        }

    }

    @Override
    public DepartNews findMainNewsById(int id) {
        this.id = id;
        String sql = " select * from news where type=:type and id=:id";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .addParameter("type", Main_news)
                    .addParameter("id", id)
                    .executeAndFetchFirst(DepartNews.class);
        }
    }

    @Override
    public DepartNews findDepartNewsById(int id) {
        String sql = " select * from news where type=:type and id=:id";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("type", Depart_news)
                    .addParameter("id", id)
                    .executeAndFetchFirst(DepartNews.class);
        }
    }

    @Override
    public void updateMainNews(News news, int userId, String content) {
        String sql = "update news set (userId, content) = (:userId, :content)  where id=:id ";
        try(Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("userId",userId)
                    .addParameter("content",news.getContent())
                    .addParameter("id",news.getId())
                    .executeUpdate();
            news.setUserId(userId);
            news.setContent(content);
        }

    }

    @Override
    public void updateDepartNews(DepartNews departNews, int userId, String content, int departId) {
        String sql = "update news set (userId, content,departmentId) = (:userId,  :content,:departmentId)  where id=:id ";
        try(Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("userId",userId)
                    .addParameter("content",content)
                    .addParameter("departId",departId)
                    .addParameter("id",departNews.getId())
                    .executeUpdate();
            departNews.setUserId(userId);
            departNews.setContent(content);
            departNews.setDepartId(departId);
        }
    }

    @Override
    public void clearAllNews() {
        String sql="delete from news";
        try(Connection con = sql2o.open()){
            con.createQuery(sql).executeUpdate();
        }
    }

    @Override
    public void clearMainNews() {
        String sql="delete from news where type = :type";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("type",Main_news)
                    .executeUpdate();
        }
    }

    @Override
    public void clearDepartNews() {
        String sql="delete from news where type = :type";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("type",Depart_news)
                    .executeUpdate();
        }
    }
//    public void getDepartUsersById() {
//        return UserDao.getAllUsers().stream()
//                .filter(user -> user.getDepartId()==id )
//                .collect(Collectors.toList());
//    }
}
