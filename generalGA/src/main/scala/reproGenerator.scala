import Agent._
import selectionGenerator._
import crossoverGenerator._
import breeze.linalg._
import breeze.stats.distributions._
import scala.reflect.ClassTag


object reproGenerator {

  // removeAgent removes the agent at index 'i' from array 'a'.  
  // I guess technically it returns a new array, though. 
  def removeAgent[E](a: Array[E], i: Int):Array[E] = {
    val slice1 = a.slice(0, i)
    val slice2 = a.slice(i + 1, a.length)
    return Array.concat(slice1, slice2)
  } 

  def reproGen[E <: Agent](selectionType:String, crossoverType:String,
	  	     crossProb:Double, 
		     mutProb:Double):Array[E] => Array[E] = {
  
    // selectionGen is a partially applied func. 
    val select = selectionGen[E](selectionType)

    // crossoverGen is a partially applied func too. 
    val crossover = crossoverGen[E](crossoverType)

    // TODO - put this function somewhere else, maybe. 
    // TODO - oops, just realize this assumes Boolean geneType.  
    // We'll have to get thinkin' on how to mutate other geneTypes.  
    def mutate[E](ag: E, mutProb: Double):E = {
      var bern = new Bernoulli(mutProb)

      // Loop through each bit in the agent's genome, and flip
      // with probability 'mutProb'.  
      // ag.genome.foreach(x => if (bern.draw()) !x )
      return ag
      
    }  		 

    // This is the function that will be returned.  
    def reproFunc(pop:Array[E]):Array[E] = {
      val numAgents = pop.length
      val fitArray = pop map {x => x.fitness}

      // Initialize new pop as an empty array. 
      var newPop:Array[E] = new Array[E](0)

      while (newPop.length < numAgents) {
        val parent1 = select(pop)
        val parent2 = select(pop)
        val children_preMut = crossover(parent1, parent2, crossProb:Double)
	val children = (mutate[E](children_preMut._1, mutProb), mutate[E](children_preMut._2, mutProb))

	// Put the newborn children in newPop
	// TODO - there is DEFINITELY a way more efficient way to do this. lol. 
	newPop :+= children._1
	newPop :+= children._2
      }
    
      // if numAgents is odd, newPop will overshoot.  
      if (newPop.length > numAgents) { // Discard one agent randomly. 
        val unif = new Uniform(0, newPop.length - 1)
        val index = unif.draw.round.toInt
	newPop = removeAgent[E](newPop, index)
      }

      return newPop
    }
    return reproFunc(_)
  }

}
