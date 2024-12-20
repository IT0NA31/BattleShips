Once run in Intellij, the BattleShips project will prompt the user, wether or not they want to play against a bot. 
This question can be answered with the inputs "y" or "n". 

After deciding on this, the setup phase will begin. 
In the setup phase, the user is prompted to place their ships. 
The position of the ship is based on the first input which is a coordinate. 
Example: "B1"

The type of the ship is equivalent to the size and can be input in several different ways.
Example: "cru"
         "submarine"

The rotation/direction of the ship can be input in four different ways. 
Example: "up"

If one player still has to set up ships, but no valid position for them left, the game is softlocked and needs to be restarted. 


Once all players have set up all of their ships, the game phase will begin. 
In the game phase players take turns shooting at each others playing fields. 
These shots can be input in the same way as the placement of the ships. 
Example: "I8"


Once one of the players has lost all of their ships, the game is over and the process will terminate.
