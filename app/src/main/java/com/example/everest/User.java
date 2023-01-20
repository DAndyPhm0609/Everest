package com.example.everest;

import java.util.List;

public class User {
    String name, password, email, phone, address;
    List<Book> favouriteBookList;

    //constructor
    public User(String name, String password, String email, String phone, String address, List<Book> favouriteBookList){
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.favouriteBookList = favouriteBookList;
    }

    //empty constructor
    public User(){

    }

    //setter & getter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {return password;}

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
