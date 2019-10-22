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
		
		Item a = new Item("Your Phone");
		//z.descrRoom = "there is a shiny object near by";	//this is displayed along with the room description when you look at the room.
		itemList.put("Phone",a);						    //this is the true (hashmap) name of the item. It's never displayed.
		//roomList.get("path1").items.add("sandwich");	    //and here the item is added to the specific room that you want it in. 

		 a = new Item("Detective's Badge");
		itemList.put("Badge",a);
		roomList.get("Police Station").items.add("Badge");
		//inventoryArray[0] == "Badge";
		
		a = new Item("Case File");
		itemList.put("File",a);
		roomList.get("Police Station").items.add("File");
		
		a = new Item("Bagel");
		a.healthPoints = 2;
		itemList.put("Bagel",a);
		roomList.get("Bakery").items.add("Bagel");
		
		a = new Item("Muffin");
		a.healthPoints = 1;
		itemList.put("Muffin",a);
		roomList.get("Bakery").items.add("Muffin");
		
		a = new Item("Hot Coffee");
		a.healthPoints = 2;
		itemList.put("Coffee",a);
		roomList.get("Bakery").items.add("Coffee");
		
		a = new Item("Knife");
		itemList.put("Knife",a);
		roomList.get("Deli").items.add("Knife");
		
		a = new Item("Drop of Blood");
		itemList.put("Blood",a);
		roomList.get("Deli").items.add("Blood");
		
		a = new Item("Deli Meat");
		itemList.put("Meat",a);
		roomList.get("Deli").items.add("Meat");
		
		a = new Item("Loaded Gun");
		itemList.put("Gun",a);
		roomList.get("Police Station").items.add("Gun");
		
		a = new Item("Hot Coffee");
		a.healthPoints = 2;
		itemList.put("Coffee",a);
		roomList.get("Police Station").items.add("Coffee");
		
	}
}
