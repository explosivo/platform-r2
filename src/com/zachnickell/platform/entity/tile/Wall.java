package com.zachnickell.platform.entity.tile;

import org.newdawn.slick.particles.Particle;

import com.zachnickell.platform.gfx.Sprites;

public class Wall extends Tile {

	int orient;
	public static final int TOPLEFT = 0;
	public static final int TOP = 1;
	public static final int TOPRIGHT = 2;
	public static final int LEFT = 3;
	public static final int BOTTOMLEFT = 4;
	public static final int BOTTOM = 5;
	public static final int BOTTOMRIGHT = 6;
	public static final int RIGHT = 7;
	
	public Wall(int x, int y, int orient) {
		super(x, y);
		this.orient = orient;
		
		if (orient == TOPLEFT)
			sprite = Sprites.cornerWallTL;
		if (orient == LEFT)
			sprite = Sprites.leftWall;
		if (orient == BOTTOMLEFT)
			sprite = Sprites.cornerWallBL;
		if (orient == BOTTOM)
			sprite = Sprites.bottomWall;
		if (orient == BOTTOMRIGHT)
			sprite = Sprites.cornerWallBR;
		if (orient == RIGHT)
			sprite = Sprites.rightWall;
		if (orient == TOPRIGHT)
			sprite = Sprites.cornerWallTR;
		if (orient == TOP)
			sprite = Sprites.topWall;
		
		isSolid = true;
		damage = 0;
	}
}