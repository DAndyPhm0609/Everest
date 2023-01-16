package com.example.everest;

public class Book {
    String name;
    String author;
    String price;
    Double rating;
    String category;
    String imageURL;

    public Book(String name, String author, String price, Double rating, String category, String state, String imageURL, int bookCover){
        this.name = name;
        this.author = author;
        this.price = price;
        this.rating = rating;
        this.category = category;
        this.imageURL = imageURL;
    }

    public Book(){

    }

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

}
