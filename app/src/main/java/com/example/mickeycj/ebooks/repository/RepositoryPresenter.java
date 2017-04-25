package com.example.mickeycj.ebooks.repository;

import com.example.mickeycj.ebooks.data.BookRepository;

/**
 * Created by mickeycj on 20/4/2560.
 */

public class RepositoryPresenter {
    private BookRepository repository;
    private RepositoryView view;

    public RepositoryPresenter(BookRepository repository, RepositoryView view) {
        this.repository = repository;
        this.view = view;
    }

    public void start() {
        view.updateRepository();
    }

    public void onSortByTitleClick() {
        repository.sortByTitle();
        view.updateRepository();
    }

    public void onSortByPublicationYearClick() {
        repository.sortByPublicationYear();
        view.updateRepository();
    }
}
