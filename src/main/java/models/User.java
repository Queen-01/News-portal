package models;

public class User {
    public int id;
    public String name;
    public String role;
    public String position;
    public int departId;

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
    public int setId(int id){
        return id;
    }
    public String getName(){
        return name;
    }

    public String setName(String name) {
        return this.name;
    }

    public String setRole(String role) {
        return this.role;
    }

    public void setPosition(String position) {
    }

    public void setDepartId(int departId) {
    }

    public int getDepartId() {
        return id;
    }
}
