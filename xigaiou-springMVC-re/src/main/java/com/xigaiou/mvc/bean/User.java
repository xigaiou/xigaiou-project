package com.xigaiou.mvc.bean;

public class User {
    private String id;
    private String username;
    private String password;
    private String age;
    private String sex;

    public void setId(String id){this.id = id;}
    public void setUsername(String username){this.username = username;}
    public void setPassword(String password){this.password = password;}
    public void setAge(String age){this.age = age;}
    public void setSex(String sex){this.sex = sex;}

    public User(String id, String username, String password, String age, String sex){
        this.id = id;
        this.username = username;
        this.password = password;
        this.age = age;
        this.sex = sex;
    }
}
