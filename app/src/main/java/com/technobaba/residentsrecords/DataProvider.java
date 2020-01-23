package com.technobaba.residentsrecords;

public class DataProvider {

    private int id,age;
    private String name,gender,mobile,address,details,other;



    public DataProvider (int i,String n,String g,int a,String m,String ad,String d,String o)
    {
        this.setId(i);
        this.setName(n);
        this.setGender(g);
        this.setAge(a);
        this.setMobile(m);
        this.setAddress(ad);
        this.setDetails(d);
        this.setOther(o);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}