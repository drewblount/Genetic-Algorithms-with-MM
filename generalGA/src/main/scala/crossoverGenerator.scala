import crossoverFuncs._
import Agent._

object CrossoverTypeError extends Exception

object crossoverGenerator {

  def crossoverGen[E <: Agent](crossoverType:String):(Typed[E#geneType], Typed[E#geneType], 
                                          Double) => (Typed[E#geneType], Typed[E#geneType]) = {

    var crossover = sliceFunc[E#geneType](_, _, _)					  

    if (crossoverType == "slice") {
      crossover = sliceFunc[E#geneType](_, _, _)
    }				  

    else if (crossoverType == "shuffle") {
      crossover = shuffleFunc[E#geneType](_, _, _)
    }

    else throw CrossoverTypeError

    return crossover
  }

}