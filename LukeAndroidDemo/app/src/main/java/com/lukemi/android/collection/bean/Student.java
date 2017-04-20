package com.lukemi.android.collection.bean;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.ToOne;

/**
* GreenDao 实体类（注意注解的使用）
*
* created bt: tubg
* created at: 2017/4/11 10:46
* e_mail: tbug.chen@gmail.com / tbugchen@163.com
*/
@Entity
public class Student {

    @Id(autoincrement = true)
    public Long id;
    public String name;
    public String age;
    @Index(unique = true)
    public String number;
    public String score;
    @Generated(hash = 1672075654)
    public Student(Long id, String name, String age, String number, String score) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.number = number;
        this.score = score;
    }
    @Generated(hash = 1556870573)
    public Student() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAge() {
        return this.age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getNumber() {
        return this.number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public String getScore() {
        return this.score;
    }
    public void setScore(String score) {
        this.score = score;
    }

}
 
