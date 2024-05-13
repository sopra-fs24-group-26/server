# Group 26 Contribution

## Week 1 , 25.3.24 + Easter Break , 1.4.24 - 7.4.24

Leon: https://github.com/sopra-fs24-group-26/server/issues/57,
https://github.com/sopra-fs24-group-26/server/issues/59
Create backend rest controller and session backend structure (service, repository)

Noah: https://github.com/sopra-fs24-group-26/server/issues/53,
https://github.com/sopra-fs24-group-26/client/issues/26,
https://github.com/sopra-fs24-group-26/client/issues/25 
Create initial frontend structure with PhaserJS

Patric: https://github.com/sopra-fs24-group-26/server/issues/58, https://github.com/sopra-fs24-group-26/server/issues/67,
https://github.com/sopra-fs24-group-26/client/issues/33,
https://github.com/sopra-fs24-group-26/server/issues/70
Create backend tiles structure, Create naming convention, create tiles definition

Paul: https://github.com/sopra-fs24-group-26/client/issues/2,
https://github.com/sopra-fs24-group-26/client/issues/52,
 Display lobby and all playernames currently in the lobby

Roxane: https://github.com/sopra-fs24-group-26/client/issues/31,
https://github.com/sopra-fs24-group-26/client/issues/45
I implemented a titlescreen, where you can press on a button to be able to get to the lobby. When you press on the button, a random name gets generated and a post request with the name as requestbody gets sent to the server. Then you receive the playerId from the server and it gets saved to the localstorage

## Week 2, 8.4.24 - 14.4.24

Leon: https://github.com/sopra-fs24-group-26/server/issues/77,
https://github.com/sopra-fs24-group-26/server/issues/80, 
Create endpoint for pinging, to receive all the information about players, tiles and session and write tests for controller.

Noah: https://github.com/sopra-fs24-group-26/client/issues/54,
https://github.com/sopra-fs24-group-26/client/issues/55,
Create seed randomness and shuffle system.

Patric: https://github.com/sopra-fs24-group-26/client/issues/47,
https://github.com/sopra-fs24-group-26/server/issues/129
Create json for predefined tiles (so they can be used for further logic). After small redesign, OrderIndex not yet implemented, needed to wait for other issues to be completed first

Paul: https://github.com/sopra-fs24-group-26/client/issues/51,
When players click on quit, share and start button, they will exit from lobby, copy the lobby link to clipboard, or start the game. This issue is linked to PR 37 in client repository
https://github.com/sopra-fs24-group-26/client/issues/53, When players are in lobby, they can change their playername by entering it in a HTML input field. This issue has low priority, after discussion this issue is dropped without merging

Roxane: https://github.com/sopra-fs24-group-26/client/issues/56,
https://github.com/sopra-fs24-group-26/client/issues/59
First off I implemented the generation of roles, depending on how many players there are in the lobby, which then gets shuffled (from Noah) with a seed. Then every player gets a role assigned according to their orderIndex. I also drew the titlescreen art and added it into the background to the titlescreen, such that it dynamically adjusts itself to the screen width of the client.

## Week 3, 15.4.24 - 21.4.24

Leon: https://github.com/sopra-fs24-group-26/server/issues/90,
https://github.com/sopra-fs24-group-26/server/issues/94
Add delete endpoint so the player can quit and generate the seed in the back end to distribute to all the players

Noah: https://github.com/sopra-fs24-group-26/server/issues/107,
https://github.com/sopra-fs24-group-26/client/issues/64,
https://github.com/sopra-fs24-group-26/client/issues/66,
https://github.com/sopra-fs24-group-26/client/issues/68,
https://github.com/sopra-fs24-group-26/client/issues/69,
https://github.com/sopra-fs24-group-26/client/issues/70,
https://github.com/sopra-fs24-group-26/client/issues/71,
https://github.com/sopra-fs24-group-26/client/issues/72,
https://github.com/sopra-fs24-group-26/client/issues/73,
Refactor frontend architecture, create placeholders, make game boilerplate.

Patric: https://github.com/sopra-fs24-group-26/server/issues/108,
https://github.com/sopra-fs24-group-26/client/issues/63,
https://github.com/sopra-fs24-group-26/client/issues/74,
https://github.com/sopra-fs24-group-26/client/issues/75,
https://github.com/sopra-fs24-group-26/client/issues/77,
Refactor backend together with Paul in partner programming. Add connection to tiles in json for future use when checking. Create json for preplacedTiles (tiles that are placed at start of game (goal, start)), Identifying through before implemented seed logic which tile at which point is in which players hand (based on what current turn is). This is then used to identify tiles in own hand which then can be visualized (done by Roxane).

Paul: https://github.com/sopra-fs24-group-26/server/issues/108
Refactor backend and devise test cases to match front end architecture
https://github.com/sopra-fs24-group-26/client/issues/41
Implement display-tiles-logic on game screen

Roxane: https://github.com/sopra-fs24-group-26/client/issues/5, https://github.com/sopra-fs24-group-26/client/issues/64,
In this issue I implemented the visual representation of having the tiles in hand. With the function getInHand (from Patric) I got all tiles that are currently in the players hand and needed to be represented. With a container I placed them in a way that the spacing between the cards get dynamically adjusted according to the width of the grey background rectangle. Additionally with Noah we refactored the frontend to have a better architecture

## Week 4, 22.4.24 - 28.4.24

Leon: https://github.com/sopra-fs24-group-26/client/issues/8, 
https://github.com/sopra-fs24-group-26/client/issues/6,
https://github.com/sopra-fs24-group-26/client/issues/7
Create an adjacency map so the ui-screen can efficiently check if the tile is placed according to rules. If this is the case the tile gets placed and is updated on the server. With the next ping the gameworlds can be updated thanks to pauls code.

Noah: https://github.com/sopra-fs24-group-26/client/issues/6,
https://github.com/sopra-fs24-group-26/client/issues/7,
Worked together with Leon to deliver this core part of the game.

Patric: https://github.com/sopra-fs24-group-26/client/issues/85,
After the visualizing of the tiles was implemented, I also added the thePlaced tiles into the scene.

Paul: https://github.com/sopra-fs24-group-26/server/issues/130,
https://github.com/sopra-fs24-group-26/server/issues/131
Extend test suite with test cases on Controller, Service and Repository
https://github.com/sopra-fs24-group-26/server/issues/127, 
Update UML diagram on backend architecture 

Roxane: https://github.com/sopra-fs24-group-26/client/issues/44, https://github.com/sopra-fs24-group-26/client/issues/9.
Here I implemented the visual representation of the tiles, when its the players turn. If it is not the players turn the tiles get a bit transparent and get a yellow hue. When it is the players turn the tiles look normal again. Additionally the player cannot play its tiles when it is not its turn. 

## Week 5, 29.4.24 - 5.4.24

Leon: https://github.com/sopra-fs24-group-26/client/issues/95,
https://github.com/sopra-fs24-group-26/client/issues/96,
Previously the adjacencymap only considered placed tiles and not goal tiles. The goal this week was that the adjacency map also detect goal tiles to be able to detect when the game ends. Also the whole adjacency map was refactored so it looks cleaner and is easier to read.

Paul: 
https://github.com/sopra-fs24-group-26/client/issues/10,
https://github.com/sopra-fs24-group-26/client/issues/12,
https://github.com/sopra-fs24-group-26/client/issues/13
A player can click on the trash icon to discard a tile in hand. The player will automatically receive a new tile and it’s next player’s turn.

Noah: https://github.com/sopra-fs24-group-26/client/issues/94,
https://github.com/sopra-fs24-group-26/client/issues/96, 
Fix deploy asset error and refactor tile placing.

Roxane: https://github.com/sopra-fs24-group-26/client/issues/21, https://github.com/sopra-fs24-group-26/client/issues/97, https://github.com/sopra-fs24-group-26/client/issues/99
Tiles have a transition to the background. New players can only join in the lobby, not when the game has already started and did with Patric the endgame logic.

Patric: https://github.com/sopra-fs24-group-26/client/issues/106
https://github.com/sopra-fs24-group-26/client/issues/17
https://github.com/sopra-fs24-group-26/client/issues/16
https://github.com/sopra-fs24-group-26/client/issues/20
Endgame logic with Roxane. Also, when reaching a coal vein, a coal path gets put into its place and the game continues.

## Week 6, 6.5.24 - 12.5.24

Leon: https://github.com/sopra-fs24-group-26/client/issues/21, https://github.com/sopra-fs24-group-26/client/issues/13, When a tile was dragged and discarded at the same time the game broke and the endlogic wasn’t right. I fixed these bugs in this pr: https://github.com/sopra-fs24-group-26/client/pull/107.  This pr also contained a refactor of the adjacency map. A nice recursive algorithm to update all the tiles connected to start instead of the hard coded version from before. This makes it easier to add the rockfall- action card (to destroy an already placed tile) in the future.

Paul: 
https://github.com/sopra-fs24-group-26/client/issues/13,
https://github.com/sopra-fs24-group-26/client/issues/108,
https://github.com/sopra-fs24-group-26/client/issues/109
Build in avatar api. Help fix a bug that a dragging tile might be discarded and thus break the endgame logic. An avatar is assigned to each player. Every player sees the same avatar for every other player.

Noah: https://github.com/sopra-fs24-group-26/client/issues/117,
https://github.com/sopra-fs24-group-26/client/issues/118,
https://github.com/sopra-fs24-group-26/client/issues/119,
https://github.com/sopra-fs24-group-26/client/issues/120
Refactor, create actions architecture with Patric and implement look action.

Roxane: https://github.com/sopra-fs24-group-26/client/issues/3, https://github.com/sopra-fs24-group-26/client/issues/121, https://github.com/sopra-fs24-group-26/client/issues/122, https://github.com/sopra-fs24-group-26/client/issues/123
In the game there is an overview of profiles with usernames. Each player sees their own roles and sees whose turn it is.

Patric:
https://github.com/sopra-fs24-group-26/client/issues/120
https://github.com/sopra-fs24-group-26/client/issues/124
Refactor, create actions architecture with Noah. Add ore shower made of particles to endscreen.