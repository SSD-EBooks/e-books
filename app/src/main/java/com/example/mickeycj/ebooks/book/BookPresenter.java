package com.example.mickeycj.ebooks.book;

import com.example.mickeycj.ebooks.data.Book;
import com.example.mickeycj.ebooks.data.User;

/**
 * Created by mickeycj on 27/4/2560.
 */

public class BookPresenter {
    private User user;
    private Book book;
    private BookView view;

    public BookPresenter(User user, Book book, BookView view) {
        this.user = user;
        this.book = book;
        this.view = view;
    }

    public void start() {
        view.updateBookDetails();
        view.updateUserFund();
        view.updateCartPrice();
    }

    public void onAddToCartClick() {
        user.addToCart(book);
        view.updateCartPrice();
    }
}
