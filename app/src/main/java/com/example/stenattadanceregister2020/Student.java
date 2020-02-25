package com.example.stenattadanceregister2020;

class Student {
    private String rollno, name, mobileno, email, branch, year, section;

    public Student(String rollno, String name, String mobileno, String email, String branch, String year, String section) {
    this.rollno=rollno;
    this.name=name;
    this.mobileno=mobileno;
    this.email=email;
    this.branch=branch;
    this.year=year;
    this.section=section;

    }

    public Student() {
    }

    public String getRollno() {
        return rollno;
    }

    public String getName() {
        return name;
    }

    public String getMobileno() {
        return mobileno;
    }

    public String getEmail() {
        return email;
    }

    public String getBranch() {
        return branch;
    }

    public String getYear() {
        return year;
    }

    public String getSection() {
        return section;
    }
}
