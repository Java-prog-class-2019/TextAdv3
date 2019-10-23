package textAdventure;

import java.util.HashMap;

class Item {
	
	String name;
	int healthPoints = 0;

	/*****************************************************************************************
	 * About items:
	 * ============
	 * Items are added in the Item class, via a similar construct to the static void setupRooms() method
	 * It is in the Item class that I'm adding items to each room, that way a room never contains an object that is not in this list.
	 * All objects are items, including containers that have other items in them.
	/*********/
	
	
	//Had to add this??
	public Item(String string) {
		name = string;
	}

	static void setUpItems(HashMap<String,Item> itemList, HashMap<String,Room> roomList) {
		
		Item item = new Item("Your Phone");
		//z.descrRoom = "there is a shiny object near by";	//this is displayed along with the room description when you look at the room.
		itemList.put("Phone",item);						    //this is the true (hashmap) name of the item. It's never displayed.
		//roomList.get("path1").items.add("sandwich");	    //and here the item is added to the specific room that you want it in. 

		item = new Item("Detective's Badge");
		itemList.put("Badge",item);
		
		//roomList.get("a_block").items.add("Badge");
		
		Room r = roomList.get("a_block");
		
		r.items.add("Badge");
		

		for (String s  : r.items) {
			System.out.println("--- " + s);
		}
		
		
		//inventoryArray[0] == "Badge";
		
		item = new Item("Case File");
		itemList.put("File",item);
		r = roomList.get("Police Station");
				r.items.add("File");
		
		item = new Item("Bagel");
		item.healthPoints = 2;
		itemList.put("Bagel",item);
		roomList.get("Bakery").items.add("Bagel");
		
		item = new Item("Muffin");
		item.healthPoints = 1;
		itemList.put("Muffin",item);
		roomList.get("Bakery").items.add("Muffin");
		
		item = new Item("Hot Coffee");
		item.healthPoints = 2;
		itemList.put("Coffee",item);
		roomList.get("Bakery").items.add("Coffee");
		
		item = new Item("Knife");
		itemList.put("Knife",item);
		roomList.get("Deli").items.add("Knife");
		
		item = new Item("Drop of Blood");
		itemList.put("Blood",item);
		roomList.get("Deli").items.add("Blood");
		
		item = new Item("Deli Meat");
		itemList.put("Meat",item);
		roomList.get("Deli").items.add("Meat");
		
		item = new Item("Loaded Gun");
		itemList.put("Gun",item);
		roomList.get("Police Station").items.add("Gun");
	//	roomList.get("Police Station").items.
		
	//	item = new Item("Hot Coffee");
	//	item.healthPoints = 2;
	//	itemList.put("Coffee",item);
	//	roomList.get("Police Station").items.add("Coffee");
		
	}
}
