// our convention is that 0: cooperate, 1: defect

import scala.math._

object GenomeSizeError extends Exception

class Prisoner(gnome: Array[Boolean], memsize: Int) {

  // The prisoner's memory is of size n, but for the first n turns
  // the prisoner's memory is not full. So the genome contains n+1
  // subarrays concatenated, one for each memory fullness size 0-n.
  // So the genome is of size
  //
  //   sum from i=0 to n of 2^n = 2^(n+1) - 1.
  //
  // Our convention is to put the full-memory array first, then the
  // one-from-full, and so on.

  val genome:Array[Boolean] = gnome;
  val n:Int = memsize;
  // check to see that the genome's size corresponds with the memsize.
  if (genome.size != pow(2, n+1).toInt - 1) throw GenomeSizeError;

  var points = 0;
  
  // memory(i) contains the opponent's ith most recent move,
  // and -1 if there is no memory of that
  var memory:Array[Int] = Array.fill[Int](n)(-1);

  // Determines the prisoner's behavior by looking up the position
  // in the genome corresponding to the prisoner's current mem array
  def decision(): Boolean = {
	// k is the index of the first -1 in the memory, or n if there
	// is none. So when t<n, k = t. If t>n, k = n
	val k:Int = if (memory.indexOf(-1) == -1) n else memory.indexOf(-1);

	// offset is the index of the beginning of the subarray corresponding
	// to the current memory fullness.
	var offset:Int = 0
	for (i <- k+1 to n) {
	  offset = offset + pow(2, i).toInt;
	}

	// index is the place in the memory fullness subarray corresponding
	// to the particular memory fullness
	var index:Int = 0
	for (i <- 0 to k-1) {
	  index = index +  memory(i) * pow(2, i).toInt;
	}

	return genome(index + offset);
  }

  //Definte toInt for Booleans
  def boolToInt(b:Boolean):Int = {
    if (b) {
      return 1;
    }
    else {
      return 0;
    }
  }

  override def toString():String = {
    val genome_temp:Array[Int] = genome map {x => boolToInt(x)};
    val string:String = genome_temp.mkString("");
    return string;
  }
  

}
