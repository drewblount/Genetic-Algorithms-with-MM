// Creates reproGenerator that returns reproduction functions given params. 
// This is just a sketch. I'm still new to some of these concepts. 

def reproGenerator(selectionType:String, crossoverType:String,
		   crossProb:Double, 
		   mutProb:Double):Array[_ <: Agent] => Array[_ <: Agent = {
  
  // selectionGen
  // 
  // Generates a selection function based on selectionType. 
  // Selection function takes a population and returns an agent. 
  // 
  // TODO: write this function. 
  val selectionGen(selectionType:String):Array[_ <: Agent] => _ <: Agent;

  // crossoverGen
  // 
  //   generates crossover function based on crossoverType.
  //   crossover function takes two parents and returns two children. 
  // 
  //   TODO: write this function.  
  val crossoverGen(crossoverType:String):(_ <: Agent, _ <: Agent) => (_ <: Agent, _ <: Agent)

  def mutate(ag:_ <: Agent):_ <: Agent = {
    // Not sure what to do here because I'm reluctant to include dependency on breeze. 
    // As this code now stands it is not providing the correct return type. 
  } 		 

  // This is the function that will be returned.  
  val reproFunc(pop:Array[_ <: Agent]):Array[_ <: Agent] = {
    val numAgents = pop.length
    val fitArray = pop map {x => x.fitness}

    // Initialize new pop as an empty array. 
    val newPop = Array[_ <: Agent]()

    val select = selectGen(selectionType)
    val crossover = crossoverGen(crossoverType)
    while (newPop.length < numAgents) {
      val parent1 = select(pop)
      val parent2 = select(pop)
      val children = crossover(parent1, parent2)
      children.foreach((child:_ <:Agent) => mutate(child))
      newPop :+= children // Is this doable? Append tuple to Array? Probably not. 
    }
    
    // if numAgents is odd, newPop will overshoot.  
    if (newPop.length > numAgents) { // Discard one agent randomly. }

    return newPop
    
  }
  
  return reproFunc

}