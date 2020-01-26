package com.company;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class FileUtility {
    private static Scanner scanner = new Scanner(System.in);

    public static void saveObject(String filename, Object o, StandardOpenOption... option) {
        Path path = Paths.get(filename);
        try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(path, option))) {
            out.writeObject(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object loadObject(String filename) {
        Path path = Paths.get(filename);
        try (ObjectInputStream in = new ObjectInputStream(Files.newInputStream(path))) {
            return in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object readObject(String fileName) {
        ObjectInputStream objectinputstream = null;
        Object object = null;
        try {
            FileInputStream streamIn = new FileInputStream(fileName);
            objectinputstream = new ObjectInputStream(streamIn);
            object = objectinputstream.readObject();
            objectinputstream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

    public static void writeObject(Object object, String fileName) {
        ObjectOutputStream objectOutputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(fileName, false);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void fileMenu() {
        System.out.println(
                "Choose from menu\n" +
                        "[1] Load file \n" +
                        "[2] Load file \n" +
                        "[3] Show file(s) \n" +
                        "");
        /*do {
            String userInput = scanner.nextLine();
            switch (){

            }
        }while(true);*/
    }

    public static void writeAllLines(ArrayList<String> listOfStringToRead, String fileName) {
        List<String> lines = listOfStringToRead;

        try {
            Path path = Paths.get("./" + fileName);
            Files.write(path, lines, StandardCharsets.UTF_8);
        } catch (Exception e) {

            System.out.println("The list/file you're trying to read from doesn't exist... Please try again!");
        }
    }

    public static List<String> readAllLines(String fileName) {
        List<String> lines = null;

        if (Files.exists(Paths.get(fileName))) {

        }
        try {
            lines = Files.readAllLines(Paths.get(fileName));
        } catch (Exception e) {
            //e.printStackTrace();
            //System.out.println(e);
            System.out.println("The file doesn't exist, try with another file name!");
        }
        return lines;
    }
}
