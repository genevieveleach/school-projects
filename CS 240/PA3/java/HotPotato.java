// Genevieve Leach
// CS 240, Section 1
// Programming Assignment 3
// 9 May 2016

/**
 * Copyright 2016: smanna@cpp.edu
 * CS 240 Spring 2016
 *
 * STUDENTS SHOULD COMPLETE THIS CODE.
 * You will upload this code to Blackboard.
 *
 * If you do not write enough comments, at least two points
 * will be deducted from your assignment. Also make sure you
 * follow the coding conventions.
 *
 * The main aim is to simulate a HotPotato game using
 * Queue. Please read through the details from the instructions.
 *
 */

public class HotPotato {

  // No need for fields.

  // Constructor
  public HotPotato() {
    // ¯\_(ツ)_/¯
  }

  /** Simulating HotPotato game.
   *  @param names Takes names of players
   *  @param num   number of times the potato is passed
   *  @return the name of the winner
   */
  public String play(Queue<String> names, int num) {
    if(names.size() == 1) { // Base case, determines winner.
      return names.peek();
    } else { // Actual gameplay
      int passes = num;
      while(passes>0) {
        names.enqueue(names.dequeue());
        passes--; 
      }
      names.dequeue(); // Removes the loser.
      return play(names, num); // Plays again.
    }
  }
}
