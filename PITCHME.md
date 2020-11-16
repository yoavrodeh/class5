# Interfaces


---
## Today
1. Interfaces.
1. Interfaces vs. subclasses.
1. A simple version of `Comparable`.
1. Function pointer - Strategy pattern.
1. A simple version of `Comparator`.
1. Decorator pattern.

---
## Resources
1. Deitel, chapter 10.
1. Java oracle docs - [Interfaces and Inheritance.](https://docs.oracle.com/javase/tutorial/java/IandI/index.html)
1. Wikipedia: 
  + [Strategy pattern](https://www.wikiwand.com/en/Strategy_pattern)
  + [Decorator Pattern](https://www.wikiwand.com/en/Decorator_pattern)


---
### Interfaces

+ An interface is a way to specify what we need from a class. 
+ In Java, interfaces are defined much like classes are, except
  + They have no instance variables.
  + Methods have no body, only a signature.
+ Classes can `implement` an interface.

*sounds like a completely abstract class, no?*



---
@code[java code-noblend](src/HelloInterface.java)

Methods in interfaces are `public abstract` by default.

@css[fragment](*Let's see how a class implements an interface:*)




---
@code[java code-max code-noblend](src/HelloWorld.java)


---
@code[java code-max code-noblend](src/HelloKitty.java)


---
@code[java code-noblend](src/MainForHello.java)

Interfaces can be used as types, and so enjoy the advantages of polymorphism.



---
### Implementing Interfaces vs Subclassing 
+ Use of the keyword `implements` and not `extends`. 
+ Methods are `public` by default.
+ Can implements many interfaces, but can only extend one class.

@box[](Interfaces are much lighter than classes, and are used to let other classes know what contracts a class satisfies.)




---
### Another example
@code[java code-noblend](src/Payable.java)

+ Anything that has the `amount()` method can be said to implement `Payable`. 
+ We can therefore write algorithms and structures that accept `Payable` objects, and use their `amount()` function. 



---
@code[java code-max code-noblend](src/Employee.java)



---
@code[java code-max code-noblend](src/Invoice.java)
@[1-11]
@[13-22]


---
@code[java code-max code-noblend](src/Expenses.java)
@[1-10]
@[12-18]
A class for summing expenses, and saving the most expensive one.


---
@code[java code-max code-noblend](src/MainForExpenses.java)
If both `Employee` and `Invoice` were subclasses of abstract class `Payable` this would work the same. But what if `Employee` is a subclass of `Person`?



---
### Comparable

A classic example for interface usage.
@code[java code-noblend](src/Comparable.java)
+ `compareTo` gives a way to compare the object to another. it returns a positive number if the object is larger, zero if they are equal, and a negative number if it is smaller.
+ It is part of the Java library, but a little differently. 
  + We will learn about it later in the course.


---
@code[java code-max code-noblend](src/Complex.java)
@[1-12](A simple class describing a complex number.)
@[14-28](We must use a cast. Later on we will learn how to avoid it.)


---
@code[java code-max code-noblend](src/MyArrays.java)
+ Our version of `Arrays.sort`.
+ All we know about `a` is that its element have a `compareTo(Object)` method.


---
@code[java code-max code-noblend](src/MainForSorting.java)



---
### Function pointer?

Java does not directly support sending a function pointer to a method. What can we do?
+ For example, we wish to count the number of strings in an array that satisfy some unknown property, e.g., their length is at most 3.
+ Start with this:
@code[java code-noblend](src/Predicate.java)


---
@code[java code-noblend](src/Counter.java)
This will return the number of Strings with `f()` that is true. 
It remains to pass it an `f` as we like.



---
@code[java code-noblend](src/ShortPredicate.java)
and then use it:

```java code-noblend
String[] a = {"The","times","they","are","a","changin"};
System.out.println(Counter.count(a, new ShortPredicate()));
```
which will print 3.


---
To calculate the number of strings in the array that start with a 't', we can define:
@code[java code-noblend](src/TPredicate.java)
and use 
```java code-noblend
Counter.count(a, new TPredicate());
```

---
What we just did is actually known as the
### Strategy Design Pattern
@quote[The strategy pattern is a behavioral design pattern that enables selecting an algorithm at runtime](Design Patterns)


---
@img[span-50](resources/strategy.png)

* The general function (Context), receives an object that is guaranteed to have a function it needs. 
* To call this function, we need to instantiate such an object.



---
### `Comparator`

+ Uses the strategy pattern to externally supply a way to compare elements.
+ Unlike `Comparable` where each element has a way of comparing itself to others.

@code[java code-noblend](src/DoubleComparator.java)

of course, can be defined for any type (not just double). 
+ The Java standard library has a slightly different version of it.



---
@code[java code-max code-noblend](src/DoubleArray.java)


---
@code[java code-max code-noblend](src/MaxSinus.java)




---
### Decorators

Let's start with an interface for an integer function:
@code[java code-max code-noblend](src/Function.java)

And here is a simple class implementing it:

@code[java code-max code-noblend](src/DoubleFunction.java)

@css[fragment](*Let's define a general transformation for functions:*)


---
@code[java code-max code-noblend](src/PositiveDecorator.java)

The decorator is initizalized with another `Function`, 
and also implements `Function` on its own.

Here is how we use it:


---
@code[java code-max code-noblend](src/MainForPositiveDecorator.java)

@css[fragment](*Another one:*)



---
@code[java code-max code-noblend](src/SumDecorator.java)
@[1-7](A standard decorator, holding another `Function` as its base)
@[9-20](implementing the method of the interface, using the base `Function`.)

---
@code[java code-max code-noblend](src/MainForDecorators.java)
@[1-6]
@[8-18]

@css[fragment](*Last one:*)




---
@code[java code-max code-noblend](src/MultDecorator.java)
@[1-11](These decorator classes are just classes, and so we can add whatever we want to them.)
@[13-17]


---
This is known as the
### Decorator Design Pattern
@quote[Dynamically adds responsibility to the interface by wrapping the original code](Design Patterns)



---
@img[span-65](resources/decorator.png)

How does our example fit here?


---
## Exercise 

Use the strategy design pattern to write a general function for summing $\sum_{i=0}^n f(i)$ for given $f$ and $n$.

Use it to calculate $\sum_{i=0}^{100} i^2$.





---
@code[java code-max code-noblend](src/Sumer.java)
where
@code[java code-max code-noblend](src/Function.java)



---
@code[java code-noblend](src/Squarer.java)

The answer is 328350, and
in fact, there is a formula for it:

$\sum_{i=1}^n i^2 = \frac{1}{6} n(n+1)(2n+1)$



---
### Another Question
What would you change if we wanted a class `Powerer` implementing `Function`, where `f(x) = x^y`, where `y` is given.
+ Where should `y` be given?
+ Change `main` so that it calculates $\sum_{i=0}^{10} i^3$


---
### Another
How would you change the interface `Function` so that a function is defined between `0` and some upper-bound? This upper-bound should be available through the interface. 
+ Use this change to alter `sum` so that it sums over the whole domain of the given function. 



