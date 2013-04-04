package com.zachnickell.platform.entity.tile;

import com.zachnickell.platform.gfx.Sprites;

public class Wall extends Tile {

	public Wall(int x, int y, int maxLevelWidth, int maxLevelHeight) {
		super(x, y, maxLevelWidth, maxLevelHeight);
		if (x == 0 && y == 0)
			sprite = Sprites.cornerWallTL;
		if (x == 0 && y != 0 && y != maxLevelHeight - 1)
			sprite = Sprites.leftWall;
		if (x == 0 && y == maxLevelHeight - 1)
			sprite = Sprites.cornerWallBL;
		if (x != 0 && x != maxLevelWidth - 1 && y == maxLevelHeight - 1)
			sprite = Sprites.bottomWall;
		if (x == maxLevelWidth -1 && y == maxLevelHeight -1)
			sprite = Sprites.cornerWallBR;
		if (x == maxLevelWidth - 1 && y != maxLevelHeight - 1 && y != 0)
			sprite = Sprites.rightWall;
		if (x == maxLevelWidth -1 && y == 0)
			sprite = Sprites.cornerWallTR;
		if (x != 0 && x != maxLevelWidth - 1 && y == 0)
			sprite = Sprites.topWall;
		
		isSolid = true;
		damage = 0;
	}
}