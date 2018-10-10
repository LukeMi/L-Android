package com.lukemi.android.tutorial.dagger2;


public class StudentBean {

    private String name;
    private String degree;


    public StudentBean() {
    }

    public StudentBean(String name, String degree) {
        this.name = name;
        this.degree = degree;
    }

    public void eat() {
        System.out.println("eat-----------");
    }

    public void eats() {
        System.out.println("eat-----------");
    }

    @Override
    public String toString() {
        return "StudentBean{" +
                "name='" + name + '\'' +
                ", degree='" + degree + '\'' +
                '}';
    }
}
