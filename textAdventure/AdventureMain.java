package textAdventure;

//TITLE: The Oakville Mystery//

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/* A skeleton program for a text adventure game */
/* some other parts, like rooms, will be explained in class */

public class AdventureMain {

	static int INVSIZE = 10; //size of inventory	

	//instance variables
	//ArrayList<Room> roomList = new ArrayList<Room>();
	HashMap<String,Room> roomList = new HashMap<String,Room>();
	
	
	HashMap<String, Item> itemList = new HashMap<String,Item>(); //list of all item objects
	String currentRoom;
	Player player;
	
	
	
	int turns = 0;

	public static void main(String[]args){
		new AdventureMain();
	}

	AdventureMain() {

		boolean playing = true;
		String command = "";

		setup(); //create all objects needed, including map; print intro. message
		
		lookAtRoom(true); //display information about the current room

		/***** MAIN GAME LOOP *****/
		while (playing) {

			command = getCommand();

			playing = parseCommand(command);
			//check to see if player has died (in whichever various ways the player can die)

			//check to see if the player has won the game
			
		}

		// does anything need to be done after the main game loop exits?

	}

	void setup() {
		Room.setupRooms(roomList);
		// ... more stuff ...
		//******Added*********
		System.out.println("This is, The Oakville Mystery.");
		System.out.println("\n\t A Town once known as peaceful and happy until one day people just disappear.\n\t"
		+ " As the best detective around, you’re asked to take on this case. Follow the clues\n\t"
		+ " around town to find the five missing people and catch the Kidnapper. The town is\n\t"
		+ " counting on you to restore the happy and calm reputation.\n\t"
		+ " Dont forget to eat along the way and keep your health up.");
		//starting room
		currentRoom = "police_station";
	}

	String getCommand() {
		Scanner sc = new Scanner(System.in);		
		String text = sc.nextLine();
		if (text.length() == 0) text = "qwerty"; //default command
		//sc.close();
		return text;
	}

	
	boolean parseCommand(String text) {

		/***** PREPROCESSING *****/
		//P1. 
		text = text.toLowerCase().trim();	//the complete string BEFORE parsing
		

		//handle situation where no words entered ...

		
		//P2. word replacement
		text = text.replaceAll(" into ", " in ");
		text = text.replaceAll(" rocks", " rock");
		text = text.replaceAll("pick up", "pickup");
		text = text.replaceAll("look at", "lookat");
		text = text.replaceAll("climb up", "climbup");
		text = text.replaceAll("show health", "health");
		
		String words[] = text.split(" ");
		
		//P3. remove all instances of "THE"
		ArrayList<String> wordlist = new ArrayList<String>(Arrays.asList(words));		//array list of words
		for(int i=0; i< wordlist.size(); i++) {
			if (wordlist.get(i).equals("the")) wordlist.remove(i--);			
		}

		//separate out into word1, word2, etc.
		String word1 = words[0];

		/***** MAIN PROCESSING *****/
		switch(word1) {
		
		/**** one word commands ****/
		case "quit":
			System.out.print("Do you really want to quit the game? ");
			String ans = getCommand().toUpperCase();
			if (ans.equals("YES") || ans.equals("Y")) {
				System.out.print("Thanks for playing. Bye.");
				return false;
			}			
		case "n": case "s": case "w": case "e": case "u": case "d":
		case "north": case "south": case "west": case "east": case "up": case "down":
			moveToRoom(word1.charAt(0));
			break;
		case "i": case "inventory":
			lookAtInventory(false);
			break;
		case "sleep":
		//	sleep();			
			break;	
		case "help":
		//	printHelp();
			break;
			
		/**** two word commands ****/		
		case "read":
			//readObject(word2);
			break;
		case "eat":
			eatItem(false);
			break;
		case "drink":
			drinkItem(false);
			break;
		case "health":
			lookAtHealth(false);
			break;
		case "call Sation":
			callStation(false);
			break;
		case "inspect knife":
			inspectKnife(false);
			break;
		case "inspect body":
			inspectBodies(false);
			break;
		case "inspect bood":
			inspectBlood(false);
			break;
		case "take":
			takeItem(false);
			break;
			
		/**** SPECIAL COMMANDS ****/
		// ...		

		default: 
			System.out.println("Sorry, I don't understand that command");
		}
		return true;
	}	

	//LOOKING AT	
	void lookAtRoom(boolean showDesc) {
		System.out.println("\n_.-._.-" + roomList.get(currentRoom).getTitle() + "-._.-._");
		System.out.println(roomList.get(currentRoom).getDesc());
	}
	void lookAtHealth(boolean showDesc) {
		System.out.println("\n_.-._. " + "Heath: " + player.health + " ._.-._");
	}
	void lookAtInventory(boolean showDesc) {
		System.out.println("\n_.-._.-" + itemList.get(showDesc));
		//System.out.println(itemList.get(showDesc).getDesc());
	}
	
	//TAKING
	void takeItem(boolean showDesc) {
		//How do I add an item to the inventory 
	}
	
	
	//MOVING
	void moveToRoom(char dir) {
		//do whateverroom moving stuff you do
		//then	
		if (currentRoom == "bakery" || currentRoom == "butchery"){
			player.health -= 2;
		}
		if (currentRoom == "b_storage") {
			System.out.println("5 dead and wrapped bodies are layed out accross the floor. "
					+ "\nYou shoulld check to see if the badies are the ones your looking for.");
		}
	}
	
	
	//EATING and DRINKING
	void eatItem(boolean showDesc) {
		//a.healthPoints + player.health = player.health;
	}
	void drinkItem(boolean showDesc) {
		//a.healthPoints + player.health = player.health;
	}
	
	
	//CALLING
	void callStation(boolean showDesc) {
		System.out.println("The station I working on it now, and wishes you good luck.");
	}
	
	
	//INSPECTING
	void inspectKnife(boolean showDesc) {
		if (currentRoom == "Deli") {
			System.out.println("This seems important. You've bagged the item for evidence.");
		}
	}
	void inspectBlood(boolean showDesc) {
		if (currentRoom == "Deli") {
			System.out.println("This could be the blood of the killer, You've taken a sample.\n"
					+ " You should call this in for futher inspection. ");
		}
	}
	void inspectBodies(boolean showDesc) {
		if (currentRoom == "b_storage") {
			System.out.println("The bodies match the people in your case. You found the missing people,\n"
					+ " but sadly they are dead. Look for more clues to find the killer.");
		}
	}
		
	
}
