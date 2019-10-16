package textAdventure;

class Player {
	
	//make foodpoints counter. (health)
	//Make food counter for the player and keep the health up.
	//If the person goes into a main room/store, they lose 2 points of health.
	
	if (AdventureMain.currentRoom == AdventureMain.bakery || AdventureMain.currentRoom == AdventureMain.butchery){
		AdventureMain.health = AdventureMain.health -2;
 	}

}
