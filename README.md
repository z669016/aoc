# aoc
A small library with supporting classes for solving Advent of Code puzzles

## Package com.putoet.resources
Contains
- ResourceLines: to read a resource file into a ```List<String>``` or ```List<Integer>```
- CSV: to read a comma separated resource file into a ```List<List<String>>``` or a flat ```List<String>```, optionally applying a transformation mapper to convert the strings into ... integers perhaps?

## Package com.putoet.security
Contains
- MD5: a simple class with a method to generate a standard MD5 hash

## Package com.putoet.statistics
Contains
- Permutator: A generic class with methods to create permutations of combinations of ```List``` of elements