package models;

public class User {
    public int id;
    public String name;
    public String role;
    public String position;
    public int departId;
    private String content;

    public void setId(int id, String name, String role, String position, int departId) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.position = position;
        this.departId = departId;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setDepartId(int departId) {
        this.departId = departId;
    }

    public int getDepartId() {
        return id;
    }

}
