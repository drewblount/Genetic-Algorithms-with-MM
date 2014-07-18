README
========

A general-purpose modular GA framework that can be applied to disparate problems

Classes
-------------
  - Agent (abstract): has genome of type array(a), might have a fitness too? Extensions of this class might know their location, resource amount, etc, or have rules of competition with other agents (which alternately be encoded as part of a fitness function).
       - toString: by default printing its genome.
  - Simulation (abstract): initialized by specifying an initial population of agents, a fitness function, and a reproductive function. 
	   - Population :: Array/List(Agent)
       - fitness function :: (any number of input args) -> Unit (no output, simply updates the fitness scores of each agent). Each extension of simulation would likely have a particular fitness function.
	   - reproduction function :: takes an array/list of agents and produces another
	   - extensions of this class might have an environment, etc.

	- Methods:
	   - iterate: evaluate fitness w/ fitness func, then update population w/ reproduction func

  - We should have a scala object, say, ReproFunc, to generate reproduction functions. E.g. calling ReproFunc(type="eliteSelection", mutProb=0.1, crossoverProb=0.1, crossoverType="shuffle") would return a particular reproduction func.


Use
-------------

To make a new simulation environment (e.g. to evolve  prisoner's dilemma strategies), make a new scala class extending simulation, and an extension of file defining the extension of the Agent class  and the fitness function.

TODO
-------------


* Figure out how we want to generalize 'mutate' to genomeTypes other than Boolean. 
* There are some serious unsolved type issues with the reproGenerator code. I will try to document the issues well
  and get you up to speed on that code layout. 

reproGenerator
--------------

Layout: 
* selectionGenerator.scala uses the funcions from selectionFuncs.scala
* crossoverGenerator.scala uses the functions from crossoverFuncs.scala
* reproGenerator.scala uses selectionGenerator and crossoverGenerator to grab the appropriate
  functions and compose them into the eventual reproduction function.  
* We call reproGenerator from simulation.scala

Issues: 
* I attempted to make everything work with classes that extend an Agent.
  The syntax that I decided upon for most functions was as you had it, except with a parametrizing type E.
  For example, instead of  

```scala
def fitnessProportionateSampling(pop: Array[_ <: Agent]):_ <: Agent] = {// function definition}
```

We have 
```scala
def fitnessProportionateSampling[E <: Agent](pop:Array[E]):E = {
```

This seems to generally solve the problem you discussed of making sure that the extension of the abstract 
agent class is the same type throughout a function. 

But there are issues that arise in the code for sliceFunc when we use this type of type definition.
To see further detail, one would be advised to try to compile the code with: 
```
sbt console
```

I've tried to mitigate this issue with the *type Typed[A]* code in the abstract class definition of Agent.
But to no avail.  










