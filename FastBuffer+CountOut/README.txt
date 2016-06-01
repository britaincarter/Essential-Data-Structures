Text Editor Buffer : `FastBuffer.java` implements `Buffer.java`. Built 
it so it will handle a max of 10 million chars.

 The editor is intended for relatively slow computers, 
like an Arduino,
or Raspberry PI, 'insertLeft`,
`deleteRight`, `deleteLeft`, `moveRight`, and `moveLeft` are O(1), 
so the editor will feel fast and responsive to the user.

 

"Counting-Out" - Eeny, meeny, miny, moe
is a simple game. *n* players stand in a circle. In each round one of the players is "counted out". The game master begins at some point in the circle and then proceeds around the circle in clockwise direction. 
He skips the first *k-1* players and the *k*-th player has to leave the circle. He then skips the next *k-1* players etc. In each round the circle becomes smaller, until a single person remains and is the winner.

For instance, for *n=9* and *k=4* the first three rounds of the game look like this:

![The first three rounds of the game for *n=9* and *k=4*] 

In round 0, counting starts at player 0. Player 0, 1,
and 2 are skipped, player 3 is counted out. In round 1, counting starts at player 4. Player 4, 5, and  6 are skipped and player 7 is counted out, etc.

`play(9,4)` should return the list `[3, 7, 2, 8, 5, 4, 6, 1, 0]`, 
which is the complete sequence in which the players are counted out. 0 wins.