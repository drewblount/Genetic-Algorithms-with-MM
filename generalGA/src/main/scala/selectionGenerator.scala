import selectionFuncs._
import Agent._

object SelectionTypeError extends Exception

object selectionGenerator {

  // default is fitness proportionate. 

  def selectionGen[E <: Agent](selectionType:String):Array[E] => E = {
    var select = fitnessProportionateSampling[E](_)
    if (selectionType == "fitnessProportionateSampling") {
      select = fitnessProportionateSampling[E](_)
    }

    else if (selectionType == "randomSampling") {
      select = randomSampling[E](_)
    }

    else throw SelectionTypeError

    return select
  }

}