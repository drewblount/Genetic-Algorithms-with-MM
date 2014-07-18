import Agent._
import reproGenerator._

// Note that "_ <: Agent" means "something whose type is an extension of Agent"
// Is there a way to ensure that in each "_ <: Agent", the "_" is the same as the others?
//
// JM - does this E business work? 

object simulation {

  abstract class Simulation[E <: Agent](initPop: Array[E], 
  	   	            fitFunc: Array[E] => Unit, 
		            repFunc: Array[E] => Array[E]) {

    var pop = initPop

    def iterate = {
      fitFunc(pop)
      pop = repFunc(pop)
    }

  }

  object testFuncs {
  
    def fit[E <: Agent](pop: Array[E]):Unit = for (agent <- pop) {agent.fitness = 5}
    
    // Define test parameters for repro
    val (selectionType:String, crossoverType:String) = ("fitnessProportionateSampling", "slice")
    val (crossProb:Double, mutProb:Double) = (0.5,0.1)

    val repro = reproGen[E](selectionType, crossoverType, crossProb, mutProb)

}

case class PrisSim(prisoners: Array[Prisoner]) extends Simulation[Prisoner](prisoners, testFuncs.fit, testFuncs.repro) {

}



}
