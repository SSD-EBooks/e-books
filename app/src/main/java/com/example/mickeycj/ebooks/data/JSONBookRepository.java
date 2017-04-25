package com.example.mickeycj.ebooks.data;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by mickeycj on 20/4/2560.
 */

public class JSONBookRepository extends Observable implements BookRepository {

    private static final String dataURL = "https://theory.cpe.ku.ac.th/~jittat/courses/sw-spec/ebooks/books.json";

    private static BookRepository instance;

    public static BookRepository getInstance() {
        if (instance == null) {
            instance = new JSONBookRepository();
        }
        return instance;
    }

    private ArrayList<Book> books;

    private JSONBookRepository() {
        books = new ArrayList<>();
        new JSONLoader().execute();
    }

    @Override
    public ArrayList<Book> getBooks() { return books; }

    private class JSONLoader extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            StringBuilder results = new StringBuilder();
            try {
                URL url = new URL(dataURL);
                URLConnection connection = url.openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    results.append(line + "\n");
                }
                JSONArray jArray = new JSONArray(results.toString());
                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject jBook = jArray.getJSONObject(i);
                    books.add(new Book(jBook.getInt("id"),
                            jBook.getInt("pub_year"),
                            jBook.getDouble("prize"),
                            jBook.getString("title"),
                            jBook.getString("img_url")));
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void param) {
            if (!books.isEmpty()) {
                setChanged();
                notifyObservers();
            }
        }
    }
}
