## Iterated Prisoner's Dilemma

This repo simulates the iterated prisoner's dilemma. It is currently broken. 

### How to

Bring up the sbt console with:

```
sbt console
```

Then create a new simulation object that specifies the parameters:

```scala
val sim = new PDsim(100, 2, 20, 1000, 0.01, 0.9)
```

The first parameter denotes the number of agents ("prisoners") in the population.
The second parameter denotes the number of games that an agent remembers. 
The third parameter denotes the number of games that matched up agents play against one another in an iteration.
The fourth parameter denotes the number of iterations (or "generations") of agents.
The fifth parameter is the probability of mutation for each bit in the genome.
And finally, the sixth parameter is the probability of crossover. 

Then run 
```scala
sim.runSimulation()
```

The results will be (inelegantly) outputted in the console.  



