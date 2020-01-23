package com.company;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BookProgram {
    private ArrayList<Book> books = new ArrayList<>();

    public BookProgram() {
        //addBooksFromFile(books, "books.txt");
    }

    ////////////////////////////////////////////////////////////////

    public LocalDate setDateToBorrowBook(int amountOfDaysToBorrowBook) {
        LocalDate returnDate = SC.localDate.plusDays(amountOfDaysToBorrowBook);
        return returnDate;
    }

    public void notificationMsgForBorrowDays(ArrayList<Book> bookList) {
        if (bookList.size() > 0) {
            for (Book book : bookList) {
                if (SC.amountOfBorrowDaysCounter(book) >= 7) {
                    SC.messageFieldWholeWithoutBlankSpace();
                    SC.messageFieldCenterWithBlankSpace("Du har " + SC.amountOfBorrowDaysCounter(book) + " dagar kvar att lämna tillbaka ");
                    SC.messageFieldCenterWithBlankSpace("\"" + book.getTitle() + "\"");
                    SC.messageFieldWholeWithoutBlankSpace();
                } else if (SC.amountOfBorrowDaysCounter(book) <= 6 && SC.amountOfBorrowDaysCounter(book) >= 1) {
                    SC.messageFieldWholeWithoutBlankSpace();
                    SC.messageFieldCenterWithBlankSpace("Notera att du har mindre än en vecka (" + SC.amountOfBorrowDaysCounter(book) + " dagar) kvar");
                    SC.messageFieldCenterWithBlankSpace("att lämna tillbaka \"" + book.getTitle() + "\"");
                    SC.messageFieldWholeWithoutBlankSpace();
                } else if (SC.amountOfBorrowDaysCounter(book) == 0) {
                    SC.messageFieldWholeWithoutBlankSpace();
                    SC.messageFieldCenterWithBlankSpace("Du har inte lämnat in \"" + book.getTitle() + "\" i tid");
                    SC.messageFieldCenterWithBlankSpace("och blir därför tvungen att betala en avgift på 19,999kr!");
                    SC.messageFieldCenterWithBlankSpace("Betalar du inte in boten i tid, anmäls du vidare till");
                    SC.messageFieldCenterWithBlankSpace("Inkasso och Kronofogden. Länge leve Kungen!");
                    SC.messageFieldWholeWithoutBlankSpace();
                }
            }
        }
    }

    ////////////////////////////////////////////////////////////////

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

    ////////////////////////////////////////////////////////////////

    public Book searchByTitleOrAuthorAndReturnIfTrue(ArrayList<Book> bookList, String welcomeMessage, String messageIfFail, String ifBookIsNotAvailable) {
        String tempMessage = messageIfFail;
        System.out.println();
        System.out.println(welcomeMessage);
        do {
            String userInput = SC.scanner.nextLine();
            for (Book book : bookList) {
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

    public Book searchByTitleOrAuthorAndReturnIfFalse(ArrayList<Book> bookList, String welcomeMessage, String messageIfFail) {
        System.out.println(welcomeMessage);
        do {
            String userInput = SC.scanner.nextLine();
            for (Book book : bookList) {
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

    ////////////////////////////////////////////////////////////////

    public void showOnlyAvailableBooks(ArrayList<Book> bookList, boolean trueAvailableFalseUnavailableBook, String msgWelcome, String messageIfThereIsAvailableBook, String messageIfThereIsNoAvailableBook) {
        if (bookList.size() > 0) {
            SC.messageFieldCenterWithBlankSpace(msgWelcome);
            for (Book book : bookList) {
                if (book.isAvailability() == trueAvailableFalseUnavailableBook) {
                    //System.out.println(showSimpleInformationOfBook(book));
                    //showAllBookInformation(book);
                    showAvailableBookWithRandomInformation(book,false,true,true,true,false);
                } /*else {
                    //System.out.println(showSimpleInformationOfBook(book));
                    //showAllBookInformation(book);
                    showAvailableBookWithRandomInformation(book,false,true,true,true,true);
                }*/
            }
        }
    }

    public void showOnlyUnavailableBooks(ArrayList<Book> bookList, boolean trueAvailableFalseUnavailableBook, String msgWelcome, String messageIfThereIsAvailableBook, String messageIfThereIsNoAvailableBook) {
        if (bookList.size() > 0) {
            SC.messageFieldCenterWithBlankSpace(msgWelcome);
            for (Book book : bookList) {
                if (book.isAvailability() == trueAvailableFalseUnavailableBook) {
                    //System.out.println(showSimpleInformationOfBook(book));
                    //showAllBookInformation(book);
                    showAvailableBookWithRandomInformation(book,false,true,true,true,false);
                } /*else {
                    //System.out.println(showSimpleInformationOfBook(book));
                    //showAllBookInformation(book);
                    showAvailableBookWithRandomInformation(book,false,true,true,true,true);
                }*/
            }
        }
    }

    public void showAvailableOrNotAvailableBook(ArrayList<Book> bookList, boolean trueAvailableFalseUnavailableBook, String msgWelcome, String messageIfThereIsAvailableBook, String messageIfThereIsNoAvailableBook) {
        if (bookList.size() > 0) {
            System.out.println();
            SC.messageFieldCenterWithBlankSpace(msgWelcome);
            int falseBooks = 0;
            for (int i = 0; i < bookList.size(); i++) {
                if (bookList.get(i).isAvailability() == false) {
                    System.out.println("1");
                    falseBooks++;
                }
            }

            if (falseBooks == (bookList.size() - bookList.size()) && trueAvailableFalseUnavailableBook == false) {
                System.out.println("2");
                SC.messageFieldCenterWithBlankSpace(messageIfThereIsNoAvailableBook);
            } else if (falseBooks == bookList.size() && trueAvailableFalseUnavailableBook == true) {
                System.out.println("3");
                SC.messageFieldCenterWithBlankSpace(messageIfThereIsNoAvailableBook);
            }

            for (Book book : bookList) {
                if (book.isAvailability() == trueAvailableFalseUnavailableBook) {
                    System.out.println("4");
                    //System.out.println(showSimpleInformationOfBook(book));
                    //showAllBookInformation(book);
                    //showAvailableBookListWithRandomInformation(books, false, true, true, true, true, "Listan är tom tyvärr :(");
                    break;
                } else if (book.isAvailability() == trueAvailableFalseUnavailableBook) {
                    System.out.println("5");
                    //System.out.println(showSimpleInformationOfBook(book));
                    //showAllBookInformation(book);
                    showAvailableBookListWithRandomInformation(books, false, true, true, true, true, "Listan är tom tyvärr :(");
                    break;
                }
            }
            return;
        }
        SC.messageFieldCenterWithBlankSpace(messageIfThereIsNoAvailableBook);
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

    private void showAllBookInformation(Book book) {
        System.out.printf(showTitleOrNot(book, true));
        System.out.printf(showAuthorOrNot(book, true));
        System.out.printf(showInformationOrNot(book, true));
        System.out.printf(showAvailabilityOrNot(book, true));
        System.out.println();
        /*return String.format("%-15s %-20s %-40s %-50s\n",
                book.isAvailability(),
                book.getTitle(),
                book.getAuthor(),
                book.getInformation());*/
    }

    public void showBooksList(ArrayList<Book> listOfBooksToShow, String messageIfEmptyList) {
        if (listOfBooksToShow.size() > 0) {
            for (Book book : listOfBooksToShow) {
                showAllBookInformation(book);
            }
            return;
        }
        SC.messageFieldCenterWithBlankSpaceReturn(messageIfEmptyList);
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

    ////////////////////////////////////////////////////////////////

    public void sortByTitle(ArrayList<Book> bookListToSort) {
        Collections.sort(bookListToSort, new Sort.SortByTitle());
    }

    public void sortByAuthor(ArrayList<Book> bookListToSort) {
        Collections.sort(bookListToSort, new Sort.SortByAuthor());
    }

    ////////////////////////////////////////////////////////////////

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        books.add(book);
    }

}
