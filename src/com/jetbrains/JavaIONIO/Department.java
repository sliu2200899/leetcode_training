package com.jetbrains.JavaIONIO;


import java.io.Serializable;

public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    Integer id;
    String name;

    public Department(Integer id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "Department [id=" + id + ", name=" + name + "]";
    }
}