package com.company;

import java.util.ArrayList;

public class CustomerProgram {
    private ArrayList<Book> myBorrowedBooks = new ArrayList<>();
    private final int MAX_AMOUNT_OF_DAYS_TO_BORROW_BOOK = 7;

    public CustomerProgram() {
        /*System.out.println( myBorrowedBooks.size());
        addBookBySearch();
        System.out.println( myBorrowedBooks.size());*/
    }

    ////////////////////////////////////////////////

    public void showBorrowTime(ArrayList<Book> bookList) {
        if (bookList.size() > 0) {
            for (Book book : bookList) {
                if (SC.amountOfBorrowDaysCounter(book) >= 7) {
                    SC.messageFieldCenterWithBlankSpace("Du har " + SC.amountOfBorrowDaysCounter(book) + " dagar kvar att lämna tillbaka ");
                    SC.messageFieldCenterWithBlankSpace("\"" + book.getTitle() + "\"");
                    System.out.println();
                } else if (SC.amountOfBorrowDaysCounter(book) <= 6 && SC.amountOfBorrowDaysCounter(book) >= 1) {
                    SC.messageFieldCenterWithBlankSpace("Notera att du har mindre än en vecka (" + SC.amountOfBorrowDaysCounter(book) + " dagar) kvar");
                    SC.messageFieldCenterWithBlankSpace("att lämna tillbaka \"" + book.getTitle() + "\"");
                    System.out.println();
                } else if (SC.amountOfBorrowDaysCounter(book) == 0) {
                    SC.messageFieldCenterWithBlankSpace("\"" + book.getTitle() + "\" är inte inlämnad i tid");
                    SC.messageFieldCenterWithBlankSpace("Boken måste lämnas in snarast möjligt!");
                    System.out.println();
                }
            }
        }
    }

    ////////////////////////////////////////////////

    public void borrowBook() {
        /*Program.getCurrentUser().addBook( SC.returnBooksFromLibrary( Program.getBookProgram().getBooks(),
                "Skriv boktitel eller namnet på författaren för att ta bort boken.",
                "Din sökning gav FLERA resultat, vänligen specifiera din sökning.",
                "Din sökning gav inget resultat. Vänligen försök igen.",
                "Listan är tom."));*/
        //customerListToAddBookTo.addBook(Program.getBookProgram().searchByTitleOrAuthorAndReturnIfTrue(bookListToBorrowFrom,"Vilken bok vill du låna? Sök efter titel eller författare.", "Tyvärr finns inte boken du sökte efter, försök igen med\nexakt titel eller författarnamn!", "Tyvärr är boken utlånad för tillfället."));

        Book book = SC.returnBooksFromLibrary(Program.getBooks(),
                "Sök efter boktitel eller namnet på författaren.",
                "Din sökning gav FLERA resultat, vänligen specificera din sökning.",
                "Din sökning gav inget resultat. Vänligen försök igen.",
                "Listan är tom.");
        book.setReturnDate( Program.getBookProgram().setDateToBorrowBook( MAX_AMOUNT_OF_DAYS_TO_BORROW_BOOK ));
        Program.getCurrentUser().addBook( returnBookIfAvailable(book, "Tyvärr är boken redan utlånad :("));

    }

    public void returnBook() {
        /*Program.getCurrentUser().removeBook(SC.returnBooksFromLibrary(Program.getCurrentUser().getBooks(),
                "Skriv boktitel eller namnet på författaren för att ta bort boken.",
                "Din sökning gav FLERA resultat, vänligen specifiera din sökning.",
                "Din sökning gav inget resultat. Vänligen försök igen.",
                "Listan är tom."));*/
        //customerListToAddBookTo.removeBook(Program.getBookProgram().searchByTitleOrAuthorAndReturnIfFalse(bookListToRemoveFrom,"Vilken bok vill du lämna tillbaka?", "Tyvärr finns inte boken du försöker lämna tillbaka! Försök igen."));

        Book book = SC.returnBooksFromLibrary(Program.getCurrentUser().getBooks(),
                "Sök efter boktitel eller namnet på författaren.",
                "Din sökning gav FLERA resultat, vänligen specificera din sökning.",
                "Din sökning gav inget resultat. Vänligen försök igen.",
                "Listan är tom.");
        makeAvailable(Program.getBooks(), book);
        Program.getCurrentUser().removeBook( book );
    }

    private void makeAvailable(ArrayList<Book> bookList, Book bookToMakeAvailable){
        if( bookList.contains( bookToMakeAvailable )){
            bookToMakeAvailable.setAvailability( true );
        }
    }

    private Book returnBookIfAvailable(Book book, String msgIfFail) {
        if (book.isAvailability() == true) {
            book.setAvailability(false);
            return book;
        }
        SC.messageFieldCenterWithBlankSpace(msgIfFail);
        return null;
    }

    private Book returnBookIfUnavailable(Book book) {
        book.setAvailability(true);
        return book;
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
