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

    public void onSearchByTitleClick(String title) {
        repository.clearResults();
        repository.searchByTitle(title);
        view.updateRepository();
    }

    public void onSearchByPublicationYear(String pubYearStr) {
        repository.clearResults();
        try {
            int pubYear = Integer.parseInt(pubYearStr);
            repository.searchByPublicationYear(pubYear);
            view.updateRepository();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
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
