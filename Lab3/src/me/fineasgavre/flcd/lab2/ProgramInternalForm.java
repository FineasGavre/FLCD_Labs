package me.fineasgavre.flcd.lab2;

import java.util.HashMap;
import java.util.Map;

public class ProgramInternalForm {
    private final HashMap<Integer, Integer> internalForm = new HashMap<>();

    public ProgramInternalForm() {}

    public void add(int code, int position) {
        internalForm.put(code, position);
    }

    public Map<Integer, Integer> getInternalForm() {
        return internalForm;
    }
}
