package com.example.mickeycj.ebooks.user;

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

        user = new User();
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
        fundTextView.setText((Double.toString(user.getFund())));
    }

    @Override
    public void updateCart() {
        cardAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, user.getCart());
        cartListView.setAdapter(cardAdapter);
    }

    @Override
    public String getAddFundAmount() { return addFundEditText.getText().toString(); }

    @Override
    public void clearAddFundAmount() { addFundEditText.setText(""); }

    public void onAddFundsClick(View view) { presenter.onAddFundsClick(getAddFundAmount()); }

    public void onPurchaseClick(View view) { presenter.onPurchaseClick(); }

    public void onClearCartClick(View view) { presenter.onClearCartClick(); }

}
