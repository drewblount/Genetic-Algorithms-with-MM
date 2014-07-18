abstract class Agent  {
  type geneType
  val genome: Array[geneType]
  var fitness = 0.0
  implicit def geneTag: reflect.ClassTag[geneType]
  def copy(newGenome: Array[geneType]): Typed[geneType]
}

// object Agent { type U <: Agent }
object Agent { type Typed[A] = Agent { type geneType = A }}

case class intAgent(initGenome: Array[Int]) extends Agent {
  type geneType = Int
  val genome = initGenome
  def copy(newGenome: Array[Int]):Typed[Int] = new intAgent(newGenome:Array[Int])
  def geneTag = implicitly[reflect.ClassTag[Int]]
}

case class Prisoner(initGenome: Array[Boolean], memSize: Int) extends Agent {
  type geneType = Boolean
  val genome = initGenome
  def geneTag = implicitly[reflect.ClassTag[Boolean]]
  def copy(newGenome: Array[geneType]):Typed[Boolean] = new Prisoner(newGenome:Array[Boolean], memSize: Int) 
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
  


  
