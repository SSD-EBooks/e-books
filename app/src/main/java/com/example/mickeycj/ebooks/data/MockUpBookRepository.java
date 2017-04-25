package com.example.mickeycj.ebooks.data;

import java.util.ArrayList;

/**
 * Created by mickeycj on 20/4/2560.
 */

public class MockUpBookRepository extends AbstractBookRepository {

    public static BookRepository getInstance() {
        if (instance == null) {
            instance = new MockUpBookRepository();
        }
        return instance;
    }

    private MockUpBookRepository() {
        super();
        books.add(new Book(471,
                2017,
                24.95,
                "Functional Web Development with Elixir, OTP, and Phoenix",
                "https://imagery.pragprog.com/products/471/lhelph_largebeta.jpg"));
        books.add(new Book(504,
                2017,
                24.95,
                "A Common-Sense Guide to Data Structures and Algorithms",
                "https://imagery.pragprog.com/products/504/jwdsal_largebeta.jpg"));
        books.add(new Book(508,
                2017,
                24.95,
                "Rails, Angular, Postgres, and Bootstrap, Second Edition",
                "https://imagery.pragprog.com/products/508/dcbang2_largebeta.jpg"));
        books.add(new Book(444,
                2017,
                19.0,
                "Effective Testing with RSpec 3",
                "https://imagery.pragprog.com/products/444/rspec3_largebeta.jpg"));
        books.add(new Book(486,
                2017,
                26.95,
                "Design It!",
                "https://imagery.pragprog.com/products/486/mkdsa_largebeta.jpg"));
    }
}
