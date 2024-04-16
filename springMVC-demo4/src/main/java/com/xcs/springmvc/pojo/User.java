package com.xcs.springmvc.pojo;

public class User {
    private String username;
    private String age;
    private String password;
    private String sex;

    public User() {
    }

    public User(String username, String age, String password, String sex) {
        this.username = username;
        this.age = age;
        this.password = password;
        this.sex = sex;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

}
