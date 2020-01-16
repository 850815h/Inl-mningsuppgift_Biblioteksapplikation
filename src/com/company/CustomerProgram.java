package com.company;

import java.util.ArrayList;

public class CustomerProgram {
    private ArrayList<Book> myBorrowedBooks = new ArrayList<>();

    public CustomerProgram() {
    }

    public void showMyBorrowedBooks(String messageIfEmptyList) {
        if (myBorrowedBooks.size() > 0) {
            for (Book book : myBorrowedBooks) {
                System.out.println(Program.getBookProgram().showSimpleInformationOfBook(book));
            }
        } else {
            System.out.println(messageIfEmptyList);
        }
    }

    private void addBookBySearch() {
        //myBorrowedBooks.add(bookProgram.searchByTitleOrAuthor("Vilken bok söker du efter?", "Tyvärr finns inte boken du sökte, försök igen med\nexakt titel eller författare!", "Tyvärr är boken utlånad för tillfället."));
        myBorrowedBooks.add(Program.getBookProgram().searchByTitleOrAuthor("Vilken bok söker du efter?", "Tyvärr finns inte boken du sökte, försök igen med\nexakt titel eller författare!", "Tyvärr är boken utlånad för tillfället."));
    }

    private void addBookByTitle() {
        myBorrowedBooks.add(Program.getBookProgram().searchByTitle("Vilken titel söker du efter?", "Tyvärr finns inte titeln du sökte, försök igen!", "Tyvärr är boken utlånad för tillfället."));
    }

    private void addBookByAuthor() {
        myBorrowedBooks.add(Program.getBookProgram().searchByAuthor("Vilken författare söker du efter?", "Tyvärr finns inte författaren du sökte, försök igen!", "Tyvärr är boken utlånad för tillfället."));
    }

    private Book returnBorrowedBook(String welcomeMessage, String messageIfFail, String messageIfEmptyList) {
        if (myBorrowedBooks.size() > 0) {
            System.out.println(welcomeMessage);
            do {
                String userInput = SC.scanner.nextLine();
                for (Book book : myBorrowedBooks) {
                    if (book.getTitle().toLowerCase().equals(userInput.toLowerCase()) && book.isAvailability() == false ||
                            book.getAuthor().toLowerCase().equals(userInput.toLowerCase()) && book.isAvailability() == false) {
                        book.setAvailability(true);
                        myBorrowedBooks.remove(book);
                        return book;
                    }
                }
                System.out.println(messageIfFail);
            } while (true);
        }
        System.out.println(messageIfEmptyList);
        return null;
    }
}
