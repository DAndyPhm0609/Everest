package com.example.everest;

public class Book {
    String name, author, des, url, price;
    Double rating;

    //constructor
    public Book(String name, String author, String price, Double rating, String des, String url){
        this.name = name;
        this.author = author;
        this.price = price;
        this.rating = rating;
        this.des = des;
        this.url = url;
    }

    //empty constructor
    public Book(){}

    //getter & setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

