package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SC {
    private static Thread thread;
    public transient static Scanner scanner = new Scanner(System.in);
    private static final int NEW_LINE_LIMIT = 5;
    private static final int MAX_MESSAGE_FIELD_SPACE = 62;
    private static final int MAX_BLANK_LEFT_SPACE = 3;
    private static final int MAX_BLANK_RIGHT_SPACE = 3;

    public static void pressKeyToQuitMenu(String messageWhatToPressToQuitMenu) {
        System.out.println();
        SC.messageFieldCenterWithBlankSpace(messageWhatToPressToQuitMenu);
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
                    for (int i = 0; i < listToReturnFrom.size(); i++) {
                        if (listToReturnFrom.get(i).getTitle().toLowerCase().contains(userInput.toLowerCase()) ||
                                listToReturnFrom.get(i).getAuthor().toLowerCase().contains(userInput.toLowerCase())) {
                            sameSearchBooks.add(listToReturnFrom.get(i));
                        }
                    }
                    if(sameSearchBooks.size()>0){
                        msgIfFail = tempMsgRefineSearch;
                        Program.getBookProgram().showAvailableBookListWithRandomInformation(sameSearchBooks, false, true, true, false, false, "Listan är tom tyvärr :(");
                    }
                    if (sameSearchBooks.size() == 1) {
                        if( sameSearchBooks.get(0).isAvailability() == true ){
<<<<<<< Updated upstream
                        sameSearchBooks.get(0).setAvailability(false);
=======
                            sameSearchBooks.get(0).setAvailability(false);
>>>>>>> Stashed changes
                        } else {
                            sameSearchBooks.get(0).setAvailability(false);
                        }
                        return sameSearchBooks.get(0);
                    }
                    SC.messageFieldCenterWithBlankSpace( msgIfFail);
                } while (true);
            } while (true);
        }
        SC.messageFieldCenterWithBlankSpace(msgIfEmptyList);
        return null;
    }

<<<<<<< Updated upstream
=======
    public static void eraseBookList(ArrayList<Book> bookListToEmpty) {
        if (bookListToEmpty.size() > 0) {
            do {
                bookListToEmpty.remove(bookListToEmpty.get(0));
            } while (bookListToEmpty.size() > 0);
            return;
        }
    }

>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
                    SC.eraseBookList(sameSearchBooks);
=======
                    eraseList(sameSearchBooks);
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
    public static void eraseBookList(ArrayList<Book> bookListToEmpty) {
=======
    public static void eraseList(ArrayList<Book> bookListToEmpty) {
>>>>>>> Stashed changes
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
