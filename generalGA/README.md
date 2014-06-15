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

