package com.example.mickeycj.ebooks.user;

/**
 * Created by mickeycj on 26/4/2560.
 */

public interface UserView {

    void updateFund();

    void updateCart();

    double getAddFundAmount();

    void clearAddFundAmount();
}
