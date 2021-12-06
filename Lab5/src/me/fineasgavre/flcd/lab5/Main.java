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
                case 2 -> loadGrammar2();
                case 3 -> printGrammar();
                case 4 -> checkIfContextFreeGrammar();
                case 5 -> testSequence();
                case 0 -> System.exit(0);
            }
        }
    }

    private static void displayOptions() {
        System.out.println("Please select an option:");
        System.out.println("1 - Load Grammar");
        System.out.println("2 - Load Grammar 2");
        System.out.println("3 - Print Grammar");
        System.out.println("4 - Check if Grammar is Context-Free");
        System.out.println("5 - Test Sequence");
        System.out.println("0 - Exit");
        System.out.print("-> ");
    }

    private static void loadGrammar() {
        System.out.println("Loading Grammar from File...");

        var file = new File("files/G1.txt");
        try {
            grammar.loadGrammarFromFile(file);
        } catch (FileNotFoundException e) {
            System.out.println("Could not load Grammar from file. 404");
            e.printStackTrace();
        }
    }

    private static void loadGrammar2() {
        System.out.println("Loading Grammar from File...");

        var file = new File("files/G2.txt");
        try {
            grammar.loadGrammarFromFile(file);
        } catch (FileNotFoundException e) {
            System.out.println("Could not load Grammar from file. 404");
            e.printStackTrace();
        }
    }

    private static void printGrammar() {
        var recursiveDescendent = new RecursiveDescendent(grammar, "0110");
        var rdAlgorithm = new RecursiveDescendentAlgorithm(recursiveDescendent);
        rdAlgorithm.run();
    }

    private static void checkIfContextFreeGrammar() {
        System.out.println(grammar.isContextFreeGrammar());
    }

    private static void testSequence() {
        System.out.println("Enter sequence:");
        scanner.nextLine();
        var sequence = scanner.nextLine();

        var recursiveDescendent = new RecursiveDescendent(grammar, sequence);
        var rdAlgorithm = new RecursiveDescendentAlgorithm(recursiveDescendent);
        rdAlgorithm.run();
    }
}
