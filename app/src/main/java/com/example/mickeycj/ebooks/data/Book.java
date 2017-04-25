package com.example.mickeycj.ebooks.data;

/**
 * Created by mickeycj on 20/4/2560.
 */

public class Book {
    private int id;
    private int pubYear;
    private double price;
    private String title;
    private String imgURL;

    public Book(int id, int pubYear, double price, String title, String imgURL) {
        this.id = id;
        this.pubYear = pubYear;
        this.price = price;
        this.title = title;
        this.imgURL = imgURL;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public int getPubYear() { return pubYear; }

    public void setPubYear(int pubYear) { this.pubYear = pubYear; }

    public double getPrice() { return price; }

    public void setPrice(double price) { this.price = price; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getImgURL() { return imgURL; }

    public void setImgURL(String imgURL) { this.imgURL = imgURL; }

    @Override
    public String toString() {
        return "\nTitle: " + title + "\n\nPublication Year: " + pubYear + "\n";
    }
}
