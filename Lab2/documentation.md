[Github Link](https://github.com/FineasGavre/FLCD_Labs)
# Lab2 Documentation

## SymbolTable class
The symbol table class is backed by a hashtable, 
it uses linear probing as a conflict resolution method,
and the hash function takes the ASCII value sum of the key
and applies modulo by the capacity of the table.

### Add
Input: String key  
Output: Boolean

Adds a given key to the Symbol Table.  
Return true if added, false otherwise.

### Search
Input: String key  
Output: Int

Searches a given key in the Symbol Table.  
Returns the hashValue / index of the given key, or -1 if the key cannot be found.