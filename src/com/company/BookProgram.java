package com.company;

import java.util.ArrayList;

public class BookProgram {
    ArrayList<Book> books = new ArrayList<>();

    public BookProgram() {
        addBooks();
        //showAllBookList();
        showAvailableBookWithRandomInformation(false, false, true, false );
    }


    private void showAllBookList() {
        for (Book book : books) {
            System.out.println(showAllBookInformationAndAvailability(book));
        }
    }

    private void showAvailableBookWithRandomInformation(boolean showTitle, boolean showAuthor, boolean showInformation, boolean showAvailability ) {
        for (Book book : books) {
            //System.out.println( returnTitleOrNot( book, showTitle)
            System.out.printf( "%-20s %-20s %-40s %-50s\n",
                    returnTitleOrNot( book, showTitle),
                    returnAuthorOrNot( book, showAuthor),
                    returnInformationOrNot( book, showInformation),
                    returnAvailableOrNot( showAvailability));
        }
    }

    private String returnTitleOrNot(Book bookToShowInfoOf, boolean showOrNot){
        if (showOrNot == true) {
            return bookToShowInfoOf.getTitle();
        }
        return "";
    }

    private String returnAuthorOrNot(Book bookToShowInfoOf, boolean showOrNot){
        if (showOrNot == true) {
            return bookToShowInfoOf.getAuthor();
        }
        return "";
    }

    private String returnInformationOrNot(Book bookToShowInfoOf, boolean showOrNot){
        if (showOrNot == true) {
            return bookToShowInfoOf.getInformation();
        }
        return "";
    }



    private String showAllBookInformation(Book book) {
        return String.format("%-20s %-40s %-50s\n", book.getTitle(), book.getAuthor(), book.getInformation());
    }

    private String showAllBookInformationAndAvailability(Book book) {
        return String.format("%-20s\nAv: %-40s\n%-50s\n%s\n", book.getTitle(), book.getAuthor(), book.getInformation(), returnAvailableOrNot( book.isAvailability()));

    }

    private void addBooks() {
        books.add( new Book("Ondskan" ,"Jan Guillou","\"Pojken som behandlas illa av sina klasskamrater.\"",true ));
        books.add( new Book("Tomat, det är ingen grönsak!", "\"Patrik Grönkvist", "\"Tomat går att äta till allt!\"", true ));
        books.add( new Book("Sportbilar Delux", "Hassan A.", "\"Om Mercedes Benz och BMW, som tar över världen med storm.\"", true ));
        books.add( new Book("Tysta Johan", "Erik Polako", "\"Erik känner sig otroligt smart jämfört med sina klasskamrater.\"", true ));
        books.add( new Book("Nils och Hundarna", "Lars Isak", "\"Mannen som älskade sina två hundar över allt annat.\"", true ));
        books.add( new Book("Skrattet", "Anna S.", "\"Pojken som behandlas illa av sina klasskamrater\"", true ));
        books.add( new Book("Shoo Kurnia", "Kurnia V.", "\"Hur ska man hälsa på andra medarbetare på jobbet? Räcker det verkligen med bara ett hej?\"", true ));
        books.add( new Book("Stugans svaga länk", "Christian Andersson", "\"Den dramatiska historien om stugan utan dörr.\"", true ));
        books.add( new Book("Skolan som gick i konkurs", "Humanus YH", "\"Vad hände egentligen med eleverna som studerade OOPAi?.\"", true ));
        books.add( new Book("How to become a code master", "Tobias S.", "\"How to use Google and YouTube, to finish your studies.\"", true ));
    }

    private String returnAvailableOrNot(boolean falseOrTrue){
        if (falseOrTrue == true) {
            return "Tillgänglig";
        }
        return "Ej tillgänglig";
    }


}
