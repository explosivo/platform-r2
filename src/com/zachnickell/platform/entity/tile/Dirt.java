package com.zachnickell.platform.entity.tile;

public class Dirt extends Tile{

	public Dirt(int x, int y) {
		super(x, y);
		sprite = dirt;
		isSolid = true;
		damage = 0;
	}
}