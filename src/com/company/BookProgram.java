package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BookProgram {
    private Scanner scanner = new Scanner(System.in);
    private ArrayList<Book> books = new ArrayList<>();

    public BookProgram() {
        addBooks();
        //System.out.println(searchByTitle("SKriv boktitel!", "Boken finns ej!", "Boken är utlånad tyvärr."));
        //System.out.println(searchByAuthor("SKriv boktitel!", "Boken finns ej!"));
        //showAllBookList();
        //showAvailableBookWithRandomInformation(true, false, false, true );
    }

    public Book searchByTitleOrAuthor(String welcomeMessage, String messageIfFail, String ifBookIsNotAvailable) {
        String tempMessage = messageIfFail;
        System.out.println(welcomeMessage);
        do {
            String userInput = scanner.nextLine();
            for (Book book : books) {
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

    public Book searchByTitle(String welcomeMessage, String messageIfFail, String ifBookIsNotAvailable) {
        String tempMessage = messageIfFail;
        System.out.println(welcomeMessage);
        do {
            String userInput = scanner.nextLine();
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
            String userInput = scanner.nextLine();
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

    private void addBooks() {
        books.add(new Book("aaa Ondskan", "Jan Guillou", "\"Pojken som behandlas illa av sina klasskamrater.\"", true));
        books.add(new Book("aaa Tomat, det är ingen grönsak!", "\"Patrik Grönkvist", "\"Tomat går att äta till allt!\"", true));
        books.add(new Book("Sportbilar Delux", "Hassan A", "\"Om Mercedes Benz och BMW, som tar över världen med storm.\"", true));
        books.add(new Book("Tysta Johan", "Erik Polako", "\"Erik känner sig otroligt smart jämfört med sina klasskamrater.\"", true));
        books.add(new Book("Nils och Hundarna", "Lars Isak", "\"Mannen som älskade sina två hundar över allt annat.\"", true));
        books.add(new Book("Skrattet", "Anna S", "\"Pojken som behandlas illa av sina klasskamrater\"", true));
        books.add(new Book("Shoo Kurnia", "Kurnia V", "\"Hur ska man hälsa på andra medarbetare på jobbet? Räcker det verkligen med bara ett hej?\"", true));
        books.add(new Book("Stugans svaga länk", "Christian Andersson", "\"Den dramatiska historien om stugan utan dörr.\"", true));
        books.add(new Book("Skolan som gick i konkurs", "Humanus YH", "\"Vad hände egentligen med eleverna som studerade OOPAi?.\"", true));
        books.add(new Book("How to become a code master", "Tobias S", "\"How to use Google and YouTube, to finish your studies.\"", true));
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
