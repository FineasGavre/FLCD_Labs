package me.fineasgavre.flcd.lab5;

public class RecursiveDescendentAlgorithm {
    private final RecursiveDescendent recursiveDescendent;

    public RecursiveDescendentAlgorithm(RecursiveDescendent recursiveDescendent) {
        this.recursiveDescendent = recursiveDescendent;
    }

    public void run() {
        while (!recursiveDescendent.internalState.state.equals(InternalState.StateValues.F) && !recursiveDescendent.internalState.state.equals(InternalState.StateValues.E)) {
            if (recursiveDescendent.internalState.state.equals(InternalState.StateValues.Q)) {
                if (recursiveDescendent.internalState.position == recursiveDescendent.sequence.length() + 1 && recursiveDescendent.internalState.inputStack.isEmpty()) {
                    recursiveDescendent.success();
                } else {
                    var inputStackHead = "E";

                    if (recursiveDescendent.internalState.inputStack.size() > 0) {
                        inputStackHead = recursiveDescendent.internalState.inputStack.get(0);
                    }

                    if (recursiveDescendent.grammar.getNonTerminals().contains(inputStackHead)) {
                        recursiveDescendent.expand();
                        // CHANGE CONDITION TO MATCHING SEQ TO POSITION
                    } else if (recursiveDescendent.grammar.getTerminals().contains(inputStackHead)) {
                        recursiveDescendent.advance();
                    } else {
                        recursiveDescendent.momentaryInsuccess();
                    }
                }
            } else if (recursiveDescendent.internalState.state.equals(InternalState.StateValues.B)) {
                var workingStackHead = recursiveDescendent.internalState.workingStack.get(recursiveDescendent.internalState.workingStack.size() - 1);

                if (recursiveDescendent.grammar.getTerminals().contains(workingStackHead)) {
                    recursiveDescendent.back();
                } else {
                    recursiveDescendent.anotherTry();
                }
            }
        }

        if (recursiveDescendent.internalState.state.equals(InternalState.StateValues.E)) {
            System.out.println("Error encountered");
        } else {
            System.out.println("Sequence accepted");
            System.out.println("Working stack: " + recursiveDescendent.internalState.workingStack);
            System.out.println("Input stack: " + recursiveDescendent.internalState.inputStack);
        }
    }
}
