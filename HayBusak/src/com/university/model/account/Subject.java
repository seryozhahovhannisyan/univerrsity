package com.university.model.account;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 06.08.13
 * Time: 1:03
 * To change this template use File | Settings | File Templates.
 */
public class Subject {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
