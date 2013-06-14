package com.zachnickell.platform.entity.item;

import java.util.ArrayList;

import com.zachnickell.platform.entity.Player;


public class Inventory {
	Player owner = null;
	
	public Inventory(Player owner){
		this.owner = owner;
	}
	
	public void add(Item item){
		item.use(owner);
	}
	
	public void renderHotBar(){
		
	}

}
