package com.example.mickeycj.ebooks.repository;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.mickeycj.ebooks.R;
import com.example.mickeycj.ebooks.data.Book;
import com.example.mickeycj.ebooks.data.BookRepository;
import com.example.mickeycj.ebooks.data.JSONBookRepository;
import com.example.mickeycj.ebooks.data.MockUpBookRepository;

import java.util.Observable;
import java.util.Observer;

public class RepositoryActivity extends AppCompatActivity implements RepositoryView, Observer {
    private BookRepository bookRepository;

    private ArrayAdapter<Book> bookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository);

        initListView();
    }

    private void initListView() {
        bookRepository = JSONBookRepository.getInstance();
        ((JSONBookRepository) bookRepository).addObserver(this);
        updateListView();
    }

    private void updateListView() {
        bookAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bookRepository.getBooks());

        ListView listView = (ListView) findViewById(R.id.listview_book_list);
        listView.setAdapter(bookAdapter);
    }

    @Override
    public void update(Observable o, Object arg) {
        updateListView();
    }
}
