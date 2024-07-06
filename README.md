# aoc
A small library with supporting classes for solving Advent of Code puzzles

Version 1.7.0
JavaDoc was added and small code enhancements were made.
Upgrade of Mockito and JUnit to the latest released versions.

## Package com.putoet.resources
Contains
- ResourceLines: to read a resource file into a ```List<String>``` or ```List<T>```
- CSV: to read a comma separated resource file into a ```List<List<String>>``` or a flat ```List<String>```, optionally applying a transformation mapper to convert the strings into ... integers perhaps?

## Package com.putoet.security
Contains
- MD5: a simple class with a method to generate a standard MD5 hash

## Package com.putoet.math
Contains
- Factors: split a number into its factors, gcd (Greatest Common Denominator), lcm (Lowest Common Multiple)
- Josephus: Answer to the [Josephus problem](https://www.youtube.com/watch?v=uCsD3ZGzMgE)  

## Package com.putoet.grid
Contains 
- GridType: the basic Grid interface
- Grid: a basic grid class based on a two-dimensional character array
- GridSection: a class that maps to a section of a bigger underlying grid 
- GridUtils: utility classes for manipulating a two-dimensional character array
- Point: a class representing a point in a grid
- Point3D: a class representing a point in a three-dimensional grid
- Size: a class representing the size of a rectangle
- GridIterator: an interface to walk a grid
- SquareIterator: an implementation of GridIterator to walk a grid in a square pattern
- SpiralIterator: an implementation of GridIterator to walk a grid in a spiral pattern
