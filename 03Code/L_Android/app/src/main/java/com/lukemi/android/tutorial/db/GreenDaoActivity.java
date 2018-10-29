package com.lukemi.android.tutorial.db;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lukemi.android.tutorial.R;
import com.lukemi.android.tutorial.app.ToturialApplication;
import com.lukemi.android.tutorial.bean.Student;
import com.lukemi.android.tutorial.dao.DaoSession;
import com.lukemi.android.tutorial.dao.StudentDao;
import com.lukemi.android.tutorial.util.Logcat;
import com.lukemi.android.tutorial.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GreenDaoActivity extends AppCompatActivity implements BaseQuickAdapter.OnItemLongClickListener,
                                                                           BaseQuickAdapter.OnItemChildClickListener {

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
        DaoSession daoSession = ((ToturialApplication) getApplication()).getDaoSession();
        studentDao = daoSession.getStudentDao();
        studentAdapter = new StudentAdapter(R.layout.view_student_item, studentList);
        studentAdapter.setRelationView(delHistory);
        studentAdapter.setOnItemLongClickListener(this);
        studentAdapter.setOnItemChildClickListener(this);
        rvList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvList.setAdapter(studentAdapter);
    }

    private void initView() {

    }

    @OnClick({R.id.insert, R.id.update, R.id.query, R.id.del_history})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.insert:
                id++;
                int age = 10;
                Student student = new Student();
                student.setAge("age: " + String.valueOf(age + id));
                student.setName("name: " + id);
                student.setNumber("number: " + id);
                insertStudent(student);
                break;
            case R.id.update:
                updateStudent(null);
                break;
            case R.id.query:
                queryStudent(null);
                break;
            case R.id.del_history:
                deleteHistory();
                break;
        }
    }

    private void deleteHistory() {
        studentDao.deleteAll();
        queryStudent(null);
    }

    private void insertStudent(Student student) {
        if (!queryStudent(student)) {
            studentDao.insert(student);
        } else {
            Logcat.log("student is existed");
        }
    }

    private void deleteStudent(Student student) {
        studentDao.delete(student);
        queryStudent(null);
    }

    private void deleteStudentByKey(long id) {
        studentDao.deleteByKey(id);
        queryStudent(null);
    }

    private void updateStudent(Student student) {

    }

    private void queryStudentByName(String name) {
        Student student = studentDao.queryBuilder().where(StudentDao.Properties.Name.eq(name)).build().unique();
        ToastUtil.show_makeText(this, student != null ? student.toString() : "当前id未找到：", Toast.LENGTH_SHORT);
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
    public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final Student student = studentList.get(position);
        final Long id = student.getId();
        builder.setMessage("改名字: ")
                .setMessage("更改的是：id= " + id)
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        student.setName("更改的是：id= " + id);
                        updateStudent(student);
                    }
                })
                .setNegativeButton(R.string.cancle, null)
                .create()
                .show();
        return false;
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        Student student = studentList.get(position);
        switch (view.getId()) {
            case R.id.btn_del:
                deleteStudent(student);
                break;
            case R.id.btn_query:
                boolean b = queryStudent(student);
                ToastUtil.show_makeText(this, b ? "数据库中查询到该条数据：" + student.toString() : "数据库中未查询到该条数据", Toast.LENGTH_SHORT);
                break;
            case R.id.btn_id:
                queryStudentByName(student.getName());
                break;
            case R.id.btn_DEL_id:
                deleteStudentByKey(student.getId());
                break;
            default:
                break;
        }
    }

    class StudentAdapter extends BaseQuickAdapter<Student, BaseViewHolder> {
        private View view;

        public StudentAdapter(@LayoutRes int layoutResId, @Nullable List<Student> data) {
            super(layoutResId, data);
        }

        @Override
        public int getItemCount() {
            int itemCount = super.getItemCount();
            if (itemCount != 0 && view != null) {
                view.setVisibility(View.VISIBLE);
            } else {
                view.setVisibility(View.GONE);
            }
            return itemCount;
        }

        @Override
        protected void convert(BaseViewHolder helper, Student item) {
            helper.setText(R.id.id, "id: " + item.getId())
                    .setText(R.id.number, item.getNumber() + "")
                    .setText(R.id.name, item.getName() + "")
                    .setText(R.id.age, item.getAge() + "")
                    .addOnClickListener(R.id.btn_del)
                    .addOnClickListener(R.id.btn_query)
                    .addOnClickListener(R.id.btn_DEL_id)
                    .addOnClickListener(R.id.btn_id);
        }

        public void setRelationView(View view) {
            this.view = view;
        }
    }

}