package com.company;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class SC {
    public transient static Scanner scanner = new Scanner(System.in);
    public static LocalDate localDate = LocalDate.now();
    private static final int MAX_MESSAGE_FIELD_SPACE = 76;
    private static final int MAX_BLANK_LEFT_SPACE = 3;

    public static void msgWelcomeSquare(String messageWhatToPressToQuitMenu) {
        System.out.println();
        messageFieldWholeWithoutBlankSpace();
        messageFieldCenterWithBlankSpace(messageWhatToPressToQuitMenu);
        messageFieldWholeWithoutBlankSpace();
        System.out.println();
    }

    public static void msgPressAnyKeyToQuit(String messageWhatToPressToQuitMenu) {
        System.out.println();
        messageFieldWholeWithoutBlankSpace();
        messageFieldCenterWithBlankSpace(messageWhatToPressToQuitMenu);
        messageFieldWholeWithoutBlankSpace();
        System.out.println();
        try {
            System.in.read();
        } catch (Exception e) {
        }
    }

    public static String userInput() {
        String userInput = scanner.nextLine();
        return userInput;
    }

    public static void messageFieldLeftWithBlankSpace(String messageToShow) {
        String startAndEndFrame = "¤";
        String blankSpace = " ";
        String emptyLeftSpace = "";
        String emptyRightSpace = "";
        int dev = (MAX_MESSAGE_FIELD_SPACE - MAX_BLANK_LEFT_SPACE - messageToShow.length() - (startAndEndFrame.length() * 2));

        if (messageToShow.length() % 2 == 1) {
            for (int i = 0; i < MAX_BLANK_LEFT_SPACE; i++) {
                emptyLeftSpace += blankSpace;
            }
            for (int i = 0; i < (dev); i++) {
                emptyRightSpace += blankSpace;
            }
        } else if (messageToShow.length() % 2 == 0) {
            for (int i = 0; i < MAX_BLANK_LEFT_SPACE; i++) {
                emptyLeftSpace += blankSpace;
            }
            for (int i = 0; i < dev; i++) {
                emptyRightSpace += blankSpace;
            }
        }

        String fullString = startAndEndFrame + emptyLeftSpace + messageToShow + emptyRightSpace + startAndEndFrame;
        System.out.println(fullString);
    }

    public static void messageFieldCenterWithBlankSpace(String messageToShow) {
        String startAndEndFrame = "¤";
        String blankSpace = " ";
        String emptyLeftSpace = "";
        String emptyRightSpace = "";
        int dev = (MAX_MESSAGE_FIELD_SPACE - messageToShow.length() - (startAndEndFrame.length() * 2)) / 2;

        if (messageToShow.length() % 2 == 1) {
            for (int i = 0; i < dev; i++) {
                emptyLeftSpace += blankSpace;
            }
            for (int i = 0; i < (dev + 1); i++) {
                emptyRightSpace += blankSpace;
            }
        } else if (messageToShow.length() % 2 == 0) {
            for (int i = 0; i < dev; i++) {
                emptyLeftSpace += blankSpace;
            }
            for (int i = 0; i < dev; i++) {
                emptyRightSpace += blankSpace;
            }
        }

        String fullString = startAndEndFrame + emptyLeftSpace + messageToShow + emptyRightSpace + startAndEndFrame;
        System.out.println(fullString);
    }

    public static String messageFieldCenterWithBlankSpaceReturn(String messageToShow) {
        String startAndEndFrame = "¤";
        String blankSpace = " ";
        String emptyLeftSpace = "";
        String emptyRightSpace = "";
        int dev = (MAX_MESSAGE_FIELD_SPACE - messageToShow.length() - (startAndEndFrame.length() * 2)) / 2;

        if (messageToShow.length() % 2 == 1) {
            for (int i = 0; i < dev; i++) {
                emptyLeftSpace += blankSpace;
            }
            for (int i = 0; i < (dev + 1); i++) {
                emptyRightSpace += blankSpace;
            }
        } else if (messageToShow.length() % 2 == 0) {
            for (int i = 0; i < dev; i++) {
                emptyLeftSpace += blankSpace;
            }
            for (int i = 0; i < dev; i++) {
                emptyRightSpace += blankSpace;
            }
        }

        String fullString = startAndEndFrame + emptyLeftSpace + messageToShow + emptyRightSpace + startAndEndFrame;
        return fullString;
    }

    public static void messageFieldCenterWithoutBlankSpace(String messageToShow) {
        String startAndEndFrame = "¤";
        String blankSpace = " ";
        String noBlankSpace = "¤";
        String emptyLeftSpace = "";
        String emptyRightSpace = "";
        int dev = (MAX_MESSAGE_FIELD_SPACE - messageToShow.length() - (blankSpace.length() * 2) - (startAndEndFrame.length() * 2)) / 2;

        if (messageToShow.length() % 2 == 1) {
            for (int i = 0; i < dev; i++) {
                emptyLeftSpace += noBlankSpace;
            }
            for (int i = 0; i < (dev + 1); i++) {
                emptyRightSpace += noBlankSpace;
            }
        } else if (messageToShow.length() % 2 == 0) {
            for (int i = 0; i < dev; i++) {
                emptyLeftSpace += noBlankSpace;
            }
            for (int i = 0; i < dev; i++) {
                emptyRightSpace += noBlankSpace;
            }
        }

        String fullString = startAndEndFrame + emptyLeftSpace + blankSpace + messageToShow + blankSpace + emptyRightSpace + startAndEndFrame;
        System.out.println(fullString);
    }

    public static void messageFieldWholeWithoutBlankSpace() {
        String noBlankSpace = "¤";
        String emptySpace = "";

        for (int i = 0; i < MAX_MESSAGE_FIELD_SPACE; i++) {
            emptySpace += noBlankSpace;
        }

        String fullString = emptySpace;
        System.out.println(fullString);
    }

    public static void notificationMsgForBorrowDays(ArrayList<Book> bookList) {
        if (bookList.size() > 0) {
            messageFieldWholeWithoutBlankSpace();
            System.out.println();
            for (Book book : bookList) {
                if (SC.amountOfBorrowDaysCounter(book) >= 7 && book.getIsDeletedFromLibrarianSystem() == false) {
                    SC.messageFieldCenterWithBlankSpace("Du har " + SC.amountOfBorrowDaysCounter(book) + " dagar kvar att lämna tillbaka ");
                    SC.messageFieldCenterWithBlankSpace("\"" + book.getTitle() + "\"");
                    System.out.println();
                } else if (SC.amountOfBorrowDaysCounter(book) <= 6 && SC.amountOfBorrowDaysCounter(book) >= 1 && book.getIsDeletedFromLibrarianSystem() == false) {
                    SC.messageFieldCenterWithBlankSpace("Notera att du har mindre än en vecka (" + SC.amountOfBorrowDaysCounter(book) + " dagar) kvar");
                    SC.messageFieldCenterWithBlankSpace("att lämna tillbaka \"" + book.getTitle() + "\"");
                    System.out.println();
                } else if (SC.amountOfBorrowDaysCounter(book) == 0 && book.getIsDeletedFromLibrarianSystem() == false) {
                    SC.messageFieldCenterWithBlankSpace("Du har inte lämnat in \"" + book.getTitle() + "\" i tid");
                    SC.messageFieldCenterWithBlankSpace("och blir därför skyldig att betala en avgift på 19,999kr!");
                    SC.messageFieldCenterWithBlankSpace("Betalar du inte in boten i tid, anmäls du vidare till");
                    SC.messageFieldCenterWithBlankSpace("Inkasso och Kronofogden. Länge leve Kungen!");
                    System.out.println();
                } else if (book.getIsDeletedFromLibrarianSystem() == true) {
                    SC.messageFieldCenterWithBlankSpace("Du behöver inte lämna in \"" + book.getTitle() + "\"");
                    SC.messageFieldCenterWithBlankSpace("Biblioteket har tagit bort boken från systemet och boken är nu din!");
                    SC.messageFieldCenterWithBlankSpace("Länge leve Bibblan!");
                    System.out.println();
                }
            }
            messageFieldWholeWithoutBlankSpace();
        }
    }

    public static long amountOfBorrowDaysCounter(Book book) {
        long differenceBetweenTwoDates = ChronoUnit.DAYS.between(localDate, book.getReturnDate());
        return differenceBetweenTwoDates;
    }

    public static Book returnBooksFromLibrary(ArrayList<Book> listToReturnFrom, String msgWelcome, String msgRefineSearch, String msgIfFail, String msgIfEmptyList) {
        System.out.println();
        String tempMsgRefineSearch = msgRefineSearch;
        String tempMsgIfFail = msgIfFail;
        ArrayList<Book> sameSearchBooks = new ArrayList<>();
        String userInput = "";
        if (listToReturnFrom.size() > 0) {
            SC.messageFieldCenterWithBlankSpace(msgWelcome);

            do {
                msgIfFail = tempMsgIfFail;
                SC.eraseBookList(sameSearchBooks);
                SC.messageFieldCenterWithBlankSpace("(Återgå till kundmenyn genom att när som helst trycka [9])");
                userInput = SC.scanner.nextLine();
                if (userInput.equals("9")) {
                    return null;
                }

                for (int i = 0; i < listToReturnFrom.size(); i++) {
                    if (listToReturnFrom.get(i).getTitle().toLowerCase().contains(userInput.toLowerCase()) ||
                            listToReturnFrom.get(i).getAuthor().toLowerCase().contains(userInput.toLowerCase())) {
                        sameSearchBooks.add(listToReturnFrom.get(i));
                    }
                }

                if (sameSearchBooks.size() > 1) {
                    msgIfFail = tempMsgRefineSearch;
                    Program.getBookProgram().showAvailableBookListWithRandomInformation(sameSearchBooks, false, true, true, false, true, "Listan är tom tyvärr :(");
                    System.out.println();
                }

                if (sameSearchBooks.size() == 1) {
                    SC.messageFieldCenterWithBlankSpace("Sökresultat: ");
                    SC.messageFieldCenterWithBlankSpace(sameSearchBooks.get(0).getTitle() + " av författaren " + sameSearchBooks.get(0).getAuthor());
                    return sameSearchBooks.get(0);
                }
                SC.messageFieldCenterWithBlankSpace(msgIfFail);
            } while (true);
        }
        SC.messageFieldCenterWithBlankSpace(msgIfEmptyList);
        return null;
    }

    public static void eraseBookList(ArrayList<Book> bookListToEmpty) {
        if (bookListToEmpty.size() > 0) {
            do {
                bookListToEmpty.remove(bookListToEmpty.get(0));
            } while (bookListToEmpty.size() > 0);
            return;
        }
    }

    public static void exitProgram() {
        System.exit(0);
    }
}
