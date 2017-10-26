hw4b Feedback
============

#### Implementation of Scrabble game (56/60)
## Board, player, and basic game mechanisms other than placing tiles (20/20)
  * 20/20, Your implementation seems to completely or almost completely implement the functionality related to the game’s board (squares, tiles, special tiles, drawing and exchanging tiles, purchasing special tiles, making moves, etc). Well done.

## Validating a move (5/7)
 * -2, You are missing the following case in your move validation:
   * All tiles must come from the current player’s rack (and no tile can be used more often than it occurs in the rack).

## Challenging a move (6/8) 
  * -2, You should update the player’s score on a successful challenge.

## Counting Points (10/10)
  * 10/10, Your implementation seems to completely or almost completely implement the functionality related to scoring a move in the game (adding points for tiles and words, applying multipliers and scoring-related special tiles, etc). Well done.

## Special Tiles (15/15)
  * 15/15, Your implementation seems to completely or almost completely implement the special tiles in the game (reverse order, boom, negative points, and own tile). Well done.

#### Program Design (25/25)
  *  25/25, The design aspects of your implementation (responsibility assignment, coupling, etc) are generally excellent. Well done!

## Coupling and responsibility assignment (Excellent)

## Extensibility (Excellent)

## Code reuse (Excellent)

#### Testing and Build Automation (18/25)

## Testing practice (10/10)
  * 10/10, Your testing practice meets our expectations. Well done!

## Test coverage (5/10)
  * -5, Your test coverage is very low. Large parts of your implementation, such as `GameBoard`, `Player` are entirely unteste.

## Build Automation (3/5)
  * 3/5, Build automation using gradle seems work fine. Travis-CI reports are from your previous homework but not homework 4. There are some Checkstyle issues with your build.

#### Documentation and Style (8/10)
  * 8/10, Your implementation mostly meets our expectations regarding documentation and style, but we have some smaller suggestions for improvement:

  * -1, Avoid using magic numbers in your code. Declare variables as `static final` constants at the top of the file. ([link](https://github.com/CMU-15-214/yiyir/blob/master/homework/4/src/main/java/edu/cmu/cs/cs214/hw4/core/gameelements/gameboard/GameBoard.java))
  
  * -1, Constants should be declared `static final`. e.g. `TWS`. ([link](https://github.com/CMU-15-214/yiyir/blob/master/homework/4/src/main/java/edu/cmu/cs/cs214/hw4/core/gameelements/gameboard/GameBoard.java))

---

#### Total (107/120)

Late days used: 4 (1 left)

---

#### Additional Notes

Graded by: Shuli Jiang (shulij@andrew.cmu.edu)

To view this file with formatting, visit the following page: https://github.com/CMU-15-214/yiyir/blob/master/grades/hw4b.md
