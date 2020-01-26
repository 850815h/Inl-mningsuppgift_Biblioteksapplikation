package com.company;

import java.util.Comparator;

public class Sort {

    public static class SortByTitle implements Comparator<Book>{
        public int compare(Book a, Book b){
            return a.getTitle().compareTo( b.getTitle());
        }
    }

    public static class SortByAuthor implements Comparator<Book>{
        public int compare(Book a, Book b){
            return a.getAuthor().compareTo( b.getAuthor() );
        }
    }
}
