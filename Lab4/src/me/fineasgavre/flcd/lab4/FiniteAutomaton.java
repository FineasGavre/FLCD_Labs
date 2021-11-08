package me.fineasgavre.flcd.lab4;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FiniteAutomaton {
    private List<String> states;
    private List<String> alphabet;
    private List<String> finalStates;
    private List<Transition> transitions;
    private String initialState;

    public FiniteAutomaton(List<String> q, List<String> e, List<String> f, List<Transition> s, String q0) {
        this.states = q;
        this.alphabet = e;
        this.finalStates = f;
        this.transitions = s;
        this.initialState = q0;
    }

    public List<String> getStates() {
        return states;
    }

    public List<String> getAlphabet() {
        return alphabet;
    }

    public List<Transition> getTransitions() {
        return transitions;
    }

    public List<String> getFinalStates() {
        return finalStates;
    }

    public boolean checkIfValid() {
        if (!states.contains(initialState)) {
            return false;
        }

        for (var state : finalStates) {
            if (!states.contains(state)) {
                return false;
            }
        }

        for (var step : transitions) {
            if (!states.contains(step.getP1())) {
                return false;
            }

            if (!alphabet.contains(step.getP2())) {
                return false;
            }

            for (var t : step.getTarget()) {
                if (!states.contains(t)) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isDefinite() {
        for (var transition : transitions) {
            if (transition.getTarget().size() > 1) {
                return false;
            }
        }

        return true;
    }

    public boolean isAccepted(String sequence) {
        if (!isDefinite()) return false;

        if (sequence.length() == 0) {
            return finalStates.contains(initialState);
        }

        var currentState = initialState;
        for (int i = 0; i < sequence.length(); i++) {
            int finalI = i;
            String finalCurrentState = currentState;
            var transition = transitions.stream()
                    .filter(t -> t.getP1().equals(finalCurrentState) && t.getP2().equals(String.valueOf(sequence.charAt(finalI))))
                    .findFirst();

            if (transition.isPresent()) {
                currentState = transition.get().getTarget().get(0);
            } else {
                return false;
            }
        }


        return true;
    }

    @Override
    public String toString() {
        return "FiniteAutomaton{" +
                "q=" + states +
                ", e=" + alphabet +
                ", f=" + finalStates +
                ", s=" + transitions +
                ", q0='" + initialState + '\'' +
                '}';
    }

    public static FiniteAutomaton createFromFile(File file) throws IOException {
        var lines = Files.readAllLines(file.toPath());

        List<String> qList = null;
        List<String> eList = null;
        List<String> fList = null;
        List<Transition> sList = new ArrayList<>();
        String q0 = "";

        for (var line : lines) {
            if (line.startsWith("Q = ")) {
                var qLine = line.substring(4);
                qList = Arrays.stream(qLine.split(Pattern.quote(" "))).collect(Collectors.toList());
            } else if (line.startsWith("E = ")) {
                var eLine = line.substring(4);
                eList = Arrays.stream(eLine.split(Pattern.quote(" "))).collect(Collectors.toList());
            } else if (line.startsWith("Q0 = ")) {
                q0 = line.substring(5);
            } else if (line.startsWith("F = ")) {
                var fLine = line.substring(4);
                fList = Arrays.stream(fLine.split(Pattern.quote(" "))).collect(Collectors.toList());
            } else if (line.startsWith("(")) {
                var sLineParts = line.split(Pattern.quote(" -> "));

                var pParts = sLineParts[0].split(Pattern.quote(", "));
                var p1 = pParts[0].substring(1);
                var p2 = pParts[1].substring(0, pParts[1].length() - 1);

                var t = sLineParts[1];

                var stepOpt = sList.stream().filter(s -> s.getP1().equals(p1) && s.getP2().equals(p2)).findFirst();

                if (stepOpt.isPresent()) {
                    var step = stepOpt.get();
                    step.addTarget(t);
                } else {
                    var step = new Transition(p1, p2);
                    step.addTarget(t);
                    sList.add(step);
                }
            }
        }

        return new FiniteAutomaton(qList, eList, fList, sList, q0);
    }
}
