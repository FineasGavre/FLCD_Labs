[Github Link](https://github.com/FineasGavre/FLCD_Labs)
# Lab5/6 Documentation

## Grammar class
This class holds the grammar representation from a given input file.

### Grammar#loadGrammarFromFile(File)
Loads the grammar representation from a given file into the following internal fields of the class:
* Terminals
* Non-terminals
* Productions
* Starting Symbol

### Grammar#validate()
Validates if the loaded grammar is correct.

### Grammar#productionForNonTerminal(String)
Generates the productions for a given non-terminal as List of Production (String) List.

### Grammar#isContextFreeGrammar()
Returns a boolean value of the check if the loaded Grammar is Context Free.
