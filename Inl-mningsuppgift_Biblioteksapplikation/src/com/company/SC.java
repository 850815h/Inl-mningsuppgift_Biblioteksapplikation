package com.company;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SC {
    private static Thread thread;
    public transient static Scanner scanner = new Scanner(System.in);
    public static LocalDate localDate = LocalDate.now();
    private static final int NEW_LINE_LIMIT = 5;
    private static final int MAX_MESSAGE_FIELD_SPACE = 70;
    private static final int MAX_BLANK_LEFT_SPACE = 3;
    private static final int MAX_BLANK_RIGHT_SPACE = 3;

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

    public static String breakLineAfterAmountOfWords(String stringToCut) {
        List<String> lines = new ArrayList<>();
        String line = "";
        String[] words = stringToCut.split(" ");

        int rounds = NEW_LINE_LIMIT - 1;
        for (int i = 0; i < words.length; i++) {
            if (lines.size() == rounds) {
                lines.add(words[i] + "\n");
                rounds += NEW_LINE_LIMIT;
                continue;
            }
            lines.add(words[i] + " ");
        }

        for (String s : lines) {
            line += s;
        }
        return line;
    }

    public static String breakMessageFieldLeftRightWithBlankSpaceReturn(String stringToCut) {
        String startAndEndFrame = "¤";
        String blankSpace = " ";
        String emptyLeftSpace = "";
        String emptyRightSpace = "";
        int dev = (MAX_MESSAGE_FIELD_SPACE - MAX_BLANK_LEFT_SPACE - MAX_BLANK_RIGHT_SPACE - stringToCut.length() - (startAndEndFrame.length() * 2));

        if (stringToCut.length() % 2 == 1) {
            for (int i = 0; i < MAX_BLANK_LEFT_SPACE; i++) {
                emptyLeftSpace += blankSpace;
            }
            for (int i = 0; i < (dev); i++) {
                emptyRightSpace += blankSpace;
            }
        } else if (stringToCut.length() % 2 == 0) {
            for (int i = 0; i < MAX_BLANK_LEFT_SPACE; i++) {
                emptyLeftSpace += blankSpace;
            }
            for (int i = 0; i < dev; i++) {
                emptyRightSpace += blankSpace;
            }
        }

        List<String> lines = new ArrayList<>();
        String line = "";
        String[] words = stringToCut.split(" ");
        for (int j = 0; j < MAX_BLANK_RIGHT_SPACE; j++) {
            emptyLeftSpace += blankSpace;
        }

        int rounds = MAX_MESSAGE_FIELD_SPACE - 1; //10
        //lines.add( startAndEndFrame + MAX_BLANK_LEFT_SPACE ); //+ words[i]+startAndEndFrame);
        for (int i = 0; i < words.length; i++) {

            if (line.length() == rounds - rounds + words[i].length() + emptyLeftSpace.length() + startAndEndFrame.length()) {
                lines.add(words[i] + emptyLeftSpace + startAndEndFrame + "\n");
                rounds += NEW_LINE_LIMIT;
                continue;
            }

                /*if (lines.size() == rounds) {
                    lines.add(words[i] + emptyLeftSpace + startAndEndFrame + "\n");
                    rounds += NEW_LINE_LIMIT;
                    continue;
                }*/
            lines.add(words[i] + " ");
        }

        for (String s : lines) {
            line += s;
        }

        String fullString = startAndEndFrame + emptyLeftSpace + line + emptyRightSpace + startAndEndFrame;
        return line;
    }

    public static String enterString() {
        String inputString = scanner.nextLine();
        return inputString;
    }

    public static String enterStringWithMessage(String enterMessageToShow) {
        System.out.println(enterMessageToShow);
        String inputString = scanner.nextLine();
        return inputString;
    }

    public static double enterDouble(String enterMessageToShow) {
        System.out.println(enterMessageToShow);
        double inputDouble = Double.parseDouble(scanner.nextLine());
        return inputDouble;
    }

    public static int enterInt(String enterMessageToShow) {
        System.out.println(enterMessageToShow);
        int inputInt = Integer.parseInt(scanner.nextLine());
        return inputInt;
    }

    public static int enterIntWithMessage(String enterMessageToShow) {
        System.out.println(enterMessageToShow);
        int inputInt = Integer.parseInt(scanner.nextLine());
        return inputInt;
    }

    public static double convertStringToInt(String stringToConvert) {
        double number = Double.parseDouble(stringToConvert);
        return number;
    }

    public static void messageFieldLeftListWithBlankSpace(ArrayList<String> messageToShow) {
        for (String str : messageToShow) {
            messageFieldLeftWithBlankSpaceReturn(str);
        }
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
        //return fullString;
    }

    public static String messageFieldLeftWithBlankSpaceReturn(String messageToShow) {
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
        //System.out.println(fullString);
        return fullString;
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

        String fullString =  startAndEndFrame + emptyLeftSpace + messageToShow + emptyRightSpace + startAndEndFrame;
        System.out.println(fullString);
        //return fullString;
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
        //System.out.println(fullString);
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
        //return fullString;
    }

    public static void messageFieldWholeWithoutBlankSpace() {
        String noBlankSpace = "¤";
        String emptySpace = "";

        for (int i = 0; i < MAX_MESSAGE_FIELD_SPACE; i++) {
            emptySpace += noBlankSpace;
        }

        String fullString = emptySpace;
        System.out.println(fullString);
        //return fullString;
    }

    public static void removeOneOfTwoOfSame(ArrayList<Book> bookList) {
        if (bookList.size() > 0) {
            for (int i = 0; i < bookList.size(); i++) {
                for (int j = 0; j < bookList.size(); j++) {
                    if (i == j) {
                        continue;
                    }
                    if (bookList.get(i) == bookList.get(j)) {
                        bookList.remove(j);
                        break;
                    }
                }
            }
            return;
        }
    }

    public static void notificationMsgForBorrowDays(ArrayList<Book> bookList) {
        if (bookList.size() > 0) {
            messageFieldWholeWithoutBlankSpace();
            System.out.println();
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
                    SC.messageFieldCenterWithBlankSpace("Du har inte lämnat in \"" + book.getTitle() + "\" i tid");
                    SC.messageFieldCenterWithBlankSpace("och blir därför skyldig att betala en avgift på 19,999kr!");
                    SC.messageFieldCenterWithBlankSpace("Betalar du inte in boten i tid, anmäls du vidare till");
                    SC.messageFieldCenterWithBlankSpace("Inkasso och Kronofogden. Länge leve Kungen!");
                    System.out.println();
                }
            }
            messageFieldWholeWithoutBlankSpace();
        }
    }

    public static void notificationMsgForNotReturnedBookInTime(ArrayList<Book> bookList) {
        if (bookList.size() > 0) {
            for (Book book : bookList) {
                if (SC.amountOfBorrowDaysCounter(book) == 0) {
                    SC.messageFieldCenterWithBlankSpace("Du har inte lämnat in \"" + book.getTitle() + "\" i tid!");
                    SC.messageFieldCenterWithBlankSpace("Lämna tillbaka boken snarast möjligt för att slippa");
                    SC.messageFieldCenterWithBlankSpace("onödiga påminnelseavgifter och betalningsanmärkning");
                    System.out.println();
                }
            }
        }
    }

    public static long amountOfBorrowDaysCounter(Book book) {
        long differenceBetweenTwoDates = ChronoUnit.DAYS.between(localDate, book.getReturnDate());
        return differenceBetweenTwoDates;
    }

    public static Book returnBooksFromLibrary(ArrayList<Book> listToReturnFrom, String msgWelcome, String msgRefineSearch, String msgIfFail, String msgIfEmptyList) {
        String tempMsgRefineSearch = msgRefineSearch;
        String tempMsgIfFail = msgIfFail;
        ArrayList<Book> sameSearchBooks = new ArrayList<>();
        String userInput = "";
        if (listToReturnFrom.size() > 0) {
            SC.messageFieldCenterWithBlankSpace(msgWelcome);

            do {
                do {
                    msgIfFail = tempMsgIfFail;
                    SC.eraseBookList(sameSearchBooks);
                    userInput = SC.scanner.nextLine();
                    if( userInput.equals("9")){return null;}
                    for (int i = 0; i < listToReturnFrom.size(); i++) {
                        if (listToReturnFrom.get(i).getTitle().toLowerCase().contains(userInput.toLowerCase()) ||
                                listToReturnFrom.get(i).getAuthor().toLowerCase().contains(userInput.toLowerCase())) {
                            sameSearchBooks.add(listToReturnFrom.get(i));
                        }
                    }
                    if(sameSearchBooks.size()>1){
                        msgIfFail = tempMsgRefineSearch;
                        Program.getBookProgram().showAvailableBookListWithRandomInformation(sameSearchBooks, false, true, true, false, false, "Listan är tom tyvärr :(");
                    }
                    if (sameSearchBooks.size() == 1) {
                        /*if( sameSearchBooks.get(0).isAvailability() == true ){
                            sameSearchBooks.get(0).setAvailability(false);
                        } else {
                            sameSearchBooks.get(0).setAvailability(true);
                        }*/
                        SC.messageFieldCenterWithBlankSpace( "Sökresultat: " +sameSearchBooks.get(0).getTitle());
                        return sameSearchBooks.get(0);
                    }
                    SC.messageFieldCenterWithBlankSpace( msgIfFail);
                } while (true);
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

    public static Book advancedSearchInList(ArrayList<Book> bookListToRemoveFrom, String msgWelcome, String msgRefineSearch, String msgIfFail, String msgIfEmptyList) {
        String tempMsgRefineSearch = msgRefineSearch;
        String tempMsgIfFail = msgIfFail;
        ArrayList<Book> sameSearchBooks = new ArrayList<>();
        String userInput = "";
        //if(userInput.equals("9")){return;}
        if (bookListToRemoveFrom.size() > 0) {
            SC.messageFieldCenterWithBlankSpace(msgWelcome);

            do {
                do {
                    msgIfFail = tempMsgIfFail;
                    eraseList(sameSearchBooks);
                    userInput = SC.scanner.nextLine();
                    for (int i = 0; i < bookListToRemoveFrom.size(); i++) {
                        if (bookListToRemoveFrom.get(i).getTitle().toLowerCase().contains(userInput.toLowerCase()) ||
                                bookListToRemoveFrom.get(i).getAuthor().toLowerCase().contains(userInput.toLowerCase())) {
                            sameSearchBooks.add(bookListToRemoveFrom.get(i));
                        }
                    }
                    if(sameSearchBooks.size()>0){
                        msgIfFail = tempMsgRefineSearch;
                        Program.getBookProgram().showAvailableBookListWithRandomInformation(sameSearchBooks, false, true, true, false, false, "Listan är tom tyvärr :(");
                    }
                    if (sameSearchBooks.size() == 1) {
                        bookListToRemoveFrom.remove(sameSearchBooks.get(0));
                        return sameSearchBooks.get(0);
                    }
                    SC.messageFieldCenterWithBlankSpace( msgIfFail);
                } while (true);
            } while (true);
        }
        SC.messageFieldCenterWithBlankSpace(msgIfEmptyList);
        return null;
    }

    public static void eraseList(ArrayList<Book> bookListToEmpty) {
        if (bookListToEmpty.size() > 0) {
            do {
                bookListToEmpty.remove(bookListToEmpty.get(0));
            } while (bookListToEmpty.size() > 0);
            return;
        }
    }

    public static void insertNumberBetween(ArrayList<Integer> listToSearchIn, int minNumber, int maxNumber) {
        for (Integer i : listToSearchIn) {
            if (i < minNumber || i > maxNumber) {
                System.out.println("Try again, and please use only numbers between: " + minNumber + " and " + maxNumber + "!");
            }
        }
    }

    public static void delayTimer(double howManySecondsToDelay) {
        double wholeSeconds = howManySecondsToDelay * 1000;
        try {
            thread.sleep((long) wholeSeconds);
        } catch (Exception e) {
            System.out.println("TimeLeft");
        }
    }

    public static void exitProgram() {
        System.exit(0);
    }


}
