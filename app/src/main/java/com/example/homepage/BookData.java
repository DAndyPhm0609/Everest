package com.example.homepage;

public class BookData {
    private String bookName;
    private int imageBook;

    public BookData(String bookName, int imageBook) {
        this.bookName = bookName;
        this.imageBook = imageBook;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getImageBook() {
        return imageBook;
    }

    public void setImageBook(int imageBook) {
        this.imageBook = imageBook;
    }
}
