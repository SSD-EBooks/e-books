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
import com.example.mickeycj.ebooks.cart.CartActivity;
import com.example.mickeycj.ebooks.data.Book;
import com.example.mickeycj.ebooks.data.User;

public class UserActivity extends AppCompatActivity implements UserView {
    private User user;
    private UserPresenter presenter;

    private ArrayAdapter<Book> booksAdapter;

    private TextView fundTextView;
    private EditText addFundEditText;
    private ListView booksListView;

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
        booksListView = (ListView) findViewById(R.id.listview_books_list);
    }

    @Override
    public void updateFund() {
        fundTextView.setText(String.format("%.2f", user.getFund()));
    }

    @Override
    public void updateBooks() {
        booksAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, user.getBooks());
        booksListView.setAdapter(booksAdapter);
    }

    @Override
    public double getAddFundAmount() {
        try {
            return Double.parseDouble(addFundEditText.getText().toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public void clearAddFundAmount() { addFundEditText.setText(""); }

    public void onAddFundsClick(View view) { presenter.onAddFundsClick(getAddFundAmount()); }

    public void onToCartClick(View view) {
        Intent intent = new Intent(this, CartActivity.class);
        intent.putExtra("user", user);
        startActivityForResult(intent, 0);
    }

    @Override
    public void onBackPressed() {
        Intent returnedIntent = new Intent();
        returnedIntent.putExtra("user", user);
        setResult(Activity.RESULT_OK, returnedIntent);
        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            user = data.getExtras().getParcelable("user");
            updateFund();
            updateBooks();
        }
    }
}
