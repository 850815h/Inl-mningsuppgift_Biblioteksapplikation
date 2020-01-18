package com.company;

import java.io.Serializable;
import java.util.ArrayList;

public class LibraryProgram implements Serializable {
    private static ArrayList<User> customers = new ArrayList<>();
    private static ArrayList<User> librarians = new ArrayList<>();
    private static Customer currentCustomer = new Customer("Tobbe", "123");
    private static ArrayList<Book> myBooks = currentCustomer.getBooks();
    private static ArrayList<Book> books = new ArrayList<>();

    public static void addCustomer(Customer customer){
        customers.add( customer );
    }

    public static void addLibrarian(Librarian librarian){
        librarians.add( librarian );
    }

    public static ArrayList<User> getCustomers() {
        return customers;
    }

    public static ArrayList<User> getLibrarians() {
        return librarians;
    }

    public static Customer getCurrentCustomer() {
        return currentCustomer;
    }

    public static void setCurrentCustomer(Customer newCustomer) {
        currentCustomer = newCustomer;
    }

    public static ArrayList<Book> getMyBooks() {
        return myBooks;
    }

    public static ArrayList<Book> getBooks() {
        return books;
    }
}
