package com.example.csaper6.collegeapp2.Person;

import java.util.Date;

/**
 * Created by csaper6 on 2/6/17.
 */

public class Person {
    private Date dateOfBirth;
    private String fName,lName;
    private String job;

    public Person(Date dateOfBirth, String fName, String lName,String job) {
        this.dateOfBirth = dateOfBirth;
        this.fName = fName;
        this.lName = lName;
        this.job=job ;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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
