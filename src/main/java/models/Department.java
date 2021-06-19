package models;

import java.util.Objects;

public class Department {
    public int id;
    public String name;
    public String description;

    public Department(int id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }
    public boolean equals(Object o){
        if (this == o)
            return true;
        if(o == null || getClass() != o.getClass())
            return false;
        Department that = (Department) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description);
    }
}
