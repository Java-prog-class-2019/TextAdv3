package textAdventure;

import java.util.HashMap;

class Item {
	
	/*****************************************************************************************
	 * About items:
	 * ============
	 * Items are added in the Item class, via a similar construct to the static void setupRooms() method
	 * It is in the Item class that I'm adding items to each room, that way a room never contains an object that is not in this list.
	 * All objects are items, including containers that have other items in them.
	/*********/
	
	
	//Had to add this??
	public Item(String string) {
		
	}

	static void setUpItems(HashMap<String,Item> itemList, HashMap<String,Room> roomList) {
		
		Item a = new Item("Your Phone");
		//z.descrRoom = "there is a shiny object near by";	//this is displayed along with the room description when you look at the room.
		itemList.put("Phone",a);						//this is the true (hashmap) name of the item. It's never displayed.
		//roomList.get("path1").items.add("sandwich");	//and here the item is added to the specific room that you want it in. 

		Item b = new Item("Dectective's Badge");
		itemList.put("Badge",b);
		//roomList.get("path1").items.add("Badge");
		
		Item c = new Item("Case File");
		itemList.put("File",c);
		//roomList.get("path1").items.add("File");
		
		Item d = new Item("Bagel");
		//z.descrRoom = "Something smells good."
		//d.foodpoints = 10;
		itemList.put("Bagel",d);
		//roomList.get("path1").items.add("Bagel");
		
		Item e = new Item("Muffin");
		//d.foodpoints = 10;
		itemList.put("Muffin",e);
		//roomList.get("path1").items.add("Muffin");
		
		Item f = new Item("Hot Coffee");
		//d.foodpoints = 10;
		itemList.put("Coffee",f);
		//roomList.get("path1").items.add("Coffee");
		
		Item g = new Item("Knife");
		itemList.put("Knife",g);
		//roomList.get("path1").items.add("Knife");
		
		Item h = new Item("Drop of Blood");
		itemList.put("Blood",h);
		//roomList.get("path1").items.add("Blood");
		
		Item i = new Item("Deli Meat");
		itemList.put("Meat",i);
		//roomList.get("path1").items.add("Meat");
		
		Item j = new Item("Loaded Gun");
		itemList.put("Gun",j);
		//roomList.get("path1").items.add("Gun");
		
	}
}
