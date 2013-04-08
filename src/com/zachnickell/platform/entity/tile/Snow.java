package com.zachnickell.platform.entity.tile;

import com.zachnickell.platform.gfx.Sprites;

public class Snow extends Tile {

	public Snow(int x, int y, int maxLevelWidth, int maxLevelHeight) {
		super(x, y, maxLevelWidth, maxLevelHeight);
		//sprite = Sprites.snow;
		damage = 0;
	}
}
