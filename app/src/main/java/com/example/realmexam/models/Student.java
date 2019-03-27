package com.example.realmexam.models;

import android.net.Uri;

import io.realm.RealmObject;

public class Student extends RealmObject {
    private String name;
    private int age;
    private String uu;

    public Student() {

    }

    public Student(String name, int age, String uu) {
        this.name = name;
        this.age = age;
        this.uu = uu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUu() {
        return uu;
    }

    public void setUu(String uu) {
        this.uu = uu;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Student{");
        sb.append("name='").append(name).append('\'');
        sb.append(", age=").append(age);
        sb.append('}');
        return sb.toString();
    }
}
