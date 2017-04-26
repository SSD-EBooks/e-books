package com.example.mickeycj.ebooks.data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Observable;

/**
 * Created by mickeycj on 25/4/2560.
 */

public abstract class AbstractBookRepository extends Observable implements BookRepository {

    protected static BookRepository instance;

    protected final ArrayList<Book> books;
    protected final ArrayList<Book> results;

    private boolean isSortedByTitle;
    private boolean isSortedByPublicationYear;
    private boolean isInAscendingOrder;

    protected AbstractBookRepository() {
        books = new ArrayList<>();
        results = new ArrayList<>();
    }

    @Override
    public ArrayList<Book> getBooks() { return results; }

    @Override
    public void searchByTitle(String title) {
        clearResults();
        for (Book book : books) {
            if (book.getTitle().contains(title)) {
                results.add(book);
            }
        }
    }

    @Override
    public void searchByPublicationYear(int pubYear) {
        clearResults();
        for (Book book : books) {
            if (book.getPubYear() == pubYear) {
                results.add(book);
            }
        }
    }

    @Override
    public void sortByTitle() {
        if (!isSortedByTitle || isSortedByTitle && !isInAscendingOrder) {
            results.sort(new Comparator<Book>() {
                @Override
                public int compare(Book b1, Book b2) {
                    return b1.getTitle().toLowerCase().compareTo(b2.getTitle().toLowerCase());
                }
            });
            isInAscendingOrder = true;
        } else {
            results.sort(new Comparator<Book>() {
                @Override
                public int compare(Book b1, Book b2) {
                    return b2.getTitle().toLowerCase().compareTo(b1.getTitle().toLowerCase());
                }
            });
            isInAscendingOrder = false;
        }
        isSortedByTitle = true;
        isSortedByPublicationYear = false;
    }

    @Override
    public void sortByPublicationYear() {
        if (!isSortedByPublicationYear || isSortedByPublicationYear && !isInAscendingOrder) {
            results.sort(new Comparator<Book>() {
                @Override
                public int compare(Book b1, Book b2) {
                    return b1.getPubYear() - b2.getPubYear();
                }
            });
            isInAscendingOrder = true;
        } else {
            results.sort(new Comparator<Book>() {
                @Override
                public int compare(Book b1, Book b2) {
                    return b2.getPubYear() - b1.getPubYear();
                }
            });
            isInAscendingOrder = false;
        }
        isSortedByTitle = false;
        isSortedByPublicationYear = true;
    }

    @Override
    public void clearResults() {
        results.clear();
        isSortedByTitle = isSortedByPublicationYear = isInAscendingOrder = false;
    }
}
