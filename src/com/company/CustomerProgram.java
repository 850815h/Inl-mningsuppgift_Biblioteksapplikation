package com.company;

import java.util.ArrayList;

public class CustomerProgram {
    private ArrayList<Book> myBorrowedBooks = new ArrayList<>();

    public CustomerProgram() {
        /*System.out.println( myBorrowedBooks.size());
        addBookBySearch();
        System.out.println( myBorrowedBooks.size());*/
    }

<<<<<<< Updated upstream
    public void addBookBySearch(ArrayList<Book> bookListToBorrowFrom, User customerListToAddBookTo) {
        customerListToAddBookTo.addBook(Program.getBookProgram().searchByTitleOrAuthorAndReturnIfTrue(bookListToBorrowFrom,"Vilken bok vill du låna? Sök efter titel eller författare.", "Tyvärr finns inte boken du sökte efter, försök igen med\nexakt titel eller författarnamn!", "Tyvärr är boken utlånad för tillfället."));
    }

    public void returnBookBySearch(ArrayList<Book> bookListToRemoveFrom, User customerListToAddBookTo) {
        customerListToAddBookTo.removeBook(Program.getBookProgram().searchByTitleOrAuthorAndReturnIfFalse(bookListToRemoveFrom,"Vilken bok vill du lämna tillbaka?", "Tyvärr finns inte boken du försöker lämna tillbaka! Försök igen."));
=======
    public void loanBook() {
        Program.getCurrentUser().addBook( SC.returnBooksFromLibrary( Program.getBookProgram().getBooks(),
                "Skriv boktitel eller namnet på författaren för att ta bort boken.",
                "Din sökning gav FLERA resultat, vänligen specifiera din sökning.",
                "Din sökning gav inget resultat. Vänligen försök igen.",
                "Listan är tom."));
        //customerListToAddBookTo.addBook(Program.getBookProgram().searchByTitleOrAuthorAndReturnIfTrue(bookListToBorrowFrom,"Vilken bok vill du låna? Sök efter titel eller författare.", "Tyvärr finns inte boken du sökte efter, försök igen med\nexakt titel eller författarnamn!", "Tyvärr är boken utlånad för tillfället."));
    }

    public void returnBook(ArrayList<Book> bookListToRemoveFrom, User customerListToAddBookTo) {
        Program.getCurrentUser().removeBook( SC.returnBooksFromLibrary( Program.getCurrentUser().getBooks(),
                "Skriv boktitel eller namnet på författaren för att ta bort boken.",
                "Din sökning gav FLERA resultat, vänligen specifiera din sökning.",
                "Din sökning gav inget resultat. Vänligen försök igen.",
                "Listan är tom."));
        //customerListToAddBookTo.removeBook(Program.getBookProgram().searchByTitleOrAuthorAndReturnIfFalse(bookListToRemoveFrom,"Vilken bok vill du lämna tillbaka?", "Tyvärr finns inte boken du försöker lämna tillbaka! Försök igen."));
>>>>>>> Stashed changes
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
