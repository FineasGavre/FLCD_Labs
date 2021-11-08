package me.fineasgavre.flcd.lab4;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static FiniteAutomaton finiteAutomaton;

    public static void main(String[] args) throws IOException {
        while (true) {
            displayMenu();

            var selection = scanner.nextInt();
            switch (selection) {
                case 1 -> readFromFile();
                case 2 -> displayFiniteAutomaton();
                case 3 -> displayStates();
                case 4 -> displayAlphabet();
                case 5 -> displayTransitions();
                case 6 -> displayFinalStates();
                case 7 -> checkIfFiniteAutomatonIsDefinite();
                case 8 -> checkIfFiniteAutomationSequenceIsAccepted();
                case 9 -> System.exit(0);
            }
        }
    }

    private static void displayMenu() {
        System.out.println();
        System.out.println("1. Read FA from file");
        System.out.println("2. Display FA");
        System.out.println("3. Display states");
        System.out.println("4. Display alphabet");
        System.out.println("5. Display transitions");
        System.out.println("6. Display final states");
        System.out.println("7. Check if FA is DFA");
        System.out.println("8. Check if sequence is accepted");
        System.out.println("9. Exit");
        System.out.print("-> ");
    }

    private static void readFromFile() throws IOException {
        var file = new File("./files/FA.in");
        finiteAutomaton = FiniteAutomaton.createFromFile(file);
    }

    private static void displayFiniteAutomaton() {
        System.out.println(finiteAutomaton);
    }

    private static void displayStates() {
        System.out.println(finiteAutomaton.getStates());
    }

    private static void displayAlphabet() {
        System.out.println(finiteAutomaton.getAlphabet());
    }

    private static void displayTransitions() {
        System.out.println(finiteAutomaton.getTransitions());
    }

    private static void displayFinalStates() {
        System.out.println(finiteAutomaton.getFinalStates());
    }

    private static void checkIfFiniteAutomatonIsDefinite() {
        System.out.println(finiteAutomaton.isDefinite());
    }

    private static void checkIfFiniteAutomationSequenceIsAccepted() {
        scanner.nextLine();
        var sequence = scanner.nextLine().trim();

        System.out.println("Sequence is " + sequence);
        System.out.println(finiteAutomaton.isAccepted(sequence));
    }
}
