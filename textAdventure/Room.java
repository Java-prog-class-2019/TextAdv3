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
		
		//Basic Room thingy//
//		Room r = new Room("Name", "Description");
//		r.setExits("north", "south", "west", "east", "up", "down");//N,S,W,E,U,D -- put roomList names here
//		roomList.put("name in the list",r);
		
		Room r = new Room("Police Station", "you are in the main block of the Police Station. "
				+ "South of you is the Exit, East is B Block, and West is A Block.");
		r.setExits("", "bract_street", "a_block", "b_block", "", "");//N,S,W,E,U,D -- put roomList names here
		roomList.put("police_station",r);
		
	
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