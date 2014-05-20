data-driven-tictactoe
=====================

A Tic Tac Toe engine that learns from past games.

This is a small project written over a weekend to demonstrate my Java coding style and design
approach. It's also an experiment to find out to what extent data analysis can really improve 
Tic Tac Toe results.

The program runs in two phases:

**Phase 1**: it plays many games against itself, where both players pick completely random moves.
During this phase it records the win rate for each move. For example, it might learn that on a
board with O in the middle and no other spaces occupied, if X moves in the Northwest corner, X
has a 67% chance of eventually winning the game.

**Phase 2**: it plays many games against itself, where O picks random moves but X uses the data
collected during Phase 1 to inform its moves. X will always pick the move that has the highest
win rate for that given board configuration. It does not learn during this phase.

After both phases are finished, it prints out the win rate for X and O for both the learning
phase (where each player should win approximately the same amount of time) and the win rate after
the competition phase (where X should win many more than O, and there should be many fewer ties).


notes
-----

* The project is Mavenized, even though I didn't end up using any dependencies or other Maven features
* I haven't included any unit tests, although if this were production code I would have. 


Chris Cowell-Shah (cwcowellshah@gmail.com)
