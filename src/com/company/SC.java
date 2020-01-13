package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class SC {
    private static Thread thread;
    private static Scanner scanner = new Scanner(System.in);
    private static final int MAX_MESSAGE_FIELD_SPACE = 50;
    private static final int MAX_BLANK_LEFT_SPACE = 5;

    public static String enterString(){
        String inputString = scanner.nextLine();
        return inputString;
    }

    public static String enterStringWithMessage(String enterMessageToShow){
        System.out.println(enterMessageToShow);
        String inputString = scanner.nextLine();
        return inputString;
    }

    public static double enterDouble( String enterMessageToShow ){
        System.out.println(enterMessageToShow);
        double inputDouble = Double.parseDouble( scanner.nextLine() );
        return inputDouble;
    }

    public static int enterInt( String enterMessageToShow ){
        System.out.println(enterMessageToShow);
        int inputInt = Integer.parseInt( scanner.nextLine() );
        return inputInt;
    }

    public static int enterIntWithMessage(String enterMessageToShow ){
        System.out.println(enterMessageToShow);
        int inputInt = Integer.parseInt( scanner.nextLine() );
        return inputInt;
    }

    public static double convertStringToInt(String stringToConvert){
        double number = Double.parseDouble( stringToConvert );
        return number;
    }

    public static void messageFieldLeftListWithBlankSpace(ArrayList<String> messageToShow) {
        for( String str : messageToShow ){
            messageFieldLeftWithBlankSpace( str );
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
        //return fullString;
    }

    public static void messageFieldCenterWithoutBlankSpace(String messageToShow) {
        String startAndEndFrame = "¤";
        String blankSpace = " ";
        String noBlankSpace = "¤";
        String emptyLeftSpace = "";
        String emptyRightSpace = "";
        int dev = (MAX_MESSAGE_FIELD_SPACE - messageToShow.length() - (blankSpace.length() * 2 ) - (startAndEndFrame.length() * 2)) / 2;

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
