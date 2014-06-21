
// Note that "_ <: Agent" means "something whose type is an extension of Agent"
// Is there a way to ensure that in each "_ <: Agent", the "_" is the same as the others?

abstract class Simulation(initPop: Array[_ <: Agent], 
						  fitFunc: Array[_ <: Agent] => Unit, 
						  repFunc: Array[_ <: Agent] => Array[_ <: Agent]) {

  var pop = initPop

  def iterate = {
	fitFunc(pop)
	pop = repFunc(pop)
  }

}

object testFuncs {
  
  def fit(pop: Array[_ <: Agent]):Unit =
	for (agent <- pop) agent.fitness = 5
  
  def repro(pop: Array[_ <: Agent]):Array[_ <: Agent] = pop

}

case class PrisSim(prisoners: Array[Prisoner]) extends Simulation(prisoners, testFuncs.fit, testFuncs.repro) {

}
