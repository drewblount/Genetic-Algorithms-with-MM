// The PDsim class is an object that runs a simulation.  
// Maybe there should be another program that generates results and summarizes them, maybe outputting a csv or something.
// TODO - toString method.  
//
// PDsim.scala
//

import breeze.stats.distributions._
import breeze.linalg._
import math._

class PDsim(n:Int, s:Int, iters:Int, pm:Double, pc:Double) {
  // Define the parameters of the simulation
  val numPrisoners:Int = n;
  val memSize:Int = s;
  val genomeSize:Int = pow(2, memSize + 1).toInt - 1;
  val numIterations:Int = iters;
  val pMutation:Double = pm;
  val PCrossover:Double = pc;

  val population = new Population(numPrisoners, memSize);
  
  // Initialize the population with random strategy genomes. 
  population.initialize();

  def mutate(p:Prisoner, pm:Double ):Unit = {
    // Mutates the genome of the Prisoner with probability 'pm'. 
    
    var bern = new Bernoullli(pm);

    // Loop through each bit in the genome and flip it with probability pm. 

    for (i <- 0 to (p.genome.size - 1)) {
      var flip = bern.draw();
      if (flip) {
        p.genome(i) = !(p.genome(i));
      }
    }
  }

  def samplePrisoner():Prisoner = {
    // Samples from the population of prisoners with uniform probability
    // So that we pick people to play games with eachother.  

    val parameters:Array[Double] = Array.fill[Double](numPrisoners)(1/(numPrisoners.toDouble));
    val params = DenseVector(parameters);
    val mult = new Multinomial(params);
    val index = mult.draw();
    val prisoner = population(index);
    return prisoner;
  }

  

}