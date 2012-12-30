package com.zachnickell.platform.entity.vehicle;

import java.awt.Color;


public class SpaceShip extends Vehicle{
	public SpaceShip(int x, int y){
		isFlying = true;
		this.x = x*16;
		this.y = y*16;
		w = 16;
		h = 16;
		c = Color.gray;
	}
}
