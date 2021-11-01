package me.fineasgavre.flcd.lab4;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        var file = new File("./files/FA.in");
        var finiteAutomaton = FiniteAutomaton.createFromFile(file);
        System.out.println(finiteAutomaton);
        System.out.println(finiteAutomaton.checkIfValid());
    }
}
