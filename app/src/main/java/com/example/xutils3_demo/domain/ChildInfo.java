package com.example.xutils3_demo.domain;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by 小新 on 2016/7/3.
 */
//表的创建 onCreated是表创建的时候执行的sql语句，我们可以再表创建的时候插入一条语句
    @Table(name="child_info",onCreated = "")
public class ChildInfo {
    @Column(name = "age")
    private int age;
    @Column(name ="c_name")
    private String name;
    //列的名字为id，自动增长，是主键 property约束不能为空
    @Column(name="id",autoGen = true,isId = true,property = "NOT NULLL")
    private int id;

    public ChildInfo(String name, int age) {
        this.age = age;
        this.name = name;
    }
//必须声明默认的构造方法，不然创建不成功
    public ChildInfo() {
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ChildInfo{"+
                "id="+id+"cName="+name+"age"+age+"}";
    }
}
