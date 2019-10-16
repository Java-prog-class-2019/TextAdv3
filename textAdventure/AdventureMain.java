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
	
	
	/**LIST OF ROOMS**/
/*	roomList.put("poice_station",Room);
	roomList.put("butchery",Room);
	roomList.put("dirt_road",Room);
	roomList.put("corn_field",Room);
	roomList.put("house1",Room);
	roomList.put("house2",Room);
	roomList.put("house3",Room);
	roomList.put("house4",Room);
	roomList.put("bract_street",Room);
	roomList.put("bm_intersection",Room);
	roomList.put("main_street",Room);
	roomList.put("park",Room);
	roomList.put("bakery",Room);
	roomList.put("weapon_shop",Room);
	roomList.put("parole_house",Room);
	roomList.put("rostock_way",Room);
	roomList.put("rb_intersection",Room);
	roomList.put("rm_intersection",Room);
	roomList.put("main_tunnel",Room);
	roomList.put("bract_tunnel",Room);
*/
	
	//HashMap<String, Item> itemList = new HashMap<String,Item>(); //list of all item objects
	//ArrayList<String> inventory = new ArrayList<String>();
	String currentRoom;
	Player player;
	
	int turns = 0;

	public static void main(String[]args){
		new AdventureMain();
	}

	//ITEM THINGS
	public void itemKey() {
		boolean invExist = false;
		boolean roomExist = true;
		String description = "it's a key";
		int miscValue;
	}
	
	public void itemFood() {
		boolean invExist = false;
		boolean roomExist = true;
		String description = "it's food";
		int healthValue;
		int stack;
	}
	
	AdventureMain() {

		boolean playing = true;
		String command = "";

		setup(); //create all objects needed, including map; print intro. message
		
	//	lookAtRoom(true); //display information about the current room

		/***** MAIN GAME LOOP *****/
		while (playing) {

			command = getCommand();

			playing = parseCommand(command);

			//check to see if player has died (in whichever various ways the player can die)

			//check to see if the player has won the game
			
		}

		// does anything need to be done after the main game loop exits?

	}
	
	void readObject(String words[]) {
		System.out.println(description);
	}
	
	void setup() {
	//	Room.setupRooms(roomList);
		// ... more stuff ...
		currentRoom = "Police Station";
		System.out.println("You are at the " + currentRoom);
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
		
		String words[] = text.split(" ");
		
		//P3. remove all instances of "THE"
		ArrayList<String> wordlist = new ArrayList<String>(Arrays.asList(words));		//array list of words
		for(int i=0; i< wordlist.size(); i++) {
			if (wordlist.get(i).equals("the")) wordlist.remove(i--);			
		}

		//separate out into word1, word2, etc.
		String word1 = words[0];
		String word2 = words[1];
		//String word3 = words[2];

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
		//	moveToRoom(word1.charAt(0));
			break;
		case "i": case "inventory":
		//	showInventory();
			break;
		case "sleep":
		//	sleep();			
			break;	
		case "help":
		//	printHelp();
			break;
			
		/**** two word commands ****/		
		case "read":
			readObject(word2);
			break;
		case "eat":
		//	eatItem(word2);
			break;		
			
		/**** SPECIAL COMMANDS ****/
		// ...		

		default: 
			System.out.println("Sorry, I don't understand that command");
		}
		return true;
	}

	private void readObject(String word2) {
		// TODO Auto-generated method stub
		
	}	

	//tons of other methods go here ...		
	
}
