import crossoverFuncs._
import Agent._

object CrossoverTypeError extends Exception

object crossoverGenerator {

  def crossoverGen[E <: Agent](crossoverType:String):(E,E, 
                                          Double) => (E,E) = {

    var crossover = sliceFunc[E](_, _, _)					  

    if (crossoverType == "slice") {
      crossover = sliceFunc[E](_, _, _)
    }				  

    else if (crossoverType == "shuffle") {
      crossover = shuffleFunc[E](_, _, _)
    }

    else throw CrossoverTypeError

    return crossover
  }

}