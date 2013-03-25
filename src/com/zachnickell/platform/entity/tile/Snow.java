package com.zachnickell.platform.entity.tile;

import com.zachnickell.platform.gfx.Sprites;

public class Snow extends Tile {

	public Snow(int x, int y) {
		super(x, y);
		sprite = Sprites.snow;
		damage = 0;
	}
}
