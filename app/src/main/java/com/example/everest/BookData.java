package com.example.everest;

public class BookData {
    private String name;
    private String bookCover;

    public BookData(String name, String bookCover) {
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

    public String getBookCover() {
        return bookCover;
    }

    public void setBookCover(String bookCover) {
        this.bookCover = bookCover;
    }
}
