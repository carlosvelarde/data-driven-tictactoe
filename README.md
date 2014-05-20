data-driven-tictactoe
=====================

Chris Cowell-Shah (cwcowellshah@gmail.com)

This is a Tic Tac Toe engine that learns from past games. It's a small project written over a weekend to 
demonstrate my Java coding style and design approach. It's also an experiment to find out to what extent 
data analysis can really improve Tic Tac Toe results.

The program runs in two phases:

* **Learning Phase**: it plays many games against itself, where both players pick completely random moves.
During this phase it records the win rate for each move. For example, it might learn that on a
board with O in the middle and no other spaces occupied, if X moves in the Northwest corner, X
has a 67% chance of eventually winning the game.

* **Competition Phase**: it plays many games against itself, where O picks random moves but X uses the data
collected during the Learning Phase to inform its moves. X will always pick the move that has the highest
win rate for a given board configuration. It doesn't learn during this phase.

After both phases are finished, it prints the win rate for X and O for both the Learning Phase (where
each player should win approximately the same amount of time) and the Competition Phase (where X should
win many more than O, and there should be many fewer ties).


results
-------

Wow, data analysis really works: after collecting data on 10 million games in the Learning Phase, 
X won 96 games out of 100, with only 1 tie in the Competition Phase. This took about four minutes to 
run on a medium-fast 2013-era PC.


notes
-----

* This code is optimized for **readability** and **maintainability**, not for size, performance,
  or any other metric.
* My design philosophy is heavily influenced by Robert C. Martin's book *Clean Code*. I'm not as 
  clean as I want to be yet (there's a lot in his book to absorb), but I'm getting closer with 
  each project. Probably the two most important concepts he preaches are:
    * Methods should operate on only one level of abstraction, and should call other methods whenever
      they need to move to a different level of abstraction.
    * Classes, methods, and variables should have names that actually describe what they do, even if
      they get a little long or unwieldy.
* My documentation philosophy:
    * Full Javadoc is not a good use of a developer's time and massively increases maintenance effort
      (to combat doc vs. code drift) down the road.
    * Code should be written with clear enough variable names, method names, and overall structure that
      **what** the programmer is doing, and **how** he is doing it, should be more or less obvious to
      any other experienced programmer.
    * Comments are crucial for explaining **why** the developer is doing what she does; they 
      explain intent.
    * Comments are crucial for explaining any gotchas, weirdnesses, or non-obvious algorithms.
* The project is Mavenized, even though I didn't end up using any dependencies or other Maven features.
* I haven't included any unit tests, although if this were production code I'd probably have more LOC 
  for unit tests than LOC for actual code.

