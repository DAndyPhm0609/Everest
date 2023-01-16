package com.example.everest;

public class BookData {
    private String name;
    private int bookCover;

    public BookData(String name, int bookCover) {
        this.name = name;
        this.bookCover = bookCover;
    }

    public BookData(){

    }

    public String getBookName() {
        return name;
    }

    public void setBookName(String name) {
        this.name = name;
    }

    public int getBookCover() {
        return bookCover;
    }

    public void setBookCover(int bookCover) {
        this.bookCover = bookCover;
    }
}
