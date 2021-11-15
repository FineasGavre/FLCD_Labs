package me.fineasgavre.flcd.lab5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Grammar grammar = new Grammar();

    public static void main(String[] args) {
        while (true) {
            displayOptions();

            var selection = scanner.nextInt();
            switch (selection) {
                case 1 -> loadGrammar();
                case 2 -> printGrammar();
                case 0 -> System.exit(0);
            }
        }
    }

    private static void displayOptions() {
        System.out.println("Please select an option:");
        System.out.println("1 - Load Grammar");
        System.out.println("2 - Print Grammar");
        System.out.println("0 - Exit");
        System.out.print("-> ");
    }

    private static void loadGrammar() {
        System.out.println("Loading Grammar from File...");

        var file = new File("G1.txt");
        try {
            grammar.loadGrammarFromFile(file);
        } catch (FileNotFoundException e) {
            System.out.println("Could not load Grammar from file. 404");
            e.printStackTrace();
        }
    }

    private static void printGrammar() {
        System.out.println(grammar);
    }
}
