package com.zachnickell.platform.level;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.zachnickell.platform.Platform;
import com.zachnickell.platform.entity.*;
import com.zachnickell.platform.entity.tile.*;

public class Level {
	// screen size is 20 x 15 @(16x16)size tiles
	// 50 x 50 map is highest efficient running size so far 3/24/13
	// 256 x 256 map at 56% cpu and ~30 fps 				3/25/13
	// 175 x 175 map running smoothly at 4% cpu and ~60 fps 3/25/13 2:35PM
	
	int width = 175;
	int height = 175;
	int spawnX = width / 2;
	int spawnY = height / 2;
	Player player;
	Rock rock;
	PlayerGui pg;
	Tile[][] tiles;
	int[][] tileGrid = new int[width][height];

	public Level() {
		player = new Player(spawnX, spawnY);
		rock = new Rock();
		tiles = new Tile[width][height];
		pg = new PlayerGui(player);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				tileGrid[0][y] = 2;
				tileGrid[width - 1][y] = 2;
				tileGrid[x][0] = 2;
				tileGrid[x][height - 1] = 2;
			}
		}

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				tiles[x][y] = new Tile(x, y);
			}
		}

	}

	public void render(Graphics g) {
		Graphics gg = g.create();
		g.translate((int) -player.x + Platform.WIDTH / 2 - 10, (int) -player.y
				+ Platform.HEIGHT / 2 - 10);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				tiles[x][y].render(g);
			}
		}
		// rock.render(g);
		g.setColor(Color.red);
		g.drawRect((int) player.x - (Platform.WIDTH) / 2 + 20, (int) player.y
				- (Platform.HEIGHT) / 2 + 40, Platform.WIDTH - 20,
				Platform.HEIGHT - 50);
		player.render(g);
		pg.render(gg);
		gg.dispose();
	}

	public void update(int delta) {
		player.update(delta);
		// rock.update(delta);
		createLevel();
		shouldRender(delta);
		CollisionDetect(delta);
	}

	public void createLevel() {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (tiles[x][y].isOnScreen) {
					if (tileGrid[x][y] == Tile.DEFAULT) {
						tiles[x][y] = new Tile(x, y);
					} else if (tileGrid[x][y] == Tile.SNOW) {
						tiles[x][y] = new Snow(x, y);
					} else if (tileGrid[x][y] == Tile.DIRT) {
						tiles[x][y] = new Dirt(x, y);
					} else
						tiles[x][y] = new Tile(x, y);
				}
			}
		}
	}

	public void shouldRender(int delta) {
		Rectangle r1 = new Rectangle(
				(int) player.x - (Platform.WIDTH) / 2 + 20, (int) player.y
						- (Platform.HEIGHT) / 2 + 40, Platform.WIDTH - 20,
				Platform.HEIGHT - 50);
		Rectangle r2;
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				r2 = tiles[x][y].getBounds();
				if (r1.intersects(r2)) {
					tiles[x][y].shouldRender();
				} else
					tiles[x][y].shouldNotRender();
			}
		}

	}

	public void CollisionDetect(int delta) {
		Rectangle r1;
		Rectangle r2;
		r1 = player.getBounds();
		/*
		 * r2 = rock.getBounds(); if (r1.intersects(r2)){ rock.collision(player,
		 * delta); } else player.fixMovement();
		 */
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				r2 = tiles[x][y].getBounds();
				if (r1.intersects(r2)) {
					tiles[x][y].collision(player, delta);
				}
			}
		}
	}
}
