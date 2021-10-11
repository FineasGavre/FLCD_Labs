package me.fineasgavre.flcd.lab2;

public class SymbolTable {
    private final int capacity;
    private final String[] codification;

    public SymbolTable(int capacity) {
        this.capacity = capacity;
        this.codification = new String[capacity];
    }

    public boolean add(String key) {
        int hashValue = hashFunction(key);

        // Collision resolution: Linear Probing
        while (codification[hashValue] != null) {
            // Check if symbol is already contained
            if (codification[hashValue].equals(key)) {
                return false;
            }

            hashValue++;

            if (hashValue >= capacity) {
                hashValue = 0;
            }
        }

        codification[hashValue] = key;
        return true;
    }

    public int search(String key) {
        int hashValue = hashFunction(key);

        while (codification[hashValue] != null) {
            if (codification[hashValue].equals(key)) {
                return hashValue;
            }

            hashValue++;
        }

        return -1;
    }

    private int hashFunction(String key) {
        // Hash method: modulo of ASCII code sum
        int sum = 0;

        for (int i = 0; i < key.length(); i++) {
            sum += key.charAt(i);
        }

        return sum % capacity;
    }
}
