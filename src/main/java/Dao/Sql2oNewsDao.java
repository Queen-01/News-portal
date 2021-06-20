package Dao;

import models.DepartNews;
import models.News;

import java.util.List;

public class Sql2oNewsDao implements NewsDao{
    @Override
    public List<News> getAllNews() {
        return null;
    }

    @Override
    public List<News> getMainNews() {
        return null;
    }

    @Override
    public List<DepartNews> getDepartNews() {
        return null;
    }

    @Override
    public void addMainNews(News news) {

    }

    @Override
    public void addDepartNews(DepartNews departNews) {

    }

    @Override
    public News findMainNewsById(int id) {
        return null;
    }

    @Override
    public DepartNews findDepartNewsById(int id) {
        return null;
    }

    @Override
    public void updateMainNews(News news, int userId, String content) {

    }

    @Override
    public void updateDepartNews(DepartNews departNews, int userId, String content, int departId) {

    }

    @Override
    public void clearAllNews() {

    }

    @Override
    public void clearMainNews() {

    }

    @Override
    public void clearDepartNews() {

    }
}
