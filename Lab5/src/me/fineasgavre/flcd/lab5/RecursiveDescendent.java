package me.fineasgavre.flcd.lab5;

public class RecursiveDescendent {
    public InternalState internalState;
    public Grammar grammar;
    public String sequence;

    public RecursiveDescendent(Grammar grammar, String sequence) {
        this.grammar = grammar;
        this.sequence = sequence;
        this.internalState = new InternalState(grammar.getStartingSymbol());
    }

    public void expand() {
        if (grammar.getNonTerminals().contains(internalState.inputStack.get(0))) {
            var currentElement = internalState.inputStack.get(0);
            if (internalState.workingStack.get(0).equals("E")) {
                internalState.workingStack.remove("E");
            }

            internalState.workingStack.add(currentElement + "1");
            internalState.inputStack.remove(currentElement);

            var productions = grammar.getProductions();
            for (var p : productions.entrySet()) {
                if (p.getKey().contains(currentElement)) {
                    internalState.inputStack.addAll(p.getValue().get(0));
                }
            }
        }
    }

    public void advance() {
        var inputStackHead = internalState.inputStack.get(0);

        if (grammar.getTerminals().contains(inputStackHead) && inputStackHead.equals(Character.toString(sequence.charAt(internalState.position - 1)))) {
            internalState.position++;
            internalState.inputStack.remove(inputStackHead);
            internalState.workingStack.add(inputStackHead);
        } else {
            this.momentaryInsuccess();
        }
    }

    public void momentaryInsuccess() {
        var inputStackHead = "E";

        if (internalState.inputStack.size() > 0) {
            inputStackHead = internalState.inputStack.get(0);
        } else {
            internalState.state = InternalState.StateValues.B;
        }

        if (grammar.getTerminals().contains(inputStackHead) && !inputStackHead.equals(Character.toString(sequence.charAt(internalState.position - 1)))) {
            internalState.state = InternalState.StateValues.B;
        }
    }

    public void back() {
        var workingStackHead = internalState.workingStack.get(internalState.workingStack.size() - 1);

        if (grammar.getTerminals().contains(workingStackHead)) {
            internalState.position--;
            internalState.inputStack.add(workingStackHead);
            internalState.workingStack.remove(workingStackHead);
        }
    }

    public void anotherTry() {
        var workingStackHead = internalState.workingStack.get(internalState.workingStack.size() - 1);
        var stringValueOfHead = String.valueOf(workingStackHead.charAt(0));
        var intValueOfHead = Integer.parseInt(String.valueOf(workingStackHead.charAt(1))) + 1;

        if (grammar.getNonTerminals().contains(stringValueOfHead)) {
            var productions = grammar.getProductions();

            for (var p : productions.entrySet()) {
                if (p.getKey().contains(stringValueOfHead)) {
                    if (p.getValue().size() >= intValueOfHead) {
                        internalState.workingStack.remove(internalState.workingStack.size() - 1);
                        internalState.workingStack.add(stringValueOfHead + intValueOfHead);
                        internalState.inputStack.removeAll(p.getValue().get(intValueOfHead - 2));
                        internalState.inputStack.addAll(p.getValue().get(intValueOfHead - 1));
                        internalState.state = InternalState.StateValues.Q;
                    } else {
                        if (internalState.position == 1 && stringValueOfHead.equals(grammar.getStartingSymbol())) {
                            internalState.state = InternalState.StateValues.E;
                        } else {
                            internalState.inputStack.removeAll(p.getValue().get(intValueOfHead - 2));
                            internalState.inputStack.add(stringValueOfHead);
                            internalState.workingStack.remove(workingStackHead);
                        }
                    }
                }
            }
        }
    }

    public void success() {
        internalState.state = InternalState.StateValues.F;
    }
}
