package com.example.everest;

public class BookData {
    private String bookName;
    private int bookCover;

    public BookData(String bookName, int bookCover) {
        this.bookName = bookName;
        this.bookCover = bookCover;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getBookCover() {
        return bookCover;
    }

    public void setBookCover(int bookCover) {
        this.bookCover = bookCover;
    }
}
