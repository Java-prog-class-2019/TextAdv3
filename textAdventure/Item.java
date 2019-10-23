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
		itemList.put("badge",a);
		roomList.get("a_block").items.add("Badge");
		//inventoryArray[0] == "Badge";
		
		a = new Item("Case File");
		itemList.put("file",a);
		roomList.get("police_station").items.add("File");
		
		a = new Item("Bagel");
		itemList.put("bagel",a);
		roomList.get("bakery").items.add("Bagel");
		
		a = new Item("Muffin");
		itemList.put("muffin",a);
		roomList.get("bakery").items.add("Muffin");
		
		a = new Item("Hot Coffee");
		itemList.put("coffee",a);
		roomList.get("bakery").items.add("Coffee");
		
		a = new Item("knife");
		itemList.put("Knife",a);
		roomList.get("deli").items.add("Knife");
		
		a = new Item("Drop of Blood");
		itemList.put("blood",a);
		roomList.get("deli").items.add("Blood");
		
		a = new Item("Deli Meat");
		itemList.put("meat",a);
		roomList.get("deli").items.add("Meat");
		
		a = new Item("Loaded Gun");
		itemList.put("gun",a);
		roomList.get("b_block").items.add("Gun");
		
		a = new Item("Hot Coffee");
		itemList.put("coffee",a);
		roomList.get("a_block").items.add("Coffee");
		
	}
}
