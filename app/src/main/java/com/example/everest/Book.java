package com.example.everest;

public class Book {
    String name;
    String author;
    int price;
    String rating;
    String category;
    String state;
    private int bookCover;

    public Book(String name, String author, int price, String rating, String category, String state, int bookCover){
        this.name = name;
        this.author = author;
        this.price = price;
        this.rating = rating;
        this.category = category;
        this.state = state;
        this.bookCover = bookCover;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getBookCover() {
        return bookCover;
    }

    public void setBookCover(int bookCover) {
        this.bookCover = bookCover;
    }
}
