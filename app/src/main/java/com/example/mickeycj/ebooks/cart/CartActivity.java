package com.example.mickeycj.ebooks.cart;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mickeycj.ebooks.R;
import com.example.mickeycj.ebooks.data.Book;
import com.example.mickeycj.ebooks.data.User;

public class CartActivity extends AppCompatActivity implements CartView {
    private User user;
    private CartPresenter presenter;

    private ArrayAdapter<Book> cartAdapter;

    private TextView fundTextView;
    private TextView priceTextView;
    private ListView cartListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        user = getIntent().getParcelableExtra("user");
        presenter = new CartPresenter(user, this);

        initViewHolders();

        presenter.start();
    }

    private void initViewHolders() {
        fundTextView = (TextView) findViewById(R.id.textview_fund);
        priceTextView = (TextView) findViewById(R.id.textview_price);
        cartListView = (ListView) findViewById(R.id.listview_cart);
    }

    @Override
    public void updateFunds() { fundTextView.setText(String.format("Funds: %.2f", user.getFund())); }

    @Override
    public void updatePrice() { priceTextView.setText(String.format("Price: %.2f", user.getCartPrice())); }

    @Override
    public void updateCart() {
        cartAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, user.getCart());
        cartListView.setAdapter(cartAdapter);
    }

    public void onPurchaseClick(View view) { presenter.onPurchaseClick(); }

    public void onClearCartClick(View view) { presenter.onClearCartClick(); }

    @Override
    public void onBackPressed() {
        Intent returnedIntent = new Intent();
        returnedIntent.putExtra("user", user);
        setResult(Activity.RESULT_OK, returnedIntent);
        super.onBackPressed();
    }
}
