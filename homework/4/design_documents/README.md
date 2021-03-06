My-own-tile(“Skip”): The turn ends as usual, but after this tile is activated the player loses his/her next turn.


A summary of design changes that should be re-evaluated:

General changes within the core package:

A new interface Scrabble is added to the core;
The class GameSystem is renamed to ScrabbleImpl and it implements the Scrabble interface;
A new class SpecialTileFactory is added to the core.gameelements.specialtiles package;
A new interface GameChangeListener is added to the core;
A new class RemoveConsonants implementing the SpecialTile interface is added to the core.gameelements.specialtiles package;
 

Detailed changes within specific classes:

Class GameBoard: the method getScoreForMainWord(…) is removed, while a new method getScoreForNegativeWords(…) is created;

Classes Square, Boom, NegativePoints, RemoveConsonants, MyOwnTile, ReversePlayerOrder, LetterTile, Player: the toString() method is overridden;

Interface SpecialTile: the toString() method is overridden; the activateFunc

Class ScrabbleImpl: 
A new member field ‘store’ is created along with a getter method and is initialized within the constructor method;
There are changes for several methods() due to the addition of “notifyGameChanges()” methods in the class to notify the GUI’s “gameChangeListeners” of the changes;
Public methods removePlayer(…), addGameChangeListener(…) and removeGameChangeListener(…), a series of “notifyGameChanges()” methods are added.
Private methods isGameEnd(), getWinner() are changed; private method checkGameEnd() is added;

