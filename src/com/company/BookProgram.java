package com.company;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookProgram {
    private ArrayList<Book> books = new ArrayList<>();

    public BookProgram() {
        //addBooksFromFile(books, "books.txt");
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public LocalDate setDateToBorrowBook(int amountOfDaysToBorrowBook) {
        LocalDate returnDate = SC.localDate.plusDays(amountOfDaysToBorrowBook);
        return returnDate;
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

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void showOnlyAvailableBooks(ArrayList<Book> bookList, boolean trueAvailableFalseUnavailableBook, String msgWelcome, String messageIfThereIsAvailableBook, String messageIfThereIsNoAvailableBook) {
        if (bookList.size() > 0) {
            SC.messageFieldCenterWithBlankSpace(msgWelcome);
            for (Book book : bookList) {
                if (book.isAvailability() == trueAvailableFalseUnavailableBook) {
                    showAvailableBookWithRandomInformation(book, false, true, true, true, false);
                }
            }
        }
    }

    public void showAvailableBookListWithRandomInformation(ArrayList<Book> bookList, boolean breakLineAfterEachMessage, boolean showTitle, boolean showAuthor, boolean showInformation, boolean showAvailability, String messageIfEmptyList) {
        if (bookList.size() > 0) {
            for (Book book : bookList) {
                if (breakLineAfterEachMessage == true) {
                    System.out.println(showTitleOrNot(book, showTitle));
                    System.out.println(showAuthorOrNot(book, showAuthor));
                    System.out.println(showInformationOrNot(book, showInformation));
                    System.out.println(showAvailabilityOrNot(book, showAvailability));
                    SC.messageFieldCenterWithBlankSpace("--------------------");
                } else {
                    System.out.printf(showTitleOrNot(book, showTitle));
                    System.out.printf(showAuthorOrNot(book, showAuthor));
                    System.out.printf(showInformationOrNot(book, showInformation));
                    System.out.printf(showAvailabilityOrNot(book, showAvailability));
                    System.out.println();
                }
            }
            return;
        }
        SC.messageFieldCenterWithBlankSpace(messageIfEmptyList);
    }

    public void showAvailableBookWithRandomInformation(Book book, boolean breakLineAfterEachMessage, boolean showTitle, boolean showAuthor, boolean showInformation, boolean showAvailability) {
        if (breakLineAfterEachMessage == true) {
            System.out.println(showTitleOrNot(book, showTitle));
            System.out.println(showAuthorOrNot(book, showAuthor));
            System.out.println(showInformationOrNot(book, showInformation));
            System.out.println(showAvailabilityOrNot(book, showAvailability));
            SC.messageFieldCenterWithBlankSpace("--------------------");
        } else {
            System.out.printf(showTitleOrNot(book, showTitle));
            System.out.printf(showAuthorOrNot(book, showAuthor));
            System.out.printf(showInformationOrNot(book, showInformation));
            System.out.printf(showAvailabilityOrNot(book, showAvailability));
            System.out.println();
        }
    }

    private String showTitleOrNot(Book bookToShowInfoOf, boolean showOrNot) {
        if (showOrNot == true) {
            return String.format("%-30s", bookToShowInfoOf.getTitle());
        }
        return String.format("");
    }

    private String showAuthorOrNot(Book bookToShowInfoOf, boolean showOrNot) {
        if (showOrNot == true) {
            return String.format("%-30s", bookToShowInfoOf.getAuthor());
        }
        return String.format("");
    }

    private String showInformationOrNot(Book bookToShowInfoOf, boolean showOrNot) {
        if (showOrNot == true) {
            return String.format("%-25s", bookToShowInfoOf.getInformation());
        }
        return String.format("");
    }

    private String showAvailabilityOrNot(Book book, boolean showOrNot) {
        if (showOrNot == true) {
            if (book.isAvailability() == true) {
                return String.format("   (Tillgänglig)");
            }
            return String.format("   (Ej tillgänglig)");
        }
        return String.format("");
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void sortByTitle(ArrayList<Book> bookListToSort) {
        Collections.sort(bookListToSort, new Sort.SortByTitle());
    }

    public void sortByAuthor(ArrayList<Book> bookListToSort) {
        Collections.sort(bookListToSort, new Sort.SortByAuthor());
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public ArrayList<Book> getBooks() {
        return books;
    }
}
