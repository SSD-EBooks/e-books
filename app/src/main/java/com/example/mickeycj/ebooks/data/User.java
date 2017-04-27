package com.example.mickeycj.ebooks.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by mickeycj on 26/4/2560.
 */

public class User implements Parcelable {
    private double fund;

    private ArrayList<Book> cart;

    public User() {
        cart = new ArrayList<>();
    }

    @SuppressWarnings({"unchecked", "MismatchedReadAndWriteOfArray"})
    public User(Parcel in) {
        cart = new ArrayList<>();
        fund = in.readDouble();
        in.readList(cart, Book.class.getClassLoader());
    }

    public double getFund() { return fund; }

    public void setFund(double fund) { this.fund = fund; }

    public ArrayList<Book> getCart() { return cart; }

    public void setCart(ArrayList<Book> cart) { this.cart = cart; }

    public void addToCart(Book book) { cart.add(book); }

    public double getCartPrice() {
        double price = 0;
        for (Book book : cart) {
            price += book.getPrice();
        }
        return price;
    }

    public void clearCart() {
        cart.clear();
    }

    public boolean buyBooks() {
        double price = 0;
        for (Book book : cart) {
            price += book.getPrice();
            if (price > fund) {
                return false;
            }
        }
        fund -= price;
        cart.clear();
        return true;
    }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(fund);
        dest.writeList(cart);
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
