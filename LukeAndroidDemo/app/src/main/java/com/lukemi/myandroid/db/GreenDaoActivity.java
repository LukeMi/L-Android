package com.lukemi.myandroid.db;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.test.mock.MockApplication;
import android.view.View;
import android.widget.TextView;

import com.activeandroid.app.Application;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lukemi.myandroid.R;
import com.lukemi.myandroid.app.MyApplication;
import com.lukemi.myandroid.bean.Student;
import com.lukemi.myandroid.dao.DaoSession;
import com.lukemi.myandroid.dao.StudentDao;
import com.lukemi.myandroid.util.Logcat;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GreenDaoActivity extends AppCompatActivity implements BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.del_history)
    TextView delHistory;
    private List<Student> studentList = new ArrayList<>();
    private StudentDao studentDao;
    private int id = 0;
    private StudentAdapter studentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initData() {
        DaoSession daoSession = ((MyApplication) getApplication()).getDaoSession();
        studentDao = daoSession.getStudentDao();
        studentAdapter = new StudentAdapter(R.layout.view_student_item, studentList);
        studentAdapter.setOnItemClickListener(this);
        rvList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvList.setAdapter(studentAdapter);
    }

    private void initView() {

    }

    @OnClick({R.id.insert, R.id.delete, R.id.update, R.id.query, R.id.del_history})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.insert:
                id++;
                int age = 10;
                Student student = new Student();
                student.setAge(String.valueOf(age + id));
                student.setName("我是学生" + id);
                student.setNumber("1996_" + id);
                insertStudent(student);
                break;
            case R.id.delete:
//                deleteStudent();
                break;
            case R.id.update:
                updateStudent(null);
                break;
            case R.id.query:
                queryStudent(null);
                break;
            case R.id.del_history:
                break;
        }
    }

    private void insertStudent(Student student) {
        if (!queryStudent(student)) {
            studentDao.insert(student);
        } else {
            Logcat.log("student is existed");
        }
    }

    private void deleteStudent(Student student) {

    }

    private void updateStudent(Student student) {

    }

    private boolean queryStudent(Student student) {
        if (student == null) {
            List<Student> students = studentDao.loadAll();
            studentList.clear();
            studentList.addAll(students);
            studentAdapter.notifyDataSetChanged();
            for (int i = 0; i < studentList.size(); i++) {
                Logcat.log("students: " + students.get(i).toString());
            }
            return false;
        } else {
            boolean flag = false;
            List<Student> students = studentDao.loadAll();
            if (students != null && students.size() > 0) {
                for (int i = 0; i < students.size(); i++) {
                    Student s = students.get(i);
                    if (s == student) {
                        flag = true;
                        break;
                    }
                }
                return flag;
            } else {
                return false;
            }

        }

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        deleteStudent(studentList.get(position));
    }


    class StudentAdapter extends BaseQuickAdapter<Student, BaseViewHolder> {

        public StudentAdapter(@LayoutRes int layoutResId, @Nullable List<Student> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, Student item) {
            helper.setText(R.id.id, item.getId() + "")
                    .setText(R.id.number, item.getNumber() + "")
                    .setText(R.id.name, item.getName() + "")
                    .setText(R.id.age, item.getAge() + "");
        }
    }

}
