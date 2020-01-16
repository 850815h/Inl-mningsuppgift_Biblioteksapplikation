package com.company;

import java.util.ArrayList;

public class LibrarianProgram {

    public LibrarianProgram() {
    }

    public Book removeBooksFromLibrary(String welcomeMessage, String messageIfFail, String messageIfEmptyList) {
        if (Program.getBookProgram().getBooks().size() > 0) {
            System.out.println(welcomeMessage);
            do {
                String userInput = SC.scanner.nextLine();
                for (Book book : Program.getBookProgram().getBooks()) {
                    if (book.getTitle().toLowerCase().equals(userInput.toLowerCase()) ||
                            book.getAuthor().toLowerCase().equals(userInput.toLowerCase())) {
                        Program.getBookProgram().removeBook(book);
                        return book;
                    }
                }
                System.out.println(messageIfFail);
            } while (true);
        }
        System.out.println(messageIfEmptyList);
        return null;
    }

    public void addBooksToLibrary(String messageTitleInput, String messageAuthorInput, String messageInformationInput) {
        String titleInput = "";
        String authorInput = "";
        String informationInput = "";
        do {
            do {
                System.out.println(messageTitleInput);
                titleInput = SC.scanner.nextLine();
            } while (titleInput.isBlank());
            do {
                System.out.println(messageAuthorInput);
                authorInput = SC.scanner.nextLine();
            } while (authorInput.isBlank());
            do {
                System.out.println(messageInformationInput);
                informationInput = SC.scanner.nextLine();
            } while (informationInput.isBlank());
            Program.getBookProgram().addBook(new Book(titleInput, authorInput, informationInput, true));
            return;
        } while (true);
    }
}
