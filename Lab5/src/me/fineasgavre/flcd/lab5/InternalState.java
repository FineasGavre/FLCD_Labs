package me.fineasgavre.flcd.lab5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InternalState {
    public enum StateValues {
        Q,
        B,
        F,
        E
    }

    public StateValues state;
    public int position;
    public List<String> workingStack;
    public List<String> inputStack;

    public InternalState(String startingSymbol) {
        this.state = StateValues.Q;
        this.position = 1;
        this.workingStack = new ArrayList<>(Collections.singletonList("E"));
        this.inputStack = new ArrayList<>(Collections.singletonList(startingSymbol));
    }
}
