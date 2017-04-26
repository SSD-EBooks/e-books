package com.example.mickeycj.ebooks.main;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.mickeycj.ebooks.R;
import com.example.mickeycj.ebooks.data.BookRepository;
import com.example.mickeycj.ebooks.data.JSONBookRepository;
import com.example.mickeycj.ebooks.data.User;
import com.example.mickeycj.ebooks.repository.RepositoryActivity;
import com.example.mickeycj.ebooks.user.UserActivity;

public class MainActivity extends AppCompatActivity {
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = new User();
    }

    public void onBooksClick(View view) {
        Intent booksIntent = new Intent(this, RepositoryActivity.class);
        booksIntent.putExtra("user", user);
        startActivityForResult(booksIntent, 0);
    }

    public void onUserClick(View view) {
        Intent userIntent = new Intent(this, UserActivity.class);
        userIntent.putExtra("user", user);
        startActivityForResult(userIntent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            user = data.getExtras().getParcelable("user");
        }
    }
}
