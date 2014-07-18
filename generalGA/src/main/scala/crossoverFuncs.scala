import breeze.linalg._
import breeze.stats.distributions._
import Agent._

object crossoverFuncs {       
       
  def sliceFunc[E <: Agent](parentA: E, parentB: E,
      		   crossoverProb: Double):(E,E) = {

    val genomeSize:Int = parentA.genome.length		
    require (parentB.genome.length == genomeSize)
    import parentA.geneTag, parentB.geneTag
    // require(E#geneType == parentA.geneTag)

    // Initiallly, copy the new children as the parents. 
    val (childA, childB) = (parentA, parentB)

    // Randomly decide whether or not to crossover. 
    val bern = new Bernoulli(crossoverProb)
    val decision = bern.draw()


    if (decision) {
      // randomly decide the index over which to perform the crossover. 
      
      val unif = new Uniform(0, genomeSize - 1)      
      val index = unif.draw.round.toInt

      // If the chosen index is the last in the array, no crossover occurs. 
      if (index != genomeSize - 1) {
        // splitAt code suggested by '0__' on Stack Overflow. 
        val (aInit, aTail) = parentA.genome.splitAt(index)
	val (bInit, bTail) = parentB.genome.splitAt(index)
	val genomeA = Array.concat(aInit, bTail)
	val genomeB = Array.concat(bInit, aTail)
	val (childA, childB) = (parentA.copy(genomeA), parentB.copy(genomeB))
      }

    } 

    return (childA, childB)
  }     


  // TODO - think about how this func generalizes to other
  //        numbers of parents/children. 
  def shuffleFunc[E <: Agent](parentA: E, parentB: E,
                  crossoverProb: Double): (E, E) = {

    val genomeSize = parentA.genome.length		
    require (parentB.genome.length == genomeSize)
    import parentA.geneTag

    // Randomly decide whether or not to crossover.		  
    val bern = new Bernoulli(crossoverProb)
    val decision = bern.draw()

    // Initialize the children
    val childA:E = parentA
    val childB:E = parentB

    if (decision) {
      val indices = (0 until genomeSize).toArray
      // Figure out how to sample without replacement. 
      // There may also be a better way. 
    }

    return (childA, childB)
  }

}