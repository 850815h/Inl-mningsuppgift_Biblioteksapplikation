package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        new Program();

        /*try (Stream<Path> walk = Files.walk(Paths.get(".\\"))) {

            List<String> result = walk.map(x -> x.toString())
                    .filter(f -> f.contains(".txt"))
                    .collect(Collectors.toList());

            result.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
