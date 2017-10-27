package com.lukemi.myandroid.bean;

/**
 * Created by android on 2017/6/13.
 */

public class Village {
    int id;
    String name;

    public Village() {
        super();
    }

    public Village(int id, String name) {
        this.id = id;
        this.name = name;
    }

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
        return "Village{" +
                       "id=" + id +
                       ", name='" + name + '\'' +
                       '}';
    }
}
