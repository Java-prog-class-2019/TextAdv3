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
//		r = new Room("title", "Description");
//		r.setExits("north", "south", "west", "east", "up", "down");//N,S,W,E,U,D -- put roomList names here
//		roomList.put("name in the list",r);
		
	//POLICE STATION//
		Room r = new Room("Police Station", "you are in the main block of the Police Station. "
				+ "South of you is the Exit, East is B Block, and West is A Block.");
		r.setExits("", "bract_street", "a_block", "b_block", "", "");//N,S,W,E,U,D -- put roomList names here
		roomList.put("police_station",r);
		
	//A BLOCK//
		r = new Room("Police Station", "This is A Block of the Police Station. "
				+ "Your office is in here. Don't forget your badge before you leave!");
		r.setExits("", "", "", "police_station", "", "");//N,S,W,E,U,D -- put roomList names here
		roomList.put("a_block",r);

	//B BLOCK//
		r = new Room("Police Station", "This is B Block of the Police Station. "
				+ "This is the Armoury. There are racks of guns on the wall.");
		r.setExits("", "", "police_station", "", "", "");//N,S,W,E,U,D -- put roomList names here
		roomList.put("b_block",r);
		
	//Bract Street//
		r = new Room("Bract Street", "The eastern stretch of Bract Street.");
		r.setExits("police_station", "", "b&m_intersection", "butchery", "", "");//N,S,W,E,U,D -- put roomList names here
		roomList.put("bract_street",r);

	//Bract and Main Intersection//
		r = new Room("Intersection", "This is the intersection between Bract Street and Main Street.");
		r.setExits("", "main_street", "bract_left", "bract_street", "", "");//N,S,W,E,U,D -- put roomList names here
		roomList.put("b&m_intersection",r);
		
	//Bract Street Left//
		r = new Room("Bract Street", "The western stretch of Bract Street.");
		r.setExits("", "", "b&r_intersection", "b&m_intersection", "", "");//N,S,W,E,U,D -- put roomList names here
		roomList.put("bract_left",r);
		
	//Bract and Rostock Intersection//
		r = new Room("Intersection", "This is the intersection between Bract Street and Rostock Way.");
		r.setExits("", "rostock_way", "bract_tunnel", "bract_left", "", "");//N,S,W,E,U,D -- put roomList names here
		roomList.put("b&r_intersection",r);

	//Bract Tunnel//
		r = new Room("Tunnel", "This is a tunnel out of town. Don't think the kidnapper would have left through here.");
		r.setExits("", "", "", "b&r_intersection", "", "");//N,S,W,E,U,D -- put roomList names here
		roomList.put("bract_tunnel",r);

	//Main Street//
		r = new Room("Main Street", "The northern stretch of Main Street. "
				+ "To the West is the park, and to the East is the Bakery.");
		r.setExits("b&m_intersection", "main_south", "park", "bakery", "", "");//N,S,W,E,U,D -- put roomList names here
		roomList.put("main_street",r);

	//Main Street South//
		r = new Room("Main Street", "The southern stretch of Main Street. "
				+ "To the West is Rostock Way, and to the East is the Weapon Store.");
		r.setExits("main_street", "main_tunnel", "r&m_intersection", "weapon_store", "", "");//N,S,W,E,U,D -- put roomList names here
		roomList.put("main_south",r);
		
	//Park//
		r = new Room("Park", "This is the main park of Oakville. "
				+ "North of you is Bract Street. South and West is Rostock Way, and East is Main Street.");
		r.setExits("bract_left", "rostock_south", "rostock_north", "main_street", "", "");//N,S,W,E,U,D -- put roomList names here
		roomList.put("park",r);
		
	//Main Tunnel//
		r = new Room("Tunnel", "This is a tunnel out of town. Don't think the kidnapper would have left through here.");
		r.setExits("main_south", "", "", "", "", "");//N,S,W,E,U,D -- put roomList names here
		roomList.put("main_tunnel",r);
		
	//Rostock and Main Intersection//
		r = new Room("Intersction", "This is the intersection of Rostock Way and Main Street.");
		r.setExits("", "house1", "rostock_south", "main_south", "", "");//N,S,W,E,U,D -- put roomList names here
		roomList.put("r&m_intersction",r);
		
	//Rostock Way South//
		r = new Room("Rostock Way", "The southern stretch of Rostock Way.");
		r.setExits("park", "house2", "rostock_north", "r&m_intersection", "", "");//N,S,W,E,U,D -- put roomList names here
		roomList.put("rostock_south",r);
		
	//Rostock Way North//
		r = new Room("Rostock Way", "The northern stretch of Rostock Way.");
		r.setExits("b&r_intersection", "rostock_south", "house3", "park", "", "");//N,S,W,E,U,D -- put roomList names here
		roomList.put("rostock_north",r);
	
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