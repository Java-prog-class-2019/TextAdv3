package textAdventure;

//TITLE: The Oakville Mystery//

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class AdventureMain {

	static int INVSIZE = 10; //size of inventory	

	//instance variables
	//ArrayList<Room> roomList = new ArrayList<Room>();
	HashMap<String,Room> roomList = new HashMap<String,Room>();
	
	
	HashMap<String, Item> itemList = new HashMap<String,Item>(); //list of all item objects
	String currentRoom;
	Player player;
	boolean win = false;
	boolean die = false;
	int ending = 0;
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

			 
			if (win == true) {
				System.out.println("\tCongratulations you won the game!! Oakville is now the quiet and safe place it once was before. :) ");
				System.exit(0);
			}
			
			if (die == true) {
				System.out.println("\t You Died. You should have eaten :( Thanks for playing!");
				System.exit(0);
			}
			
		}

	}

	void setup() {
		player = new Player();
		Room.setupRooms(roomList);
		// ... more stuff ...
		//******Added*********
		System.out.println("This is The Oakville Mystery.");
		System.out.println("\n\t A Town once known to be peaceful and happy is claimed by calimity. A week ago,"
		+ "\n\t five people went missing from their homes. As the best detective around, you are\n\t"
		+ " asked to take on this case. Follow the clues around town to find the five missing\n\t"
		+ " people and catch the Kidnapper. The town is counting on you to restore the happy\n\t"
		+ " and calm reputation that Oakville was once known for. Dont forget to eat along\n\t"
		+ " the way and keep your health up. You can't work on an empty stomach! \n\t"
		+ " If you need help just type: Help");
		//starting room
		currentRoom = "police_station";
		
	}

	String getCommand() {
		Scanner sc = new Scanner(System.in);		
		String text = sc.nextLine();
		if (text.length() == 0) text = "qwerty"; //default command
		//sc.close();
		text = text.toLowerCase().trim();
		return text;
	}

	
	boolean parseCommand(String text) {

		/***** PREPROCESSING *****/
		
		
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
		case "sleep":
		//	sleep();			
			break;	
		case "help":
			//printHelp();
			break;
			
	/**** two word commands ****/		
		case "read":
			//readObject(word2);
			break;
		case "talk":
			talking();
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
			inspectBody(false);
			break;
		case "inspect"://inspect blood
			switch(word2) {
			
			case "blood":
				inspectBlood(false);
			break;
			
			case "knife":
				inspectKnife(false);
			break;
			
			case "body":
				inspectBody(false);
			break;
			
			
			}
			break;

			/*	case "take":
					switch(word2) {
					
					case "badge":
						//Detective's Badge
							invList.put("badge");
							itemList.remove("badge");
							roomList.remove("badge");
							System.out.println("You have grabbed: Detective's Badge");
					break;
					
					case "knife":
						inspectKnife(false);
					break;
					
					case "body":
						inspectBody(false);
					break;
					
					
					}
					break;
				*/	
				//case "look":
				//	lookAtRoom(false);
				//case "take":
					//takeItem(false);
				//	break;
					
			
		case "look":
			lookAtRoom(false);
		case "take":
			takeItem(false);
			//readObject(word2);
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
		if (currentRoom == "deli") {
			System.out.println("there seems to be a knife in the door way to the staff room,"
					+ " and a drop of blood int the corner of the room.");
		}
	}
	void lookAtHealth(boolean showDesc) {
		System.out.println("\n_.-._. " + "Heath: " + player.health + " ._.-._");
		if (player.health == 6) {
			System.out.println("\n***Your health is declining you should eat or drink something.***");
		}
		if (player.health == 0)	{
			die = true;
		}
	}
	void lookAtInventory(boolean showDesc) {
		System.out.println("\n_.-._.- Inventory -._.-._");
		System.out.println("\n" + itemList.get(showDesc));
	}
	
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
		//do whateverroom moving stuff you do
		//then	
		if (currentRoom == "bakery" || currentRoom == "deli"){
			player.health -= 4;
		}
		if (currentRoom == "interrogation_room" && ending == 1) {
			player.health -= 2;
		}
		
		
		
	}
	
	//TALKING 
	void talking() {
		
		//BOB
		if(currentRoom == "interrogation_room" && ending == 1) {
			System.out.println("You: Hey, Bob! What’s the good news?\n");
			String ans = getCommand();
			if(ans.equals("qwerty"))System.out.println("Bob: Thanks to you finding the missing people"
					+ " and your call to notify us, we were able to entarogate a lead and make him"
					+ " confess. Great work Detective.\n");
			if(ans.equals("qwerty"))System.out.println("You: I couldn’t of done it without your help!\n");
			win = true;
			return;
		}else {
			System.out.println("\nBob cant talk right now. :(");
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
				
//				System.out.println("Bye. Thanksforchatting");
//				return;
			//}
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
//				else return;
				
				if(ans.equals("qwerty"))System.out.println("Mike: Fine! Just don't take long, its not good for buisness.");
			
				if(ans.equals("qwerty"))System.out.println("\n\t**type: Look at (whatever you want to look at) ** ");
		}
		//House 1 Conversation 
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
			if(ans.equals("qwerty"))System.out.println("You: thank you for your time, i’ll let you knows soon as I find out more. \n");
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
			if(ans.equals("qwerty"))System.out.println("Him: Of cource! **Type: Take gun**\n");
			else return;
		}
		
	}
	
	//EATING and DRINKING
	void eatItem(boolean showDesc) {
		//player.health + a.healthPoints = player.health;
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
		if (currentRoom == "deli") {
			System.out.println("This seems important. You've bagged the item for evidence.");
		}
		else {
			System.out.println("There are no knives here...");
		}
	}
	void inspectBlood(boolean showDesc) {
		if (currentRoom == "deli") {
			System.out.println("This could be the blood of the killer, You've taken a sample.\n"
					+ " You should call this in for futher inspection. **Type: Call in **");
		}
		else {
			System.out.println("There is no blood here...");
		}
	}
	void inspectBody(boolean showDesc) {
		if (currentRoom == "b_storage") {
			System.out.println("The bodies match the people in your case. You found the missing people,\n"
					+ " but sadly they are dead... you should call this in! **Type: Call Station** \n" + 
					"you recently got a call from the station that they might have found the killer, go there now.");
			ending++;
		}
		else {
			System.out.println("There are no bodies here...");
		}
	}
	
	void takeItem(boolean showDesc) {
		//System.out.println("the " + word2 + "is now added to inventory.");
		//inventory.add.word2
		//how do we check what item they want 
		//we need to then put it in inventory 
		//get.word2 = itemList;
	}
		
	
}
