package com.example.mickeycj.ebooks.book;

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

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity implements BookView {
    private User user;
    private Book book;
    private BookPresenter presenter;

    private ArrayAdapter<String> bookDetailsAdapter;

    private ListView bookDetailsListView;
    private TextView userFundTextView;
    private TextView cartPriceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        user = getIntent().getParcelableExtra("user");
        book = getIntent().getParcelableExtra("book");
        presenter = new BookPresenter(user, book, this);

        initViewHolders();

        presenter.start();
    }

    private void initViewHolders() {
        bookDetailsListView = (ListView) findViewById(R.id.listview_book_details_list);
        userFundTextView = (TextView) findViewById(R.id.textview_user_fund);
        cartPriceTextView = (TextView) findViewById(R.id.textview_cart_price);
    }

    @Override
    public void updateBookDetails() {
        ArrayList<String> details = new ArrayList<>();
        details.add("\nTitle: " + book.getTitle() + "\n");
        details.add("\nID: " + Integer.toString(book.getId()) + "\n");
        details.add("\nPublication Year: " + Integer.toString(book.getPubYear()) + "\n");
        details.add("\nPrice: " + Double.toString(book.getPrice()) + "\n");
        bookDetailsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, details);
        bookDetailsListView.setAdapter(bookDetailsAdapter);
    }

    @Override
    public void updateUserFund() { userFundTextView.setText(String.format("Fund: %.2f", user.getFund())); }

    @Override
    public void updateCartPrice() { cartPriceTextView.setText(String.format("Cart Price: %.2f", user.getCartPrice())); }

    public void onAddToCartClick(View view) { presenter.onAddToCartClick(); }

    @Override
    public void onBackPressed() {
        Intent returnedIntent = new Intent();
        returnedIntent.putExtra("user", user);
        setResult(Activity.RESULT_OK, returnedIntent);
        super.onBackPressed();
    }
}
