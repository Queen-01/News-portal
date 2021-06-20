package models;

import java.sql.Timestamp;

public class DepartNews {
    public int departId;

    public DepartNews(int id, int userId, String content, String type, Timestamp date,int departId){
        super(id,userId,content,type,date);
        this.departId = departId;
    }
}
