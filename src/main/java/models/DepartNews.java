package models;

import java.sql.Timestamp;
import java.util.Objects;

public class DepartNews {
    private int departId;
    private int id;
    private String type;
    private String content;

    public DepartNews(int id, int userId, String content, String type, Timestamp posdate,int departId){
//        super(id, userId, type, content, posdate);
        this.departId = departId;
    }

    public int getDepartId() {
        return departId;
    }
    public void setDepartId(int departId){
        this.departId = departId;
    }
    public boolean equals(Object o){
        if (this == o)
            return true;
        if(o == null || getClass() != o.getClass())
            return false;
        if(!super.equals(o)) return false;
        DepartNews that = (DepartNews) o;
        return departId == that.departId;
    }

    public int hashCode(){
        return Objects.hash(super.hashCode(), departId);
    }

    public int getUserId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    public void setUserId(int userId) {
        this.departId = departId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }
}
