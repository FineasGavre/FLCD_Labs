package me.fineasgavre.flcd.lab2;

import java.util.*;
import java.util.regex.Pattern;

public class LanguageSpecification {
    private final Collection<String> separators = Collections.unmodifiableCollection(Arrays.asList("\"", "[", "]", "{", "}", ":", ";", " ", "\n"));
    private final Collection<String> operators = Collections.unmodifiableCollection(Arrays.asList("+", "-", "*", "/", "%", "=", "<=", "==", "=>", ">", "!="));
    private final Collection<String> reservedWords = Collections.unmodifiableCollection(Arrays.asList("let", "of", "type", "array", "char", "const", "execute", "if", "else", "then", "while", "enter", "exit", "input", "output", "be", "ignore", "true", "false", "endif", "endwhile"));

    private final ArrayList<String> codification = new ArrayList<>();

    public LanguageSpecification() {
        createCodification();
    }

    private void createCodification() {
        codification.addAll(separators);
        codification.addAll(operators);
        codification.addAll(reservedWords);
    }

    public ArrayList<String> getCodification() {
        return codification;
    }

    public boolean isSeparator(String separator) {
        return separators.contains(separator);
    }

    public boolean isOperator(String operator) {
        return operators.contains(operator);
    }

    public boolean isReservedWord(String word) {
        return reservedWords.contains(word.toLowerCase(Locale.ROOT));
    }

    public boolean isCharacter(String character) {
        var pattern = Pattern.compile("^'[a-zA-Z0-9]'$");
        return pattern.matcher(character).matches();
    }

    public boolean isInteger(String integer) {
        var pattern = Pattern.compile("[-]?\\\\d+");
        return pattern.matcher(integer).matches();
    }
}
