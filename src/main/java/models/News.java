package models;

import java.sql.Timestamp;
import java.util.Objects;

public class News {
    private int id;
    private int userId;
    private String content;
    private String type;
    private Timestamp posdate;

    public News(int id, int userId, String content, String type, Timestamp posdate){
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.type = type;
        this.posdate = posdate;
    }

    public String getContent(){
        return content;
    }
    public void setContent(String content){
        this.content = content;
    }
    public String getType(){
        return type;
    }
    public void setType(String type){
        this.type = type;
    }
    public Timestamp getPosdate() {
        return posdate;
    }
    public void setPosdate(Timestamp posdate) {
        this.posdate = posdate;
    }
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return id == news.id &&
                userId == news.userId &&
                Objects.equals(type, news.type) &&
                Objects.equals(content, news.content);
    }
    @Override
    public int hashCode(){
        return Objects.hash(id,userId,type,content);
    }

    public int getUserId() {
        return userId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }
}
