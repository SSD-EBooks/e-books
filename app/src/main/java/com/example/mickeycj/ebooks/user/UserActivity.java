package com.example.mickeycj.ebooks.user;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mickeycj.ebooks.R;
import com.example.mickeycj.ebooks.data.Book;
import com.example.mickeycj.ebooks.data.User;

public class UserActivity extends AppCompatActivity implements UserView {
    private User user;
    private UserPresenter presenter;

    private ArrayAdapter<Book> cardAdapter;

    private TextView fundTextView;
    private EditText addFundEditText;
    private ListView cartListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        user = getIntent().getParcelableExtra("user");
        presenter = new UserPresenter(user, this);

        initViewHolders();

        presenter.start();
    }

    private void initViewHolders() {
        fundTextView = (TextView) findViewById(R.id.textview_fund_amount);
        addFundEditText = (EditText) findViewById(R.id.edittext_add_amount);
        cartListView = (ListView) findViewById(R.id.listview_cart_list);
    }

    @Override
    public void updateFund() {
        fundTextView.setText(String.format("%.2f", user.getFund()));
    }

    @Override
    public void updateCart() {
        cardAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, user.getCart());
        cartListView.setAdapter(cardAdapter);
    }

    @Override
    public double getAddFundAmount() { return Double.parseDouble(addFundEditText.getText().toString()); }

    @Override
    public void clearAddFundAmount() { addFundEditText.setText(""); }

    public void onAddFundsClick(View view) { presenter.onAddFundsClick(getAddFundAmount()); }

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
