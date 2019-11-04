package textAdventure;

//TITLE: The Oakville Mystery//

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/*TO DO
 * Fix health
 * initial explanations (talk, call station, inspect)
 * grammatical errors (ex. when you can't take something)
 */


public class AdventureMain {

	static int INVSIZE = 10; //size of inventory	

	//instance variables
	HashMap<String,Room> roomList = new HashMap<String,Room>();
	
	ArrayList <String>inventoryList = new ArrayList <String>();
	
	//List of all objects
	HashMap<String, Item> itemList = new HashMap<String,Item>(); //list of all item objects
	String currentRoom;
	Player player;
	boolean win = false;
	boolean ending = false;
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
			if (player.health <= 0) {
				System.out.println("\n_.-._.YOU DIED._.-._\n\n"
						+ "You didn't keep your health up! eat and drink things to survive.");
				System.exit(0);
			}
			//Check to see if the player has won the game
			if (win) {
				System.out.println("You solved the case! Once again, Oakville returns to a state of peace.\n"
						+ "Thank you for playing! Created by: Casey, Alec, and Isaiah");
				System.exit(0);
			}
		}
	}

	//SETUP METHOD//
	void setup() {
		player = new Player();
		Room.setupRooms(roomList); //set up the rooms
		Item.setUpItems(itemList, roomList); //set up the items in the rooms
		
		//Title message
		System.out.println("This is The Oakville Mystery.");
		System.out.println("\n\t A Town once known to be peaceful and happy is claimed by calimity. A week ago,"
		+ "\n\t five people went missing from their homes. As the best detective around, you are\n\t"
		+ " asked to take on this case. Follow the clues around town to find the five missing\n\t"
		+ " people and catch the Kidnapper. The town is counting on you to restore the happy\n\t"
		+ " and calm reputation that Oakville was once known for. Don't forget to eat along\n\t"
		+ " the way and keep your health up. You can't work on an empty stomach!");
		//starting room
		currentRoom = "police_station";
	}

	//GET COMMAND METHOD//
	String getCommand() {
		Scanner sc = new Scanner(System.in);		
		String text = sc.nextLine();
		if (text.length() == 0) text = "qwerty"; //default command
		return text;
	}

	//PARSE COMMAND METHOD//
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
			if (wordlist.get(i).equals("to")) wordlist.remove(i--);
		}

		//separate out into word1, word2, etc.
		String word1 = words[0];
		String word2 = "";
		if (words.length > 1) word2 =  words[1];

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
		case "help":
			printHelp();
			break;
		case "talk":
			talking();
			break;
			
		/**** two word commands ****/		
		case "read":
			//readObject(word2);
			break;
		case "eat":
			switch(word2) {
				case"bagel":
					eatBagel(false);
					break;
				case"muffin":
					eatMuffin(false);
					break;
				case"meat":
					eatMeat(false);
					break;
				case"pig":
					eatPigs(false);
					break;
				case"corn":
					eatCorn(false);
					break;
				default: System.out.println("You can't eat that...");
			}
			
		break;
		case "drink":
			switch(word2) {
				case"coffee":
					drinkCoffee(false);
				break;
				case"blood":
					drinkBlood(false);
				break;
				default: System.out.println("You can't drink that...");
			}
		break;
		case "health":
			lookAtHealth(false);
			break;
		case "call":
			switch(word2) {
			case "station":
				callStation(false);
			}
			
			break;
		case "inspect"://inspect blood
			switch(word2) {
			
			case "blood":
				inspectBlood(false);
			break;
			
			case "knife":
				inspectKnife(false);
			break;
			
			case "fridge":
				inspectBody(false);
			break;
			default: System.out.println("You can't inspect that any further...");
			
			}
			break;				
			
		case "look":
			lookAtRoom(false);
		case "take":
			takeItem(word2);
			break;
			
		case "shoot":
			shootItem(word2);
			break;
		case "show":
			showItem(word2);
			break;
		case "drop":
			dropItem(word2);
			break;
			
		/**** SPECIAL COMMANDS ****/
		// ...		

		default: 
			System.out.println("Sorry, I don't understand that command. Type help for a list of commands");
		}
		return true;
	}//END OF PARSECOMMAND METHOD	

	//TALKING  METHOD
	void talking() {		
		//BOB
		if(currentRoom == "i_room" && ending) {
			System.out.println("You: Hey, Bob! What\'s the good news? **Press Enter: to see more of the conversation.**\n");
			String ans = getCommand();
			if(ans.equals("qwerty"))System.out.println("Bob: Thanks to you finding the missing people"
					+ " and your call to notify us,\n"
					+ "we were able to enterrogate a lead and make him confess. Great work Detective.\n");
			if(ans.equals("qwerty"))System.out.println("You: I couldn\'t of done it without your help!\n");
			win = true;
			return;
		}else {
			System.out.println("You can't talk right now");
		}
			
		//BAKERY CONVERSATION 
		if(currentRoom == "bakery") {
			System.out.println("Chat with the baker:");
			//while(true) {
			
			System.out.println("Choose either A or B\n A - Hello, Im the leading Detective "
					+ "in the missing persons case.\n B - Hello, can I get a coffee? ");
			String ans = getCommand();
			if(ans.equals("a")) {
				System.out.println("Hello Detective, what can I help you with? So sad what"
						+ " happened to those five people.\n ");
			}
			if(ans.equals("b")) {
			   	System.out.println("Of course! It's on the house **Type: Drink Coffee**");
			   	return;	
			}
					
			System.out.println("Choose either X or Y\n X - Is there anything you could "
					+ "tell me that could help me with my case?\n Y - I want a bagel. ");
			ans = getCommand();
			if(ans.equals("x")) {
				System.out.println("\nNothing noticeable around here but I have noticed"
						+ " the man at the deli looking a bit suspicious. Maybe talk to"
						+ " him and take a look around. ");
				return;//conversation ends
			}
			if(ans.equals("y")) {
				System.out.println("\nOf course! Its on the house **type: Take Bagel**");
				return;
			}
		}
		
		//DELI CONVERSATION
		if(currentRoom == "deli") {	
			System.out.println("You: Hello, Im the leading Detective "
					+ "in the missing persons case.  **Press Enter: to see more of the conversation.**\n ");
			String ans = getCommand();
			if(ans.equals("qwerty")) System.out.println("Mike: Whats a Detective like you doing in a deli shop,"
				+ " when there's five missing people in town\n");
			if(ans.equals("qwerty"))System.out.println("You: Maybe you can help me with that,"
					+ " have you noticed anthing of interest to te case?\n");
			if(ans.equals("qwerty"))System.out.println("Mike: Can't say I have.\n");
			if(ans.equals("qwerty"))System.out.println("You: Alright, do you mind if I take a look around?\n");
			if(ans.equals("qwerty"))System.out.println("Mike: Fine! Just don't take long, its not good for buisness.");
		}
		//HOUSE 1 CONVERSATION 
		if(currentRoom == "house1_inside") {
			System.out.println("Mrs. Johnston: Hello? Can I help you?\n");
			String ans = getCommand();
			if(ans.equals("qwerty"))System.out.println("You: Sorry, the door was open. I’m The detective in the missing persons case, "
					+ "and was wondering\n if you had any information that could"
					+ "help. I understand that your daughter was one of the missing people.\n");
			if(ans.equals("qwerty"))System.out.println("Mrs. Johnston: Yes, yes do you have any information for me first?\n");
			if(ans.equals("qwerty"))System.out.println("You: Well, I’m working on it. Where was the last time you saw your daughter?\n");
			if(ans.equals("qwerty"))System.out.println("Mrs. Johnston: well it was a few days ago… I was supposed to pick her up between the deli and the bakery but…\n");
			if(ans.equals("qwerty"))System.out.println("You: I’m sorry to bring this up but it’s very helpful. Do you remember what she was wearing? \n");
			if(ans.equals(""))System.out.println("Mrs Johnston: Um.. let me think, a pink sweater and orange yoga pants. \n");
			if(ans.equals("qwerty"))System.out.println("You: thank you for your time, i'll let you knows soon as I find out more. \n");
		}
		//HOUSE 2 CONVERSATION 
		if(currentRoom == "house2_inside") {
			System.out.println("No one seems to be home. You should leave the house.");	
		}
		//HOUSE 3 CONVERSATION 
		if(currentRoom == "house3_inside") {
			System.out.println("You: Hello Im the detective for the missing persons case can I ask you some questions?\n");
			String ans = getCommand();
			if(ans.equals("qwerty"))System.out.println("Inmate: You can ask, but if you think I would risk being sent back to jail over criminal activity, your wrong.");
			else return;
		}
		//WEAPON STORE CONVERSATION 
		if(currentRoom == "weapon_store") {
			System.out.println("You: Hey, im here to pick up the Detective's gun.\n");
			String ans = getCommand();
			if(ans.equals("qwerty"))System.out.println("Him: Of course! **Type: Take gun**\n");
			else return;
		}
	}//END OF TALKING METHOD
	
	//LOOK METHODS
	void lookAtRoom(boolean showDesc) {
		System.out.println("\n_.-._.-" + roomList.get(currentRoom).getTitle() + "-._.-._");
		System.out.println(roomList.get(currentRoom).getDesc());
		Room r = roomList.get(currentRoom);
		if (roomList.get(currentRoom).items.isEmpty()) {
			System.out.println("there are no items in this room");
		}else {
			System.out.println("items in the room: ");
			for (String s  : r.items) {
				System.out.println("> " + s);
			}
		}
	}
	void lookAtHealth(boolean showDesc) {
		System.out.println("\n_.-._. " + "Heath: " + player.health + " ._.-._");
	}
	void lookAtInventory(boolean showDesc) {
		System.out.println("INVENTORY " + inventoryList);
	}
	
	//END OF LOOK METHODS
	
	//MOVE TO ROOM METHOD
	void moveToRoom(char dir) {
		String newRoom = roomList.get(currentRoom).getExit(dir);
		if (newRoom != "") {	//does this direction work?
			if (newRoom != currentRoom) {	// is this a new room?
				currentRoom = newRoom;
				lookAtRoom(true);
			}
		}
		else {
			System.out.println("You can't go that way");
		}	
		if (currentRoom == "bakery" || currentRoom == "deli"){
			player.health -= 2;
		}
	}
	
	
	//EATING and DRINKING METHODS
	void eatBagel(boolean showDesc) {
		if (inventoryList.contains("bagel")) {
			System.out.println("You eat the Bagel.\t+2 health points");
			player.health = player.health + 2;
			roomList.get("bakery").items.add("bagel");
			inventoryList.remove("bagel");
		} else System.out.println("You don't have a bagel...");
		
	}
	void eatMuffin(boolean showDesc) {
		if (inventoryList.contains("muffin")) { 
			System.out.println("You eat the Muffin.\t+1 health point");
			player.health = player.health + 1;
			roomList.get("bakery").items.add("muffin");
			inventoryList.remove("muffin");
		} else System.out.println("You don't have a muffin...");
		
	}
	void eatMeat(boolean showDesc) {
		if (inventoryList.contains("meat")) {
			System.out.println("You eat the Meat. It tastes kind of funny...");
			roomList.get("deli").items.add("meat");
			inventoryList.remove("meat");
		} else System.out.println("You don't have any meat...");
		
	}
	void eatCorn(boolean showDesc) {
		if (inventoryList.contains("corn")) { 
			System.out.println("You eat some corn.\t+2 health points");
			player.health = player.health + 2;
			roomList.get("corn_field").items.add("corn");
			inventoryList.remove("corn");
		} else System.out.println("You don't have any corn...");
		
	}
	void eatPigs(boolean showDesc) {
		if (inventoryList.contains("pig")) {
			System.out.println("You ate like, an entire pig. Why?\t-15 health points");
			player.health = player.health - 15;
		} else System.out.println("You don't have any pigs to eat...");
		
	}
	void drinkCoffee(boolean showDesc) {
		if (inventoryList.contains("coffee")) {
			System.out.println("You drink the Coffee.\t+2 health points");
			player.health = player.health + 2;
			roomList.get("bakery").items.add("coffee");
			inventoryList.remove("coffee");
		} else System.out.println("You don't have any coffee...");
		
	}
	void drinkBlood(boolean showDesc) {
		if (inventoryList.contains("blood")) {
			System.out.println("Erm... You drink the blood? why would you do that??\n"
					+ "you feel funny.\t\t-5 health points");
			player.health = player.health - 5;
			inventoryList.remove("blood");
		} else System.out.println("You don't have any blood. Why would you drink it anyway?!?");
		
	}
	
	//END OF EATING AND DRINKING METHODS
	
	//CALLING METHOD
	void callStation(boolean showDesc) {
		//if you have the blood
		if (inventoryList.contains("blood") && ending == false) {
			System.out.println("_.-Blood-._\nOur team says that the blood seems to be from a pig.\n");
		}
		//if you have the knife
		if (inventoryList.contains("knife") && ending == false) {
			System.out.println("_.-Knife-._\nOur team thinks that this could be significant to the case.\n");
		}
		//if you found the bodies
		if(ending == true) {
			System.out.println("Good timing, detective. We have just caught the killer.\n"
					+ "Please come back to the station to interrogate him.");
		}
		//generic response
		else System.out.println("The team wishes you luck on your case, detective!");
	}
	
	
	//INSPECTING METHODS
	void inspectKnife(boolean showDesc) {
		if (currentRoom == "deli" && roomList.get(currentRoom).items.contains("knife")) {
			System.out.println("This seems important. You've bagged the item for evidence.");
			
			inventoryList.add("knife");
			roomList.get(currentRoom).items.remove("knife");
		}
		else {
			System.out.println("There are no knives here...");
		}
	}
	void inspectBlood(boolean showDesc) {
		if (currentRoom == "deli" && roomList.get(currentRoom).items.contains("blood")) {
			System.out.println("This could be the blood of the killer, You've taken a sample.\n"
					+ " You should call this in for futher inspection. ");
			
			inventoryList.add("blood");
			roomList.get(currentRoom).items.remove("blood");
		}
		else {
			System.out.println("There is no blood here...");
		}
	}
	void inspectBody(boolean showDesc) {
		if (currentRoom == "b_storage") {
			System.out.println("There are bodies in there!\n"
					+ "The bodies match the people in your case. You found the missing people,\n"
					+ " but sadly they are dead. You should call this in.");
			ending = true;
		}
		else {
			System.out.println("There are no bodies here...");
		}
	}
	
	//END OF INSPECTING METHODS
	
	//TAKE
	void takeItem(String word2) {		
		if(roomList.get(currentRoom).items.contains(word2)) {
			//if it is, remove it from the room
			roomList.get(currentRoom).items.remove(word2);
			//get the item object.
			Item item = itemList.get(word2); //do we need to check to see if it is in itemlist?
						//no. Every created item should be in here.
			//add it to the inventory
			inventoryList.add(word2);
			System.out.println("you take the " + word2);
		}
		else {
			System.out.println("sorry, you can't take the " + word2);
		}
	}
	
	//SHOOT METHODS
	void shootItem(String word2) {	
		if(roomList.get(currentRoom).items.contains(word2) && inventoryList.contains("gun")) {
			//if it is, remove it from the room
			roomList.get(currentRoom).items.remove(word2);
			
			System.out.println("you shoot the " + word2 + ", it's now broken... why did you do that.");
		}
		else {
			System.out.println("sorry, you can't shoot the " + word2);
		}
	}
	
	void showItem(String word2) {	
		if(inventoryList.contains(word2)) {
			//if it is, remove it from the room
			roomList.get(currentRoom).items.remove(word2);
			
			System.out.println("you shoot the " + word2 + ", it's now broken... why did you do that.");
		}
		else {
			System.out.println("sorry, you don't have the police badge.\nTry checking the police station. " + word2);
		}
	}
	
	//END OF SHOOT METHODS
	
	//DROP ITEM METHOD
	void dropItem(String word2) {	
		if(inventoryList.contains(word2)) {
			//if it is, remove it from the room
			
			roomList.get(currentRoom).items.add(word2);
			inventoryList.remove(word2);
			
			System.out.println("you drop the " + word2);
		}
		else {
			System.out.println("sorry, you don't have that. " + word2);
		}
	}
	
	//HELP METHOD
	void printHelp() {
		System.out.println("_.-._.-LIST OF COMMANDS-._.-._\n"
						 + "*\tquit\n"
						 + "*\tnorth, east, south, west\n"
						 + "*\ti, inventory\n"
						 + "*\tlook\n"
						 + "*\ttalk\n"
						 + "*\thealth\n"
						 + "*\teat \'item\', drink \'item\'\n"
						 + "*\tcall station\n"
						 + "*\tinspect \'item\'\n"
						 + "*\ttake \'item\', drop \'item\', shoot \'item\'");
	}	
	
}//end of class
