package com.zachnickell.platform.entity;
import java.awt.Color;

public class Rock extends Entity{

	public Rock(){
		x = 20;
		y = 20;
		w = 20;
		h = 20;
		isMovable = true;
		isSolid = true;
		c = Color.GRAY;
		damage = 1;
	}

	
}
