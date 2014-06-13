// Adaptation of Population.scala from my "Simple-GA" repository to work with Prisoners rather than Chromosomes. 

import breeze.stats.distributions._
import math._

class Population(n:Int, m:Int) {
  val numPrisoners:Int = n;
  val memSize:Int = m;
  val genomeSize:Int = pow(2, memSize + 1).toInt -1;
  var prisoners:Array[Prisoner] = new Array[Prisoner](numPrisoners);

  // the method randPris will create a Prisoner with a random genome bitstring.
  def randPris():Prisoner = {
    // parameter p is 0.5 so that "1" and "0" occur with equal probability.
    val bern = new Bernoulli(0.5)
    val bstring = bern.sample(genomeSize).toArray;
    val pris = new Prisoner(bstring, memSize);
    return pris;
  }

  // Creates a new population of prisoners with random genome bitstrings.
  def initialize():Unit = {
    for (i <-0 to numPrisoners - 1) {
      val pris = randPris();
      prisoners(i) = pris;
    }
  }

  def avgFit():Double = {
    val fitSum:Double = (prisoners map {x => x.points}).sum
    return fitSum/numPrisoners;
  }

  def maxFit():Double = {
    return (prisoners map {x => x.points}).max
  }

}