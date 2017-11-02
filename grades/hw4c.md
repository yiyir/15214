hw4c Feedback
============


## Design of GUI and Special Tile Implementation (30/30)

#### Separation of GUI and Core (20/20) 
  * 20/20, Your implementation effectively separates the GUI from the Core. 

#### Extra Special Tile (10/10)
  * 10/10, You implemented the extra special tile without needing to modify the core logic of your game, a hallmark of an extensible design. Well done!

## Implementation of GUI (22/40)

#### GUI Basics (4/5)
  * 4/5, Your GUI contains most of the key pieces of information we would expect to see, but we identified the following minor issues:
    * -1, Did not specify cost of special tile or value of a letter tile when a player purchases it in the GUI.

#### Appropriate handling of UI events of board state changes (10/10)
  * 10/10, Your GUI correctly handled the various board state change events. Well done!

#### GUI Gameplay (0/15)
  * -15, Your game cannot be played:
    * Unable to place tiles onto the board. (Clicking buttons seems to have no responses)
    * Not able to pass on a turn. (clicking `pass` has no effects)

#### Build Automation using Travis and Gradle (5/5)
  * 5/5, Build automation seems to work fine and we were able to start your game using `gradle run`. (Though Travis-CI is based on your homework 2. Please uprate `settings.gradle` in the root of your repository.)

#### Documentation + Style (3/5)
  * -1, Avoid using magic numbers in your code. Declare variables (such as special tile costs, String names, and the max number of tiles a player's rack can contain) as `static final` constants at the top of the class. ([link](https://github.com/CMU-15-214/yiyir/blob/master/homework/4/src/main/java/edu/cmu/cs/cs214/hw4/gui/Main.java))

  * -1, Class name should start with a capital letter. ([link](https://github.com/CMU-15-214/yiyir/blob/master/homework/4/src/main/java/edu/cmu/cs/cs214/hw4/gui/startScrabble.java))

## Reflection on the design process (10/10)
  * 10/10, You submitted a design change discussion and updated your design rationale. Well done!


---

#### Total (52/80)

#### hw4a points Back: 8 * 0.75 = 6
  * +1, Updated System Sequence Diagram.
  * +3, Updated Interaction Diagram (challenge).
  * +4, The return type of `Words` should be some location + tiles representation. It's not clear how you extract words from a move.


Late days used: 5 (0 left)

---

#### Additional Notes

Graded by: Shuli Jiang (shulij@andrew.cmu.edu)

To view this file with formatting, visit the following page: https://github.com/CMU-15-214/yiyir/blob/master/grades/hw4c.md

