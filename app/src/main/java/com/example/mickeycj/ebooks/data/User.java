package com.example.mickeycj.ebooks.data;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by mickeycj on 26/4/2560.
 */

public class User {
    private double fund;

    private ArrayList<Book> cart;

    public User() {
        cart = new ArrayList<>();
    }

    public double getFund() { return fund; }

    public void setFund(double fund) { this.fund = fund; }

    public ArrayList<Book> getCart() { return cart; }

    public void setCart(ArrayList<Book> cart) { this.cart = cart; }

    public void addToCart(Book... books) {
        for (Book book : books) {
            cart.add(book);
        }
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
}
