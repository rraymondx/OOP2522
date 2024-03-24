Modifications in LifeForm Class:

Name: Raymond Xie
Set 2B
Student Number: A01343016

Added an abstract void eat(Cell ____) method, which takes in a cell as its parameter, in LifeForm so that each class can eat, except plants, and it makes designing further implementations of it easier.

Added an abstract void breed() method, which takes no parameters, in LifeForm so that it makes breeding easier to implement for any further implementation. 

This will make all LifeForm inherit both eat() and breed().

Modifications in Plants Class:

Plants now extends both HerbEdible, and OmniEdible interfaces, as it satisfies an object-oriented design. This makes it so that plants don't have to change any core code to fulfill food for Omnivores.

Added behave() and eat(), but eat() doesn't do anything, and behave calls breed, and changed the boolean variable to false after the plants have taken a "turn"

I moved one if() conditions from a helper function directly into the breed methods to make the code base more readable and easier to understand

Modifications in Herbivore Class:

Herbivores now extends both CarniEdible and OmniEdible interface. I do that because it allows for the Carnivore and Omnivore class to eat Herbivores without telling it knowing that it is.

I moved two if() conditions from a helper function directly into the breed methods to make the code base more readable and easier to understand

