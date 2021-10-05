package guessme;

/**
 * A LinkedList-based implementation of the Guess-A-Number game.
 */
public class LinkedListGame {
  int guess;
  boolean gameOver;
  LLIntegerNode headPGuess; 
  LLIntegerNode tailPGuess; 
  LLIntegerNode headCanidates;
  boolean headP = false;
  int numGuesses;
  
  // TODO: declare data members as necessary


  // LinkedListGame constructor method
  public LinkedListGame() {
    reset();
  }
  /** Resets data members and game state so we can play again.
   * 
   */
  public void reset() {
    guess = 1000;
    numGuesses = 0;
    gameOver = false;
    headCanidates = null;
    headPGuess = null;
    headP = false;
    tailPGuess = null;
    for (int i = 9999; i >= 1000; i--) {
      LLIntegerNode newNode = new LLIntegerNode(i);
      newNode.setLink(headCanidates);
      headCanidates = newNode;
    }
  
  }

  /** Returns true if n is a prior guess; false otherwise.
   * 
   */
  public boolean isPriorGuess(int n) {
    LLIntegerNode cur = headPGuess;
    while (cur != null) {
      if (cur.getInfo()== n) {
        return true;
      }
      cur = cur.getLink();
    }
    return false;

  }

  /** Returns the number of guesses so far.
   * 
   */
  public int numGuesses() {
    
    return numGuesses;
  }

  /**
   * Returns the number of matches between integers a and b.
   * You can assume that both are 4-digits long (i.e. between 1000 and 9999).
   * The return value must be between 0 and 4.
   * 
   * <p>A match is the same digit at the same location. For example:
   *   1234 and 4321 have 0 match;
   *   1234 and 1114 have 2 matches (1 and 4);
   *   1000 and 9000 have 3 matches (three 0's).
   */
  public static int numMatches(int a, int b) {
    int matches = 0;
    for (int i = 0; i < 4; i++) {
      if (a % 10 == b % 10) {
        matches += 1;
        a /= 10;
        b /= 10;
      }
      else {
        a /= 10;
        b /= 10;
      }
    }
    return matches;    
  }

  /**
   * Returns true if the game is over; false otherwise.
   * The game is over if the number has been correctly guessed
   * or if no candidate is left.
   */
  public boolean isOver() {
 
    return gameOver;
  }

  /**
   * Returns the guess number and adds it to the list of prior guesses.
   * The insertion should occur at the end of the prior guesses list,
   * so that the order of the nodes follow the order of prior guesses.
   */
  public int getGuess() {
    LLIntegerNode cur = headCanidates;
    guess = cur.getInfo();
    numGuesses +=1;
 
    LLIntegerNode temp = null;
    if (!headP) {
      temp = new LLIntegerNode(guess);
      headPGuess= temp;
      tailPGuess = temp;
      headP = true;
    }
    else {
      temp = new LLIntegerNode(guess);
      tailPGuess.setLink(temp);
      tailPGuess = temp;
    }
      
    return guess;
  }   

  /**
   * Updates guess based on the number of matches of the previous guess.
   * If nmatches is 4, the previous guess is correct and the game is over.
   * Check project description for implementation details.
   * 
   * <p>Returns true if the update has no error; false if no candidate 
   * is left (indicating a state of error);
   */
  public boolean updateGuess(int nmatches) {
    LLIntegerNode cur = headCanidates;
    LLIntegerNode newTail = null;
    boolean newHeadExist = false;
    LLIntegerNode temp = null;
    headCanidates = null;
    while (cur!=null) {
      if (numMatches(cur.getInfo(),guess) == nmatches) {
        if (newHeadExist == false) {
          temp = new LLIntegerNode(cur.getInfo());
          headCanidates = temp;
          newTail = temp;
          newHeadExist = true;
        }
        else{
          temp = new LLIntegerNode(cur.getInfo());
          newTail.setLink(temp);
          newTail = temp;
        }
      }
      cur = cur.getLink();

    }

    if (nmatches == 4) {
      gameOver = true;
      isOver();
      return true;
    }
    if (headCanidates == null) {
      return false;
    }
    return true;
  }

  /**
   *  Returns the head of the prior guesses list.
   *  Returns null if there hasn't been any prior guess
   */
  
  public LLIntegerNode priorGuesses() {
    if (headPGuess == null) {
      return null;
    }
    else{
      return headPGuess;
    }
  }

  /**
   * Returns the list of prior guesses as a String. For example,
   * if the prior guesses are 1000, 2111, 3222, in that order,
   * the returned string should be "1000, 2111, 3222", in the same order,1
   * with every two numbers separated by a comma and space, except the
   * last number (which should not be followed by either comma or space).
   *
   * <p>Returns an empty string if here hasn't been any prior guess
   */
  public String priorGuessesString() {
    String guesses = "";
    LLIntegerNode cur = headPGuess;
    while (cur != null) {
      if (cur.getLink() == null) {
        guesses += cur.getInfo();
        break;
      }
      guesses += cur.getInfo() + ", ";
      cur = cur.getLink();
      
    }
    return guesses;
  }

}
