[Github Link](https://github.com/FineasGavre/FLCD_Labs)
# Lab4 Documentation

## FiniteAutomaton
Contains 5 fields that correspond to:
* a list of states (states)
* a list of the alphabet (alphabet)
* a list of final states (finalStates)
* a list of transitions (transitions)
* the initial state (initialState)

### createFromFile(File file)
Creates a FiniteAutomaton from a given file.

### isDeterministic()
Checks if the current FiniteAutomaton is deterministic.

### isAccepted(String sequence)
Checks if the given sequence is accepted by the FiniteAutomaton.


## Transition
Contains 3 fields that correspond to:
* the source of the transition
* the route of the transition
* a list of destinations the route can take