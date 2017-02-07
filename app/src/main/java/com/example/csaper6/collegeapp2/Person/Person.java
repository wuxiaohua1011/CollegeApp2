package com.example.csaper6.collegeapp2.Person;

import java.util.Date;

/**
 * Created by csaper6 on 2/6/17.
 */

public class Person {
    private String age;
    private String fName,lName;
    private String job;

    public Person(String age, String fName, String lName,String job) {
        this.age = age;
        this.fName = fName;
        this.lName = lName;
        this.job=job ;
    }

    public String getage() {
        return age;
    }

    public void setageh(String age) {
        this.age = age;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
