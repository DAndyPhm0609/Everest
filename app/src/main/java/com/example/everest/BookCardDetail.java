package com.example.everest;

public class BookCardDetail {
    public String name, author, url, price, star, wishList;
    public Double rating;

    public BookCardDetail(String name, String author, String price, Double rating, String star, String url, String wishList){
        this.name = name;
        this.author = author;
        this.price = price;
        this.rating = rating;
        this.star = star;
        this.url = url;
        this.wishList = wishList;
    }

    public BookCardDetail(){}

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

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getWishList(){return wishList;}

    public void setWishList(String wishList){this.wishList = wishList;}

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}


