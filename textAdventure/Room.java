package textAdventure;

import java.util.*;

class Room{
	
	/***** Instance variables (no static variable needed) *****/
	//NOTE that I am describing each variable - what it's used for.
	
	private String title;			//the short display name of the room
	private String description;		//the description that is displayed when you first enter the room or type "look"
	private String N,S,W,E,U,D; 	//these are exits that point to the HashMap name of other rooms.
	private boolean isDark=false;	//is the room dark (so you need a flashlight or else yoou die if you stay here)
	private boolean visited=false;	//has the user visited this room already?
	ArrayList<String> items = new ArrayList<String>(); //items in this room
	
	

	/**** constructor *****/
	Room(String title, String description){
		this.title = title;
		this.description = description;		
	}
	
	/***** getters and setters *****/
	//Called from Room.setupRooms() as well as from any method in AdventureMain that creates new exits or reroutes some of the map.
	void setExits(String N, String S, String W, String E, String U, String D) {
		this.N = N;
		this.E = E;
		this.S = S;
		this.W = W;		
		this.U = U;		
		this.D = D;		
	}
	
	String getExit(char c) {
		switch (c) {
		case 'N': return this.N;
		case 'E': return this.E;
		case 'S': return this.S;
		case 'W': return this.W;
		case 'U': return this.U;
		case 'D': return this.D;
		default: return ""; //Should I return some other value to indicate a non-existent direction?
		}
	}
	
	String getTitle()		{ return title; }
	String getDesc()		{ return description; }
	boolean hasVisited()	{ return visited; }
	void visit()			{ visited = true; }
	boolean getIsDark()		{ return this.isDark; }
	
	
	@Override
	public String toString(){
		String s = String.format("Title=%-25s\tDescription=%s",title,description);		
		return s;
	}


	/* This is the method that creates the rooms. It could logically be in AdventureMain.java, but that is already long enough, so I stuck it here.
	 * The hashmap names that are use in the roomlist.PUT() method are the true names of the rooms and must be unique. 
	 * These are the names that are also used in all of the exits.
	 * The names may or may not correspond to the title of the room (and multiple rooms can have the same title, like in a maze). */
	static void setupRooms(HashMap<String,Room> roomList) {
		
		Room r = new Room("Forest Clearing", 
				"There is a lovely clearing in the forest here. It is very peaceful.\n"
				+ "There is a path leaning north and another windy one that heads off to the west.");
		//		   N S W E U D	<< exit sequence
		r.setExits("forest1", "",  "path1","", "","");		
		roomList.put("clearing",r);		
		
		r = new Room("Forest path", 
				"You are on a path in the forest. "
				+ "A very tall tree completely blocks the path to the north. "
				+ "There is a path to the south.");
		r.setExits("", "clearing","", "", "tree1","");
		roomList.put("forest1",r);
		
		r = new Room("Partway up a huge tree", 
				"It is getting a lot harder to climb. Perhaps you should stop now.");
		r.setExits("", "","tree1b", "tree1b", "m_fallFromTree","forest1");				
		roomList.put("tree1",r);
		
		r = new Room("Partway up a huge tree", 
				"It is getting a lot harder to climb. Perhaps you should stop now.");
		r.setExits("", "","tree1", "tree1", "m_fallFromTree","forest2");				
		roomList.put("tree1b",r);
		
		r = new Room("Forest path", 
				"You are in a gloomy part of the forest."
				+ " A very tall tree completely blocks the path to the south."
				+ " There are narrow windy paths in other directions, but you "
				+ "might get lost if you follow them.");
		r.setExits("", "","", "", "tree1b","");
		roomList.put("forest2",r);
		
		r = new Room("Forest path", 
				"There appears to be a clearing to the north. A cave is to the west. "
				+ "The path continues to the south.");
		r.setExits("clearing", "path2","cave1", "", "","");		
		roomList.put("path1",r);
		
		r = new Room("Entrance to cave",
				"You're standing in the entrance to a dark cave. A path is to the east "
				+ "and the cave goes further on to the west");
		r.setExits("", "","cave2", "path1", "","");
		roomList.put("cave1",r);
					
		r= new Room("Sparkling cave",
				"This is a huge cavern. The walls of the cave sparkle with shiny crystals."
				+ " There is light to the east"
				+ " and passages in other directions.");	
		r.setExits("chimney", "tunnel1", "cave3", "cave1", "","tunnel1");
		r.isDark = true;		
		roomList.put("cave2", r);
		
		r = new Room("Fetid cave", "This cave smells stale and nauseating."); 
		r.setExits("", "","", "cave2", "","");
		r.isDark = true;		
		roomList.put("cave3", r);
		
		r = new Room("Tunnel","a slippery tunnel connecting parts of the cave system.");
		r.setExits("cave2", "tunnel2","cave2", "", "","");
		r.isDark = true;		
		roomList.put("tunnel1", r);

		r = new Room("Tunnel","a long slippery tunnel sloping slightly downwards.");
		r.setExits("tunnel1", "tunnel3","", "", "secret_room","");
		r.isDark = true;		
		//make a secret exit up top -- a single room with a hidden gadget?
		roomList.put("tunnel2", r);
		
		//this is the room that has the earthquake generator lever (item) in it.
		r = new Room("Secret Room", "You found a secret room with beautiful murals. "
				+ "\nYou can exit by climbing down to the tunnel below. ");
		r.setExits("","","","","","tunnel2");
		roomList.put("secret_room",r);		
		
		r = new Room("Tunnel","a slippery tunnel."
				+ "\nAt the south end is a room whose entrance is blocked by massive"
				+ " iron door that is off its hinges,"
				+ "\nbut, it looks like you can squeeze past it.");
		r.setExits("tunnel2", "treasury","", "", "","");
		r.isDark = true;		
		roomList.put("tunnel3", r);
				
		r = new Room("Dwarf treasury","centuries ago, this was the treasury of the dwarfs."
				+ "\nIt has obviously been thoroughly looted, though the ceiling is still "
				+ "lit by glowing crystals."
				+ "\nYou entered from the tunnel to the west.");
		r.setExits("", "","tunnel3", "", "","");
		r.isDark = false;		
		roomList.put("treasury", r);
		
		r = new Room("Chimney crack",
				"A vertical crack in the rock - it is climbable since you're so fit.");
		r.setExits("", "","", "", "cave2","chimney2");
		r.isDark = true;		
		roomList.put("chimney", r);
		
		r = new Room("Chimney crack",
				"A vertical crack in the rock - you can't see the top nor the bottom.");
		r.setExits("", "","", "", "chimney","chimney3");
		r.isDark = true;		
		roomList.put("chimney2", r);
		
		r = new Room("Chimney crack",
				"A vertical crack in the rock - tight and uncomfortable.");
		r.setExits("", "","", "", "chimney2","black_lake");
		r.isDark = true;		
		roomList.put("chimney3", r);
		
		r = new Room("Black lake",
				"You have descended to a large underground cavern. A black lake laps against"
				+ " the shore."
				+ "\nThe only exit is a narrow crack that extends upwards out of sight.");
		r.setExits("", "","", "", "chimney3","");
		r.isDark = true;		
		roomList.put("black_lake", r);

	
/* 		//UNCOMMENT THIS FOR DEBUGGING
 
		//List all rooms	
		System.out.println("*****************************************");
		for (Room m : roomList.values()){
			System.out.println(m.toString());
		}
		System.out.println("*****************************************\n\n");
*/

	}

}