package com.example.mickeycj.ebooks.repository;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.mickeycj.ebooks.R;
import com.example.mickeycj.ebooks.data.AbstractBookRepository;
import com.example.mickeycj.ebooks.data.Book;
import com.example.mickeycj.ebooks.data.BookRepository;
import com.example.mickeycj.ebooks.data.JSONBookRepository;

import java.util.Observable;
import java.util.Observer;

public class RepositoryActivity extends AppCompatActivity implements RepositoryView, Observer {
    private BookRepository bookRepository;
    private RepositoryPresenter presenter;

    private ArrayAdapter<Book> bookAdapter;

    private ListView repositoryListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository);

        bookRepository = JSONBookRepository.getInstance();
        ((AbstractBookRepository) bookRepository).addObserver(this);
        presenter = new RepositoryPresenter(bookRepository, this);

        initViewHolders();

        presenter.start();
    }

    private void initViewHolders() {
        repositoryListView = (ListView) findViewById(R.id.listview_book_list);
    }

    @Override
    public void updateRepository() {
        bookAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bookRepository.getBooks());
        repositoryListView.setAdapter(bookAdapter);
    }

    @Override
    public void update(Observable o, Object arg) { updateRepository(); }

    public void onSortByTitleClick(View view) { presenter.onSortByTitleClick(); }

    public void onSortByPublicationYearClick(View view) { presenter.onSortByPublicationYearClick(); }
}
