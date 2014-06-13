// This class runs the "prisoner's dilemma" game.
// It takes two Prisoners as input, computes their payoffs, 
// and updates each Prisoner's points and memory attributes. 

class PDGame(p1:Prisoner, p2:Prisoner, numRounds:Int, payoff:Array[Int]) {

  // Create a .toInt function for Booleans. 
  def boolToInt(b:Boolean):Int = {
    if (b) return 1 else return 0; 
  }


  // The payoff Array should be built in such a way that 
  //   - the 0th position indicates the payoff if both players cooperate.
  //   - the 1st position indicates the payoff to p1 if he cooperates and p2 defects.
  //   - the 2nd position indicates the payoff to p2 if he cooperates and p2 defects. 
  //   - the 3rd position indicates the payoff if both players defect.    

  def gameRound():Unit = {
    
    val p1move:Boolean = p1.decision();
    val p2move:Boolean = p2.decision();

    // Find each prisoner's payoff in the payoff array. 
    val p1payoff:Int = payoff(boolToInt(p2move) + 2 * boolToInt(p1move));
    val p2payoff:Int = payoff(boolToInt(p1move) + 2 * boolToInt(p2move));

    // Update points
    p1.points = p1.points + p1payoff;
    p2.points = p2.points + p2payoff;
    
    // Update each prisoner's memory of his opponent's past moves.
    val p1_newMem:Array[Int] = Array.concat(Array(boolToInt(p2move)), p1.memory.slice(0,p1.memory.length - 1));
    p1.memory = p1_newMem;

    val p2_newMem:Array[Int] = Array.concat(Array(boolToInt(p1move)), p2.memory.slice(0,p2.memory.length - 1));
    p2.memory = p2_newMem;
    
  }

  def play_n_games():Unit = {
    var i = 1;
    while (i < numRounds) {
      gameRound();
      i = i + 1;
    }
  }

}