package me.fineasgavre.flcd.lab5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Pattern;

// recursive descendant

public class Grammar {
    private List<String> terminals = new ArrayList<>();
    private List<String> nonTerminals = new ArrayList<>();
    // De modificat in List<String>
    private Map<String, List<String>> productions = new HashMap<>();
    private String startingSymbol;

    public void loadGrammarFromFile(File file) throws FileNotFoundException {
        var scanner = new Scanner(file);

        scanner.nextLine(); // Non-Terminal Label
        var setOfNonTerminals = scanner.nextLine();
        this.nonTerminals = Arrays.asList(setOfNonTerminals.split(Pattern.quote(",")));

        scanner.nextLine(); // Terminal Label
        var setOfTerminals = scanner.nextLine();
        this.terminals = Arrays.asList(setOfTerminals.split(Pattern.quote(",")));

        scanner.nextLine(); // Productions Label

        while (true) {
            var production = scanner.nextLine();

            if (production.equals("Starting Symbol")) {
                break;
            }

            var splitProductions = Arrays.asList(production.split(Pattern.quote("->")));
            var splitStates = splitProductions.get(1).split("\\|");

            var model = Map.entry(splitProductions.get(0), Arrays.stream(splitStates).toList());
            if (productions.containsKey(model.getKey())) {
                this.productions.get(model.getKey()).addAll(model.getValue());
            } else {
                this.productions.put(model.getKey(), model.getValue());
            }
        }

        startingSymbol = scanner.nextLine();
        scanner.close();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Grammar.class.getSimpleName() + "[", "]")
                .add("terminals=" + terminals)
                .add("nonTerminals=" + nonTerminals)
                .add("productions=" + productions)
                .add("startingSymbol='" + startingSymbol + "'")
                .toString();
    }
}
