package com.example.mickeycj.ebooks.user;

import com.example.mickeycj.ebooks.data.User;

/**
 * Created by mickeycj on 26/4/2560.
 */

public class UserPresenter {
    private User user;
    private UserView view;

    public UserPresenter(User user, UserView view) {
        this.user = user;
        this.view = view;
    }

    public void start() {
        view.updateFund();
        view.updateBooks();
    }

    public void onAddFundsClick(double amount) {
        user.setFund(user.getFund() + amount);
        view.clearAddFundAmount();
        view.updateFund();
    }
}
