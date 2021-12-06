package me.fineasgavre.flcd.lab5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Pattern;

// recursive descendant

public class Grammar {
    private List<String> terminals = new ArrayList<>();
    private List<String> nonTerminals = new ArrayList<>();
    private final Map<List<String>, List<List<String>>> productions = new LinkedHashMap<>();
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
            var splitAllProductions = Arrays.asList(splitProductions.get(0).split(Pattern.quote(" ")));
            var splitStates = splitProductions.get(1).split("\\|");

            var allStates = new ArrayList<List<String>>();
            for (var state : splitStates) {
                allStates.add(Arrays.asList(state.split(Pattern.quote(" "))));
            }

            var model = Map.entry(splitAllProductions, allStates);
            if (productions.containsKey(model.getKey())) {
                this.productions.get(model.getKey()).addAll(model.getValue());
            } else {
                this.productions.put(model.getKey(), model.getValue());
            }
        }

        startingSymbol = scanner.nextLine();
        scanner.close();

        System.out.println(validate() ? "Validation passed!" : "Validation did not pass :(");
    }

    public boolean validate() {
        if (!nonTerminals.contains(startingSymbol)) {
            return false;
        }

        for (var key : productions.keySet()) {
            for (var pKey : key) {
                if (!nonTerminals.contains(pKey)) {
                    return false;
                }
            }
            for (var move : productions.get(key)) {
                for (var list : move) {
                    if (!nonTerminals.contains(list) && !terminals.contains(list) && !list.equals("E")) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public List<List<String>> productionForNonTerminal(String nonTerminal) {
        if (nonTerminals.contains(nonTerminal)) {
            throw new RuntimeException("Not a non-terminal!");
        }

        for (var production : productions.entrySet()) {
            var productionList = production.getKey();

            if (productionList.contains(nonTerminal)) {
                return productions.get(productionList);
            }
        }

        return null;
    }

    public boolean isContextFreeGrammar() {
        var keys = productions.keySet();
        var checkStart = false;

        for (var key : keys) {
            if (key.contains(startingSymbol)) {
                checkStart = true;
                break;
            }
        }

        if (!checkStart) {
            return false;
        }

        for (var key : keys) {
            if (key.size() > 1 || nonTerminals.contains(key.get(0))) {
                return false;
            }

            var rules = productions.get(key);
            for (var rule : rules) {
                for (var term : rule) {
                    if (terminals.contains(term) && !nonTerminals.contains(term) && !term.equals("E")) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public String getStartingSymbol() {
        return startingSymbol;
    }

    public List<String> getNonTerminals() {
        return nonTerminals;
    }

    public List<String> getTerminals() {
        return terminals;
    }

    public Map<List<String>, List<List<String>>> getProductions() {
        return productions;
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
