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
		itemList.put("badge",item);
		
		//roomList.get("a_block").items.add("Badge");
		
		Room r = roomList.get("a_block");
		
		r.items.add("badge");
		

		for (String s  : r.items) {
			System.out.println("--- " + s);
		}
		
		
		//inventoryArray[0] == "Badge";
		
		item = new Item("Case File");
		itemList.put("file",item);
		r = roomList.get("a_block");
				r.items.add("file");
		
		item = new Item("Bagel");
		item.healthPoints = 2;
		itemList.put("bagel",item);
		roomList.get("bakery").items.add("bagel");
		
		item = new Item("Corn Cob");
		item.healthPoints = 1;
		itemList.put("corn",item);
		roomList.get("corn_field").items.add("corn");
		
		item = new Item("Muffin");
		item.healthPoints = 1;
		itemList.put("muffin",item);
		roomList.get("bakery").items.add("muffin");
		
		item = new Item("Hot Coffee");
		item.healthPoints = 2;
		itemList.put("coffee",item);
		roomList.get("bakery").items.add("coffee");
		
		item = new Item("Knife");
		itemList.put("knife",item);
		roomList.get("deli").items.add("knife");
		
		item = new Item("Drop of Blood");
		itemList.put("blood",item);
		roomList.get("deli").items.add("blood");
		
		item = new Item("Deli Meat");
		itemList.put("meat",item);
		roomList.get("deli").items.add("meat");
		
		item = new Item("Loaded Gun");
		itemList.put("gun",item);
		roomList.get("b_block").items.add("gun");
	//	roomList.get("Police Station").items.
		
	//	item = new Item("Hot Coffee");
	//	item.healthPoints = 2;
	//	itemList.put("Coffee",item);
	//	roomList.get("Police Station").items.add("Coffee");
		
	}
}
