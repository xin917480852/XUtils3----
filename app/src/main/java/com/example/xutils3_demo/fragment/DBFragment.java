package com.example.xutils3_demo.fragment;


import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xutils3_demo.R;
import com.example.xutils3_demo.domain.ChildInfo;
import com.example.xutils3_demo.domain.Department;
import com.example.xutils3_demo.domain.Employee;
import com.example.xutils3_demo.domain.RTeacherStudent;
import com.example.xutils3_demo.domain.Student;
import com.example.xutils3_demo.domain.Teacher;

import org.xutils.DbManager;
import org.xutils.db.sqlite.SqlInfo;
import org.xutils.db.table.DbModel;
import org.xutils.db.table.TableEntity;
import org.xutils.ex.DbException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by 小新 on 2016/7/2.
 */
@ContentView(R.layout.db_view)
public class DBFragment extends Fragment {
    public DBFragment() {
        super();
    }
    //1.DaoConfig讲解
    DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
            .setDbName("jike.db")//设置数据库的名字
            //表创建的监听
            .setTableCreateListener(new DbManager.TableCreateListener() {
                @Override
                public void onTableCreated(DbManager db, TableEntity<?> table) {
                    Log.i("xiaoxin", "onTableCreated<<" + table.getName());
                }
            })
//            .setAllowTransaction(true)//设置允许事物 默认true
//            .setDbDir(new File("/mnt/sdcard/"))//设置数据库的路径
//            .setDbVersion(1)//数据库的版本为1
            //设置数据库打开的监听
            .setDbOpenListener(new DbManager.DbOpenListener() {
                @Override
                public void onDbOpened(DbManager db) {


                }
            })

            //数据库更新的监听
           .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
               @Override
               public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                   //开启数据库支持多线程操作，提高数据库性能
                   db.getDatabase().disableWriteAheadLogging();
               }
           });
//2.获取数据库的管理对象
    DbManager db = x.getDb(daoConfig);
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this,inflater,container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
        //完成创建数据库 创建表 添加数据到表中
    //创建的表示ChildInfo（详细看这个类！！！）
    @Event(R.id.create_db)
    private void createDB(View view) throws DbException {
        ArrayList <ChildInfo> childInfos = new ArrayList<ChildInfo>();
        childInfos.add(new ChildInfo("xiaoxin1",21));
        childInfos.add(new ChildInfo("xiaoxin2",22));
        childInfos.add(new ChildInfo("xiaoxin3",23));
        childInfos.add(new ChildInfo("xiaoxin4",24));
        childInfos.add(new ChildInfo("xiaoxin5",25));
        childInfos.add(new ChildInfo("xiaoxin6",26));
        childInfos.add(new ChildInfo("xiaoxin7",27));
        childInfos.add(new ChildInfo("xiaoxin8",28));
        //创建表
        db.save(childInfos);
    }
    //    删除数据库
    @Event(R.id.del_db)
    private void delDB(View view) throws DbException {
        db.dropDb();
    }
    //    4、删除表
    @Event(R.id.del_table)
    private void delTable(View view) throws DbException {
        db.dropTable(ChildInfo.class);
    }
    //查询表中的数据
    @Event(R.id.select_table)
    private void selelctDB(View view) throws DbException {
//        查询表中的第一条数据
        ChildInfo first = db.findFirst(ChildInfo.class);
        Log.i("xiaoxin",first.toString());
        //查询有条件的表中的数据
//        WhereBuilder b = WhereBuilder.b();
//        b.and("age",">",22);
//        b.and("age","<",25);
//        List<ChildInfo> all =db.selector(ChildInfo.class).where(b).findAll();
//        List<ChildInfo> all =db.selector(ChildInfo.class).where("age",">",22).and("age","<",25).findAll();
//        //循环遍历所有的数据
//        for (ChildInfo childInfo:all){
//            Log.i("xiaoxin",childInfo.toString());
//        }
    }
    //修改表中的数据
    @Event(R.id.update_table)
    private void updateTable(View v) throws DbException {
//第一种方法
//        ChildInfo first = db.findFirst(ChildInfo.class);
//        first.setName("xiaoxinlove");
//        first.setAge(100);
//        db.update(first,"age","c_name");
        //第二种方法
//        ChildInfo first = db.findFirst(ChildInfo.class);
//        WhereBuilder b = WhereBuilder.b();
//        b.and("id","=",first.getId());
//        KeyValue age = new KeyValue("age", 1000);
//        KeyValue name = new KeyValue("c_name","张三1000");
//        db.update(ChildInfo.class,b,age,name);
        //第三种方法
        ChildInfo first = db.findFirst(ChildInfo.class);
        first.setName("xiaoxin100000");
        first.setAge(10000);
        db.saveOrUpdate(first);
    }
    //    8、删除表中的数据
    @Event(R.id.del_table_data)
    private void delTableData(View view) throws DbException {
        //删除表中所有数据
        db.delete(ChildInfo.class);
        //WhereBuilder设置条件和上面的一样
//        db.delete(ChildInfo.class,WhereBuilder);
    }
    //保存一对多的关系
    @Event(R.id.save_one_to_many)
    private void saveOneToMany(View view) throws DbException {
        String deptId = "abcd";
        Department department = new Department(deptId,"设计部");

        Employee employee = new Employee(UUID.randomUUID() + "", "张三", deptId);
        Employee employee2 = new Employee(UUID.randomUUID() + "", "李四", deptId);
        Employee employee3 = new Employee(UUID.randomUUID() + "", "王五", deptId);

        db.save(department);
        db.save(employee);
        db.save(employee2);
        db.save(employee3);

    }
    //保存多对多的关系
    @Event(R.id.save_many_to_many)
    private void saveManyToMany(View view) throws DbException {
        String java1 = UUID.randomUUID()+"";
        String java2 = UUID.randomUUID()+"";
        String android1 = UUID.randomUUID()+"";
        String android2 = UUID.randomUUID()+"";
        Student sJava1 = new Student(20, java1, "Java学生一");
        Student sJava2 = new Student(21, java2, "Java学生二");
        Student sAndroid1 = new Student(22, android1, "Android学生一");
        Student sAndroid2 = new Student(23, android2, "Android学生二");

        String aId = UUID.randomUUID()+"";
        String jId = UUID.randomUUID()+"";
        Teacher ateacher = new Teacher("Android老师", aId);
        Teacher jteacher = new Teacher("Java老师", jId);

        db.save(sJava1);
        db.save(sJava2);
        db.save(sAndroid1);
        db.save(sAndroid2);

        db.save(ateacher);
        db.save(jteacher);

        db.save(new RTeacherStudent(java1,jId));
        db.save(new RTeacherStudent(java2,jId));
        db.save(new RTeacherStudent(android1,aId));
        db.save(new RTeacherStudent(android2,aId));
        db.save(new RTeacherStudent(android1,jId));
        db.save(new RTeacherStudent(android2,jId));
    }
    //返回dbModle形式的查询
    @Event(R.id.select_many_to_many)
    private void selectOneToMany(View v) throws DbException {
        //查询设计部所有的成员信息
        String sql = "select id,emp_id,emp_name from employee where dept_id = 'abcd'";
        List<DbModel> dbModelAll = db.findDbModelAll(new SqlInfo(sql));
        for(DbModel dbmodel:dbModelAll){
            String id = dbmodel.getString("id");
            String emp_id = dbmodel.getString("emp_id");
            String emp_name = dbmodel.getString("emp_name");
            Log.i("xiaoxin","id<<"+id+" empName<<"+emp_name+" emp_id<<"+emp_id);
        }
    }
}
