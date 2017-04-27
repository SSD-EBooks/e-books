package com.example.mickeycj.ebooks.repository;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.mickeycj.ebooks.R;
import com.example.mickeycj.ebooks.book.BookActivity;
import com.example.mickeycj.ebooks.data.AbstractBookRepository;
import com.example.mickeycj.ebooks.data.Book;
import com.example.mickeycj.ebooks.data.BookRepository;
import com.example.mickeycj.ebooks.data.JSONBookRepository;
import com.example.mickeycj.ebooks.data.User;

import java.util.Observable;
import java.util.Observer;

public class RepositoryActivity extends AppCompatActivity implements RepositoryView, Observer {
    private User user;
    private BookRepository bookRepository;
    private RepositoryPresenter presenter;

    private ArrayAdapter<Book> bookAdapter;

    private EditText searchBarEditText;
    private ListView repositoryListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository);

        user = getIntent().getParcelableExtra("user");
        bookRepository = JSONBookRepository.getInstance();
        ((AbstractBookRepository) bookRepository).addObserver(this);
        presenter = new RepositoryPresenter(bookRepository, this);

        initViewHolders();

        presenter.start();
    }

    private void initViewHolders() {
        searchBarEditText = (EditText) findViewById(R.id.edittext_search_bar);
        repositoryListView = (ListView) findViewById(R.id.listview_book_list);
        repositoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(RepositoryActivity.this, BookActivity.class);
                intent.putExtra("user", user);
                intent.putExtra("book", (Book) repositoryListView.getItemAtPosition(position));
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    public void updateRepository() {
        bookAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bookRepository.getBooks());
        repositoryListView.setAdapter(bookAdapter);
    }

    @Override
    public String getTextFromSearchBar() { return searchBarEditText.getText().toString(); }

    @Override
    public void update(Observable o, Object arg) { updateRepository(); }

    public void onSearchByTitleClick(View view) { presenter.onSearchByTitleClick(getTextFromSearchBar()); }

    public void onSearchByPublicationYearClick(View view) { presenter.onSearchByPublicationYear(getTextFromSearchBar()); }

    public void onSortByTitleClick(View view) { presenter.onSortByTitleClick(); }

    public void onSortByPublicationYearClick(View view) { presenter.onSortByPublicationYearClick(); }

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
        }
    }
}
