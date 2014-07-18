import breeze.linalg._
import breeze.stats.distributions._
import Agent._

object selectionFuncs {       

  def fitnessProportionateSampling[E <: Agent](pop:Array[E]):E = {
    val fitArray = pop map {x => x.fitness}
    val fitArrayProp = fitArray map {x => x/fitArray.sum}
    val params = DenseVector(fitArrayProp)

    // Define a multinomial distribution. 
    // Probability of selecting an agent is proportionate to its fitness.
    val mult = new Multinomial(params)
    val index = mult.draw()
    val agent:E = pop(index)
    return agent
  }

  def randomSampling[E <: Agent](pop:Array[E]):E = {
    val parameters:Array[Double] = Array.fill[Double](pop.length)(1.0/pop.length.toDouble)
    val params = DenseVector(parameters)
    val mult = new Multinomial(params)
    val index = mult.draw()
    val agent:E = pop(index)
    return agent          
  }

}