package com.lukemi.android.tutorial.db.dao;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.lukemi.android.tutorial.app.Application;
import com.lukemi.android.tutorial.bean.Student;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by android on 2017/8/1.
 */

public class StudentDaoUtil {

    private static StudentDaoUtil instance;
    private final StudentDao studentDao;
    private final DaoSession daoSession;

    private StudentDaoUtil(@NonNull AppCompatActivity context) {
        daoSession = ((Application) context.getApplication()).getDaoSession();
        studentDao = daoSession.getStudentDao();
    }

    public static StudentDaoUtil getInstance(AppCompatActivity context) {
        if (instance == null) {                         //Single Checked
            synchronized (StudentDaoUtil.class) {
                if (instance == null) {                 //Double Checked
                    instance = new StudentDaoUtil(context);
                }
            }
        }
        return instance;
    }

    public boolean insertStudent(Student student) {
        return studentDao.insert(student) != -1;
    }

    public boolean insertStudentList(final List<Student> studentList) {
        boolean flag = false;
        try {
            daoSession.runInTx(new Runnable() {
                @Override
                public void run() {
                    if (studentList != null && studentList.size() > 0) {
                        studentDao.insertOrReplaceInTx(studentList);
                        for (int i = 0; i < studentList.size(); i++) {
                            Student student = studentList.get(i);
                        }
                    }
                }
            });
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean deletStudent(Student student) {
        boolean flag = false;
        try {
            studentDao.delete(student);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean deleteStudentByKey(long key) {
        boolean flag = false;
        try {
            studentDao.deleteByKey(key);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean updateStudent(Student student) {
        boolean flag = false;
        try {
            studentDao.update(student);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean queryStudent(Student student) {
        boolean flag = false;
        try {
            QueryBuilder<Student> queryBuilder = studentDao.queryBuilder();

            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public List<Student> queryAllStudent() {
        return studentDao.loadAll();
    }


}
