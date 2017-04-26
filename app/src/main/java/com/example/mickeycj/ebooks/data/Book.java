package com.example.mickeycj.ebooks.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mickeycj on 20/4/2560.
 */

public class Book implements Parcelable {
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

    protected Book(Parcel in) {
        id = in.readInt();
        pubYear = in.readInt();
        price = in.readDouble();
        title = in.readString();
        imgURL = in.readString();
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

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(pubYear);
        dest.writeDouble(price);
        dest.writeString(title);
        dest.writeString(imgURL);
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) { return new Book(in); }

        @Override
        public Book[] newArray(int size) { return new Book[size]; }
    };
}
