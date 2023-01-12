package com.example.everest;

import java.util.List;

public class User {
    String name;
    String password;
    String age;
    String phoneNo;
    String address;
    List<Book> buyBookList;

    public User(String name, String password, String age, String phoneNo, String address, List<Book> buyBookList){
        this.name = name;
        this.password = password;
        this.age = age;
        this.phoneNo = phoneNo;
        this.address = address;
        this.buyBookList = buyBookList;
    }
}
