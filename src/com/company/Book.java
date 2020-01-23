package com.company;

import java.io.Serializable;
import java.time.LocalDate;

public class Book implements Serializable {
    private String title;
    private String author;
    private String information;
    private boolean availability;
    private LocalDate returnDate = LocalDate.now();

    public Book( String title, String author, String information, boolean availability) {
        this.title = title;
        this.author = author;
        this.information = information;
        this.availability = availability;
    }

    public void setReturnDate(LocalDate dateToReturnBook){
        returnDate = dateToReturnBook;
    }

    public LocalDate getReturnDate(){
        return returnDate;
    }



    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getInformation() {
        return information;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean setAvailable){
        availability = setAvailable;
    }
}
