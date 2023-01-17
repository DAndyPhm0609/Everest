package com.example.everest;

public class Book {
    String name;
    String author;
    int price;
    String rating;
    String category;
    private int bookCover;
    private int starIcon;

    public Book(String name, String author, int price, String rating, int bookCover, int starIcon){
        this.name = name;
        this.author = author;
        this.price = price;
        this.rating = rating;
        this.bookCover = bookCover;
        this.starIcon = starIcon;
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

    public int getBookCover() {
        return bookCover;
    }

    public void setBookCover(int bookCover) {
        this.bookCover = bookCover;
    }

    public int getStarIcon() {
        return starIcon;
    }

    public void setStarIcon(int starIcon) {
        this.starIcon = starIcon;
    }
}

