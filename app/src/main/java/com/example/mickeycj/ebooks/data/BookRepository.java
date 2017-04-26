package com.example.mickeycj.ebooks.data;

import java.util.ArrayList;

/**
 * Created by mickeycj on 20/4/2560.
 */

public interface BookRepository {

    ArrayList<Book> getBooks();

    void searchByTitle(String title);

    void searchByPublicationYear(int pubYear);

    void sortByTitle();

    void sortByPublicationYear();

    void clearResults();
}
