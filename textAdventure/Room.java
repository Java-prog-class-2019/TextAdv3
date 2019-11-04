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
		case 'n': case 'N': return this.N;
		case 'e': case 'E': return this.E;
		case 's': case 'S': return this.S;
		case 'w': case 'W': return this.W;
		case 'u': case 'U': return this.U;
		case 'd': case 'D': return this.D;
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
		Room r = new Room("Police Station", "you are in the main block of the Police Station."
				+ "South of you is the Exit, East is B Block, and West is A Block. [N,E,W,S]");
		r.setExits("i_room", "bract_street", "a_block", "b_block", "", "");//N,S,W,E,U,D -- put roomList names here
		roomList.put("police_station",r);
		
	//INTERROGATION ROOM//
		r = new Room("Police Station", "This is the interrogation room in the Police Station."
				+ " South of you is the Main block of the police station. Your pal Bob is here. [S]");
		r.setExits("", "police_station", "", "", "", "");//N,S,W,E,U,D -- put roomList names here
		roomList.put("i_room",r);
				
	//A BLOCK//
		r = new Room("Police Station", "This is A Block of the Police Station."
				+ "Your office is in here. Don't forget your badge before you leave! [E]");
		r.setExits("", "", "", "police_station", "", "");//N,S,W,E,U,D -- put roomList names here
		roomList.put("a_block",r);

	//B BLOCK//
		r = new Room("Police Station", "This is B Block of the Police Station."
				+ "This is the Armoury. There are racks of guns on the wall. [W]");
		r.setExits("", "", "police_station", "", "", "");//N,S,W,E,U,D -- put roomList names here
		roomList.put("b_block",r);
		
	//Bract Street//
		r = new Room("Bract Street", "The eastern stretch of Bract Street. [N,E,W,S]");
		r.setExits("police_station", "deli", "b&m_intersection", "butchery", "", "");//N,S,W,E,U,D -- put roomList names here
		roomList.put("bract_street",r);
		
	//Deli//
		r = new Room("Deli", "\"Hello Officer. Is there anything you need today?\" "
				+ "a man with a beard says. [N]");
		r.setExits("bract_street", "", "", "", "", "");//N,S,W,E,U,D -- put roomList names here
		roomList.put("deli",r);

	//Bract and Main Intersection//
		r = new Room("Intersection", "This is the intersection between Bract Street and Main Street. [E,W,S]");
		r.setExits("", "main_street", "bract_left", "bract_street", "", "");//N,S,W,E,U,D -- put roomList names here
		roomList.put("b&m_intersection",r);
		
	//Bract Street Left//
		r = new Room("Bract Street", "The western stretch of Bract Street. [E,W]");
		r.setExits("", "", "b&r_intersection", "b&m_intersection", "", "");//N,S,W,E,U,D -- put roomList names here
		roomList.put("bract_left",r);
		
	//Bract and Rostock Intersection//
		r = new Room("Intersection", "This is the intersection between Bract Street and Rostock Way. [E,W,S]");
		r.setExits("", "rostock_north", "bract_tunnel", "bract_left", "", "");//N,S,W,E,U,D -- put roomList names here
		roomList.put("b&r_intersection",r);

	//Bract Tunnel//
		r = new Room("Tunnel", "This is a tunnel out of town. Don't think the kidnapper would have left through here. [E]");
		r.setExits("", "", "", "b&r_intersection", "", "");//N,S,W,E,U,D -- put roomList names here
		roomList.put("bract_tunnel",r);

	//Main Street//
		r = new Room("Main Street", "The northern stretch of Main Street. "
				+ "To the West is the park, and to the East is the Bakery. [N,E,W,S]");
		r.setExits("b&m_intersection", "main_south", "park", "bakery", "", "");//N,S,W,E,U,D -- put roomList names here
		roomList.put("main_street",r);
		
	//Bakery//
		r = new Room("Bakery", "\"Welcome to Dave\'s Bakery. How can I help you?\" "
				+ "the girl at the counter says. [W]");
		r.setExits("", "", "main_street", "", "", "");//N,S,W,E,U,D -- put roomList names here
		roomList.put("bakery",r);

	//Main Street South//
		r = new Room("Main Street", "The southern stretch of Main Street. "
				+ "To the West is Rostock Way, and to the East is the Weapon Store. [N,E,W,S]");
		r.setExits("main_street", "main_tunnel", "r&m_intersection", "weapon_store", "", "");//N,S,W,E,U,D -- put roomList names here
		roomList.put("main_south",r);
		
	//Weapon Store//
		r = new Room("Weapon Store", "\"What can I do ya for, Officer?\" "
				+ "a man at the counter says. [W]");
		r.setExits("", "", "main_south", "", "", "");//N,S,W,E,U,D -- put roomList names here
		roomList.put("weapon_store",r);
		
	//Park//
		r = new Room("Park", "This is the main park of Oakville. "
				+ "North of you is Bract Street. South and West is Rostock Way, and East is Main Street. [N,E,W,S]");
		r.setExits("bract_left", "rostock_south", "rostock_north", "main_street", "", "");//N,S,W,E,U,D -- put roomList names here
		roomList.put("park",r);
		
	//Main Tunnel//
		r = new Room("Tunnel", "This is a tunnel out of town. Don't think the kidnapper would have left through here. [N]");
		r.setExits("main_south", "", "", "", "", "");//N,S,W,E,U,D -- put roomList names here
		roomList.put("main_tunnel",r);
		
	//Rostock and Main Intersection//
		r = new Room("Intersction", "This is the intersection of Rostock Way and Main Street. [E,W,S]");
		r.setExits("", "house1", "rostock_south", "main_south", "", "");//N,S,W,E,U,D -- put roomList names here
		roomList.put("r&m_intersection",r);
		
	//House 1//
		r = new Room("House", "You are at 204 Rostock and Main. "
				+ "The front door is south of you. [N,S]");
		r.setExits("r&m_intersection", "house1_inside", "", "", "", "");//N,S,W,E,U,D -- put roomList names here
		roomList.put("house1",r);
		
	//House 1 Inside//
		r = new Room("House", "You can see the living room and kitchen from the entrance. "
				+ "Everything seems very clean and well kept. [N]");
		r.setExits("house1", "", "", "", "", "");//N,S,W,E,U,D -- put roomList names here
		roomList.put("house1_inside",r);
		
	//Rostock Way South//
		r = new Room("Rostock Way", "The southern stretch of Rostock Way. [N,E,W,S]");
		r.setExits("park", "house2", "rostock_north", "r&m_intersection", "", "");//N,S,W,E,U,D -- put roomList names here
		roomList.put("rostock_south",r);
		
	//House 2//
		r = new Room("House", "You are at 201 Rostock Way. [N,S]");
		r.setExits("rostock_south", "house2_inside", "", "", "", "");//N,S,W,E,U,D -- put roomList names here
		roomList.put("house2",r);
		
	//House 2 Inside//
		r = new Room("House", "You can see a the main hallway from the entrance. "
				+ "You can hear a dog barking from somewhere. [N]");
		r.setExits("house2", "", "", "", "", "");//N,S,W,E,U,D -- put roomList names here
		roomList.put("house2_inside",r);
		
	//Rostock Way North//
		r = new Room("Rostock Way", "The northern stretch of Rostock Way. [N,E,W,S]");
		r.setExits("b&r_intersection", "rostock_south", "house3", "park", "", "");//N,S,W,E,U,D -- put roomList names here
		roomList.put("rostock_north",r);
		
	//House of Paroled Inmate//
		r = new Room("House", "Here is 113 Rostock Way. "
				+ "A paroled inmate lives here. [E,W]");
		r.setExits("", "", "house3_inside", "rostock_north", "", "");//N,S,W,E,U,D -- put roomList names here
		roomList.put("house3",r);
		
	//Inside Inamte's House//
		r = new Room("House", "There is hardly anything inside and the whole place is a mess... [E]");
		r.setExits("", "", "", "house3", "", "");//N,S,W,E,U,D -- put roomList names here
		roomList.put("house3_inside",r);
		
	//Butchery//
		r = new Room("Butchery", "The local Butchery is here. It smells really bad in here. South of you is a dirt road."
				+ "To the north of you is the enterance to the Butchery. [N,W,S]");
		r.setExits("b_inside", "dirt_road", "bract_street", "", "", "");//N,S,W,E,U,D -- put roomList names here
		roomList.put("butchery",r);
		
	//Butchery Inside//
		r = new Room("Butchery", "You are at the entrance to the Butchery. The smell is overpowering. "
				+ "South of you is the exit. There are pigs hanging from the ceiling. [E,S]");
		r.setExits("", "butchery", "", "b_storage", "", "");//N,S,W,E,U,D -- put roomList names here
		roomList.put("b_inside",r);
		
	//Butchery Storage Room//
		r = new Room("Butchery", "You are in the Storage Locker for the Butchery."
				+ "It is a little chilly, but that is probably to preserve the meat.\n"
				+ "You see a suspicous fridge in the corner... [W]");
		r.setExits("", "", "b_inside", "", "", "");//N,S,W,E,U,D -- put roomList names here
		roomList.put("b_storage",r);
		
	//Dirt Road//
		r = new Room("Dirt Road", "You are on the dirt road. to your north is the butchery,"
				+ " and you can see a corn field to the south. [N,S]");
		r.setExits("butchery", "corn_field", "", "", "", "");//N,S,W,E,U,D -- put roomList names here
		roomList.put("dirt_road",r);
		
	//Corn Field//
		r = new Room("Corn Field", "You have found yourself in a corn field. "
				+ "To be honest, the corn is so tall you can't see inside or around it. [N]");
		r.setExits("dirt_road", "", "", "", "", "");//N,S,W,E,U,D -- put roomList names here
		roomList.put("corn_field",r);
	
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