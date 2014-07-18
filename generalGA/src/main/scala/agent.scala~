abstract class Agent  {
  type geneType
  val genome: Array[geneType]
  var fitness = 0.0
  // How do we specify whether fitness has been instantiated yet?
  // var fitDefined = false
  // 
  // JM - maybe we initialize at -1 like memory? 
  // JM - then we could double check and throw exceptions if it's still -1 
  //      after fitness assignment occurs.  

  // This ClassTag business was suggested by '0__' on Stack Overflow: 
  // see 'define function for extension of abstract class'
  implicit def geneTag: reflect.ClassTag[geneType]
  type Typed[A] = Agent { type geneType = A }

  def copy(newGenome: Array[geneType]): Typed[geneType]
  
}

// object Agent { type U <: Agent }
object Agent { 
  type Typed[A] = Agent { type geneType = A }
}

case class intAgent(initGenome: Array[Int]) extends Agent {
  type geneType = Int
  val genome = initGenome
  def copy(newGenome: Array[Int]):AgentT[Int] = new intAgent(newGenome:Array[Int])
  def geneTag = implicitly[reflect.ClassTag[Int]]

}

case class Prisoner(genome: Array[Boolean], memSize: Int) extends Agent {
  type geneType = Boolean
  def geneTag = implicitly[reflect.ClassTag[Boolean]]
  def copy(newGenome: Array[geneType]):AgentT[Boolean] = new Prisoner(newGenome:Array[Boolean], memSize: Int) 
  var resources = 0.0
  // Memory of opponent's past behavior for the last memSize rounds
  // -1: no memory. 0: opponent cooperated. 1: opponent defected
  var memory = Array.fill(memSize)(-1)


  // The genome should encode behavior for each possible memory state
  // which is of size 2^memsize + 2^(memsize - 1) [for when memsize - 1 games have been played]
  // + ... + 2 [for when only one game has been played] + 1 [no games played]
  // = sum from 0 to memsize of 2^n = 2^(memsize+1) - 1
  object genomeLengthException extends Exception
  import scala.math.pow
  if (genome.length != pow(2, memSize + 1) - 1) throw genomeLengthException

}
  


  