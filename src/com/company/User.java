package com.company;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class User implements Serializable {
    private String name;
    private String password;
    private ArrayList<Book> books = new ArrayList<>();

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName(){
        return name;
    }

    public String getPassword(){
        return password;
    }

    public void addBook(Book book){
        books.add( book );
    }

    public void removeBook(Book book){
        books.remove( book );
    }

    public ArrayList<Book> getBooks(){
        return books;
    }
}
