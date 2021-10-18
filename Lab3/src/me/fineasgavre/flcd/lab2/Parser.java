package me.fineasgavre.flcd.lab2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser {
    private final SymbolTable symbolTable = new SymbolTable(100);
    private final ProgramInternalForm programInternalForm = new ProgramInternalForm();
    private final LanguageSpecification languageSpecification = new LanguageSpecification();

    public Parser(String textFile) {
        parseFile(textFile);
    }

    private void parseFile(String textFile) {
        try {
            var sourceFile = new File(textFile);
            var scanner = new Scanner(sourceFile);

            while (scanner.hasNext()) {
                var token = scanner.next();

                if (languageSpecification.isReservedWord(token)) {
                    System.out.println(token + " is reserved word");
                }

                if (languageSpecification.isSeparator(token)) {
                    System.out.println(token + " is separator");
                }

                if (languageSpecification.isOperator(token)) {
                    System.out.println(token + " is operator");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
