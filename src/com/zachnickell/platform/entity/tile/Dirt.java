package com.zachnickell.platform.entity.tile;

import com.zachnickell.platform.gfx.Sprites;

public class Dirt extends Tile{

	public Dirt(int x, int y) {
		super(x, y);
		sprite = Sprites.dirt;
		isSolid = true;
		damage = 0;
	}
}