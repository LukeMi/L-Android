package com.lukemi.android.tutorial.list_r;


import androidx.annotation.NonNull;

/**
 * Created by chenmz
 * on 2018/1/12 0012.
 */

public class StudentBean implements Comparable<StudentBean> {
    private int grade;//年级
    private String name;// 姓名

    public StudentBean() {
    }

    public StudentBean(int grade, String name) {
        this.grade = grade;
        this.name = name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "StudentBean{" +
                "grade=" + grade +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(@NonNull StudentBean o) {
        return this.getGrade() - o.getGrade();
    }
}
