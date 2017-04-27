package com.example.mickeycj.ebooks.cart;

import com.example.mickeycj.ebooks.data.User;

/**
 * Created by mickeycj on 27/4/2560.
 */

public class CartPresenter {
    private User user;
    private CartView view;

    public CartPresenter(User user, CartView view) {
        this.user = user;
        this.view = view;
    }

    public void start() {
        view.updateFunds();
        view.updatePrice();
        view.updateCart();
    }

    public void onPurchaseClick() {
        user.buyBooks();
        view.updateFunds();
        view.updatePrice();
        view.updateCart();
    }

    public void onClearCartClick() {
        user.clearCart();
        view.updatePrice();
        view.updateCart();
    }
}
