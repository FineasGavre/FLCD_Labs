package me.fineasgavre.flcd.lab2;

public class Main {

    public static void main(String[] args) {
        var symbolTable = new SymbolTable(20);

        System.out.println(symbolTable.add("a"));
        System.out.println(symbolTable.add("b"));
        System.out.println(symbolTable.add("abc"));
        System.out.println(symbolTable.add("cba"));
        System.out.println(symbolTable.add("var"));

        System.out.println(symbolTable.search("a"));
        System.out.println(symbolTable.search("b"));
        System.out.println(symbolTable.search("abc"));
        System.out.println(symbolTable.search("var"));
        System.out.println(symbolTable.search("?"));
        System.out.println(symbolTable.search("abc"));
        System.out.println(symbolTable.search("cba"));
    }
}
