# Multi-Terminal BDDs

[![Build Status](https://travis-ci.org/ckaestne/vbdd.svg?branch=master)](https://travis-ci.org/ckaestne/vbdd)


Implementation of the `V` datatype to represent alternative values under symbolic conditions.
Follows the implementation of the `V` type used in  [Varex2](https://github.com/ckaestne/varex2/)
and [VarexC](https://github.com/chupanw/vbc)

Should allow for automatic garbage collection, since all caches use weak hashmaps. 

Does not depend on TypeChef's FeatureExprLib anymore. Instead `V<Boolean>`
is used to express propositional formulas.

A `V<T>` is a partition of a configuration space (exponential to the 
number of options/symbols used) such as each partition represents
a different concrete value of `T`. The implementation is 
efficient in that it represents each alternative value only once
(in contrast to choice trees, more similar to the traditional
map representation, see [Walkingshaw's Onward'14 paper](https://www.cs.cmu.edu/~ckaestne/pdf/onward14.pdf)).

All `V`'s have a config space and are undefined for values outside that
config space. An extra internal value EMPTY is used to model such partial
spaces. 

The implementation works just as BDDs do, except more terminals are 
possible. Practical operations use the `V.map` function.
Values are created with the `VFactory`.


See https://github.com/ckaestne/bdd for basic BDD implementation.

This repository also contains a Scala version, but that implements
a slightly different interface.

