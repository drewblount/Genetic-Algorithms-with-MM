// The PDsim class is an object that runs a simulation.  
// Maybe there should be another program that generates results and summarizes them, maybe outputting a csv or something.
// TODO - toString method.  
//
// PDsim.scala
//

import breeze.stats.distributions._
import breeze.linalg._
import math._

class PDsim(n:Int, s:Int, g:Int, iters:Int, pm:Double, pc:Double) {
  // Define the parameters of the simulation
  val numPrisoners:Int = n;
  val memSize:Int = s;
  val genomeSize:Int = pow(2, memSize + 1).toInt - 1;
  val numGames:Int = g;
  val numIterations:Int = iters;
  val pMutation:Double = pm;
  val pCrossover:Double = pc;

  val population = new Population(numPrisoners, memSize);
  val payoff:Array[Int] = Array(10, 2, 8, 5);
  
  // Initialize the population with random strategy genomes. 
  population.initialize();

  
  // Samples from the population of prisoners with uniform probability
  // So that we pick people to play games with eachother.  
  def samplePrisoner():Prisoner = {  
    val parameters:Array[Double] = Array.fill[Double](numPrisoners)(1.0/(numPrisoners.toDouble));
    val params = DenseVector(parameters);
    val mult = new Multinomial(params);
    val index = mult.draw();
    val prisoner = population.prisoners(index);
    return prisoner;
  }

  // Matches up everyone in the population. This will probably take some time to run. 
  def matchupEveryone():Unit = {
    for (p1 <- population.prisoners) {
      for (p2 <- population.prisoners) {
        val game:PDGame = new PDGame(p1, p2, numGames, payoff);
	game.play_n_games();
      }
    }
  }  

  // Defines genome mutation.
  // Every bit in a genome is 'flipped' with probability 'pm'. 
  def mutate(p:Prisoner, pm:Double ):Unit = {
    var bern = new Bernoulli(pm);

    // Loop through each bit in the genome and flip it with probability pm. 
    for (i <- 0 to (p.genome.size - 1)) {
      var flip = bern.draw();
      if (flip) {
        p.genome(i) = !(p.genome(i));
      }
    }
  }

  def sampleParent():Prisoner = {
    // sample from the population of prisoners with the probability of
    // selecting a prisoner being proportional to its fitness.
    // Note - here points and fitness are synonymous.  

    val fitArray = population.prisoners map {x => x.points};
    val fitArrayProp = fitArray map {x => x/fitArray.sum.toDouble};
    val params = DenseVector(fitArrayProp);

    // The params vector now carries the parameters for the appropriate 
    // multinomial random variable which we wish to sample. 

    val mult = new Multinomial(params);
    val index = mult.draw;
    val parent = population.prisoners(index);
    return parent;
  }

  def produceChild(parent1:Prisoner, parent2:Prisoner):Array[Prisoner] = {
    val bern = new Bernoulli(pCrossover);
    
    // Decision is either 0 or 1. 
    // If 1, then we cross over the children.
    // If 0, then the children are copies of the parents. 
    val decision = bern.draw();

    //Create a blank bool array to pass to the new prisoner children (lol, sucks)
    val placeholder:Array[Boolean] = new Array[Boolean](genomeSize);
    var child1:Prisoner = new Prisoner(placeholder, memSize);
    var child2:Prisoner = new Prisoner(placeholder, memSize);

    if (decision) {
      //randomly choose an index at which to cross over the genome. 
      val unif = new Uniform(0,genomeSize - 1);
      val index = unif.draw.round.toInt;

      // If the index chosen is the last in the array, then no crossover occurs.
      if (index == (genomeSize - 1)) {
        child1 = parent1;
	child2 = parent2;
      }

      //Otherwise, perform the crossover. 
      else {
        //Slice up the dna arrays of the parents.
	val p1s1:Array[Boolean] = parent1.genome.slice(0,index+1);
	val p2s1:Array[Boolean] = parent2.genome.slice(index + 1, genomeSize);
	val genome1:Array[Boolean] = Array.concat(p1s1,p2s1);

	val p1s2:Array[Boolean] = parent1.genome.slice(index +1, genomeSize);
	val p2s2:Array[Boolean] = parent2.genome.slice(0, index+1);
	val genome2:Array[Boolean] = Array.concat(p2s2,p1s2);

	child1 = new Prisoner(genome1, memSize);
	child2 = new Prisoner(genome2, memSize);
      } 
    }

    else {
      child1 = parent1;
      child2 = parent2;
    }

    mutate(child1, pMutation);
    mutate(child2, pMutation);
    val children:Array[Prisoner] = Array[Prisoner](child1, child2);
    return children;
  }



}