package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BookProgram {
    private ArrayList<Book> books = new ArrayList<>();

    public BookProgram() {
        addBooksFromFile(books, "books.txt");
        //System.out.println(searchByTitle("SKriv boktitel!", "Boken finns ej!", "Boken är utlånad tyvärr."));
        //System.out.println(searchByAuthor("SKriv boktitel!", "Boken finns ej!"));
        //showAllBookList();
        //showAvailableBookWithRandomInformation(true, false, false, true );
    }

    private void addBooksFromFile(ArrayList<Book> listOfBooksToAddBooksTo, String fileName) {
        List<String> lines = FileUtility.readAllLines(fileName);
        for (String str : lines) {
            String[] parts = str.split(",");
            for (int i = 0; i < str.length(); i++) {
                listOfBooksToAddBooksTo.add(new Book(parts[0], parts[1], parts[2], true));
                break;
            }
        }
    }

    public Book searchByTitleOrAuthorAndReturnIfTrue(String welcomeMessage, String messageIfFail, String ifBookIsNotAvailable) {
        String tempMessage = messageIfFail;
        System.out.println(welcomeMessage);
        do {
            String userInput = SC.scanner.nextLine();
            for (Book book : Program.getBookProgram().getBooks()) {
                if (book.getTitle().toLowerCase().equals(userInput.toLowerCase()) && book.isAvailability() == true ||
                        book.getAuthor().toLowerCase().equals(userInput.toLowerCase()) && book.isAvailability() == true) {
                    book.setAvailability(false);
                    return book;
                } else if (book.getTitle().toLowerCase().equals(userInput.toLowerCase()) && book.isAvailability() == false ||
                        book.getAuthor().toLowerCase().equals(userInput.toLowerCase()) && book.isAvailability() == false) {
                    messageIfFail = ifBookIsNotAvailable;
                    break;
                }
            }
            System.out.println(messageIfFail);
            messageIfFail = tempMessage;
        } while (true);
    }

    public Book searchByTitleOrAuthorAndReturnIfFalse(String welcomeMessage, String messageIfFail) {
        System.out.println(welcomeMessage);
        do {
            String userInput = SC.scanner.nextLine();
            for (Book book : Program.getBookProgram().getBooks()) {
                if (book.getTitle().toLowerCase().equals(userInput.toLowerCase()) && book.isAvailability() == false ||
                        book.getAuthor().toLowerCase().equals(userInput.toLowerCase()) && book.isAvailability() == false) {
                    book.setAvailability(true);
                    return book;
                }
            }
            System.out.println(messageIfFail);
        } while (true);
    }

    public Book searchByTitle(String welcomeMessage, String messageIfFail, String ifBookIsNotAvailable) {
        String tempMessage = messageIfFail;
        System.out.println(welcomeMessage);
        do {
            String userInput = SC.scanner.nextLine();
            for (Book book : books) {
                if (book.getTitle().toLowerCase().contains(userInput.toLowerCase()) && book.isAvailability() == true) {
                    book.setAvailability(false);
                    return book;
                } else if (book.getTitle().toLowerCase().contains(userInput.toLowerCase()) && book.isAvailability() == false) {
                    messageIfFail = ifBookIsNotAvailable;
                    break;
                }
            }
            System.out.println(messageIfFail);
            messageIfFail = tempMessage;
        } while (true);
    }

    public Book searchByAuthor(String welcomeMessage, String messageIfFail, String ifBookIsNotAvailable) {
        String tempMessage = messageIfFail;
        System.out.println(welcomeMessage);
        do {
            String userInput = SC.scanner.nextLine();
            for (Book book : books) {
                if (book.getAuthor().toLowerCase().contains(userInput.toLowerCase()) && book.isAvailability() == true) {
                    book.setAvailability(false);
                    return book;
                } else if (book.getAuthor().toLowerCase().contains(userInput.toLowerCase()) && book.isAvailability() == false) {
                    messageIfFail = ifBookIsNotAvailable;
                    break;
                }
            }
            System.out.println(messageIfFail);
            messageIfFail = tempMessage;
        } while (true);
    }

    public void showAvailableOrNotAvailableBook(boolean trueAvailableFalseUnavailableBook, String messageIfThereIsAvailableBook, String messageIfThereIsNoAvailableBook) {
        int falseBooks = 0;
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).isAvailability() == false) {
                falseBooks++;
            }
        }

        if (falseBooks == (books.size() - books.size()) && trueAvailableFalseUnavailableBook == false) {
            System.out.println(messageIfThereIsAvailableBook);
        } else if (falseBooks == books.size() && trueAvailableFalseUnavailableBook == true) {
            System.out.println(messageIfThereIsNoAvailableBook);
        }

        for (Book book : books) {
            if (book.isAvailability() == trueAvailableFalseUnavailableBook) {
                System.out.println(showSimpleInformationOfBook(book));
            } else if (book.isAvailability() == trueAvailableFalseUnavailableBook) {
                System.out.println(showSimpleInformationOfBook(book));
            }
        }


    }

    public void showAvailableBookWithRandomInformation(boolean breakLineAfterEachMessage,boolean showTitle,boolean showAuthor,boolean showInformation,boolean showAvailability) {
        for (Book book : books) {
            if (breakLineAfterEachMessage == true) {
                System.out.printf("%-18s\n", returnAvailableOrNot(book, showAvailability));
                System.out.printf("%-35s\n", returnTitleOrNot(book, showTitle));
                System.out.printf("%-30s\n", returnAuthorOrNot(book, showAuthor));
                System.out.printf("%-20s\n", returnInformationOrNot(book, showInformation));
                System.out.println();
            } else {
                System.out.printf("%-18s", returnAvailableOrNot(book, showAvailability));
                System.out.printf("%-35s", returnTitleOrNot(book, showTitle));
                System.out.printf("%-30s", returnAuthorOrNot(book, showAuthor));
                System.out.printf("%-20s", returnInformationOrNot(book, showInformation));
                System.out.println();
            }
        }
    }

    private String returnTitleOrNot(Book bookToShowInfoOf, boolean showOrNot) {
        if (showOrNot == true) {
            return bookToShowInfoOf.getTitle();
        }
        return "";
    }

    private String returnAuthorOrNot(Book bookToShowInfoOf, boolean showOrNot) {
        if (showOrNot == true) {
            return bookToShowInfoOf.getAuthor();
        }
        return "";
    }

    private String returnInformationOrNot(Book bookToShowInfoOf, boolean showOrNot) {
        if (showOrNot == true) {
            return bookToShowInfoOf.getInformation();
        }
        return "";
    }

    private String returnAvailableOrNot(Book book, boolean showOrNot) {
        if (book.isAvailability() == true) {
            return "(Tillgänglig)";
        }
        return "(Ej tillgänglig)";
    }

    private String showAllBookInformation(Book book) {
        return String.format("%-15s %-20s %-40s %-50s\n",
                book.isAvailability(),
                book.getTitle(),
                book.getAuthor(),
                book.getInformation());
    }

    public String showSimpleInformationOfBook(Book book) {
        //return String.format("%-20s\nAv: %-40s\n%-50s\n%s\n", book.getTitle(), book.getAuthor(), book.getInformation(), returnAvailableOrNot(book.isAvailability()));
        return String.format("%-35s Av: %-40s s%-50s", book.getTitle(), book.getAuthor(), book.getInformation());

    }

    public ArrayList<Book> getBooks(){
        return books;
    }

    public void addBook(Book book) {
        books.add( book );
    }

    public void removeBook(Book book) {
        books.remove( book );
    }

    public void sortByTitle(){
        Collections.sort( Program.getBookProgram().getBooks(), new Sort.SortByTitle());
    }

    public void sortByAuthor(){
        Collections.sort( Program.getBookProgram().getBooks(), new Sort.SortByAuthor());
    }
}
