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
    private List<String> q;
    private List<String> e;
    private List<String> f;
    private List<Step> s;
    private String q0;

    public FiniteAutomaton(List<String> q, List<String> e, List<String> f, List<Step> s, String q0) {
        this.q = q;
        this.e = e;
        this.f = f;
        this.s = s;
        this.q0 = q0;
    }

    public boolean checkIfValid() {
        if (!q.contains(q0)) {
            return false;
        }

        for (var state : f) {
            if (!q.contains(state)) {
                return false;
            }
        }

        for (var step : s) {
            if (!q.contains(step.getP1())) {
                return false;
            }

            if (!e.contains(step.getP2())) {
                return false;
            }

            for (var t : step.getTarget()) {
                if (!q.contains(t)) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isDefinite() {
        for (var step : s) {
            if (step.getTarget().size() > 1) {
                return false;
            }
        }

        return true;
    }

    public boolean isAccepted() {
        if (!isDefinite()) return false;

        var currentState = q0;
        return true;
    }

    @Override
    public String toString() {
        return "FiniteAutomaton{" +
                "q=" + q +
                ", e=" + e +
                ", f=" + f +
                ", s=" + s +
                ", q0='" + q0 + '\'' +
                '}';
    }

    public static FiniteAutomaton createFromFile(File file) throws IOException {
        var lines = Files.readAllLines(file.toPath());

        List<String> qList = null;
        List<String> eList = null;
        List<String> fList = null;
        List<Step> sList = new ArrayList<>();
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
                    var step = new Step(p1, p2);
                    step.addTarget(t);
                    sList.add(step);
                }
            }
        }

        return new FiniteAutomaton(qList, eList, fList, sList, q0);
    }
}
