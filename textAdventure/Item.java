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
		itemList.put("Phone",a);						    //this is the true (hashmap) name of the item. It's never displayed.
		//roomList.get("path1").items.add("sandwich");	    //and here the item is added to the specific room that you want it in. 

		Item b = new Item("Dectective's Badge");
		itemList.put("Badge",b);
		//roomList.get("Police Station").items.add("Badge");
		
		Item c = new Item("Case File");
		itemList.put("File",c);
		//roomList.get("Police Station").items.add("File");
		
		Item d = new Item("Bagel");
		AdventureMain.health++;
		itemList.put("Bagel",d);
		//roomList.get("Bakery").items.add("Bagel");
		
		Item e = new Item("Muffin");
		AdventureMain.health++;
		itemList.put("Muffin",e);
		//roomList.get("Bakery").items.add("Muffin");
		
		Item f = new Item("Hot Coffee");
		AdventureMain.health++;
		itemList.put("Coffee",f);
		//roomList.get("Bakery").items.add("Coffee");
		
		Item g = new Item("Knife");
		itemList.put("Knife",g);
		//roomList.get("Deli").items.add("Knife");
		
		Item h = new Item("Drop of Blood");
		itemList.put("Blood",h);
		//roomList.get("Deli").items.add("Blood");
		
		Item i = new Item("Deli Meat");
		itemList.put("Meat",i);
		//roomList.get("Deli").items.add("Meat");
		
		Item j = new Item("Loaded Gun");
		itemList.put("Gun",j);
		//roomList.get("Police Station").items.add("Gun");
		
		Item k = new Item("Hot Coffee");
		AdventureMain.health++;
		itemList.put("Coffee",k);
		//roomList.get("Police Station").items.add("Coffee");
		
	}
}
