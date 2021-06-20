package Dao;

import models.DepartNews;
import models.News;

import java.util.List;

public interface NewsDao{
    List<News>getAllNews();
    List<News>getMainNews();
    List<DepartNews>getDepartNews();

    void addMainNews(News news);
    void addDepartNews(DepartNews departNews);

    News findMainNewsById(int id);
    DepartNews findDepartNewsById(int id);

    void updateMainNews (News news,int userId, String content);
    void updateDepartNews(DepartNews departNews,int userId, String content, int departId);

    void clearAllNews();
    void clearMainNews();
    void clearDepartNews();

}
