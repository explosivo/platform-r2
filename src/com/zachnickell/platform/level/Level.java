package com.zachnickell.platform.level;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.Random;

import com.zachnickell.platform.Platform;
import com.zachnickell.platform.entity.*;
import com.zachnickell.platform.entity.tile.*;

public class Level {
	// screen size is 20 x 15 @(16x16)size tiles
	// 50 x 50 map is highest efficient running size so far 3/24/13
	// 256 x 256 map at 56% cpu and ~30 fps 3/25/13
	// 175 x 175 map running smoothly at 4% cpu and ~60 fps 3/25/13 2:35PM

	Random r = new Random();
	int width = 25;
	int height = 25;
	int spawnX = width / 2;
	int spawnY = height / 2;
	Player player;
	int monsterNumber = 30;
	Monster[] monsters = new Monster[monsterNumber];
	Rock rock;
	PlayerGui pg;
	Tile[][] tiles;
	int[][] tileGrid = new int[width][height];

	public Level() {
		player = new Player(spawnX, spawnY);
		for (int m = 0; m < monsterNumber; m++) {
			// System.out.println("!");
			monsters[m] = new Monster(r.nextInt(width), r.nextInt(height),
					player, monsters, this);
		}
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
				tiles[x][y] = new Tile(x, y, width, height);
			}
		}

	}

	public void render(Graphics g) {
		Graphics gg = g.create();
		//Graphics ggg = g.create();
		Graphics2D g2 = (Graphics2D) g;
		g2.translate((int) -player.x + Platform.WIDTH / 2 - 10, (int) -player.y
				+ Platform.HEIGHT / 2 - 10);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				tiles[x][y].render(g);
			}
		}
		for (int m = 0; m < monsterNumber; m++) {
			monsters[m].render(g);
		}
		// rock.render(g);
		//g.setColor(Color.red);
		//g.drawRect((int) player.x - (Platform.WIDTH) / 2 + 10, (int) player.y
		//		- (Platform.HEIGHT) / 2 + 26, Platform.WIDTH - 4,
		//		Platform.HEIGHT - 29);
		player.render(g2);
		pg.render(gg);
		gg.dispose();
	}

	public void update(int delta) {
		player.update(delta);
		for (int m = 0; m < monsterNumber; m++) {
			monsters[m].update(delta);
		}
		// rock.update(delta);
		createLevel();
		shouldRender(delta);
		CollisionDetect(player, delta);
		for (int m = 0; m < monsterNumber; m++) {
			CollisionDetect(monsters[m], delta);
		}
		// CollisionDetect(monster, delta);
	}

	public void createLevel() {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (tiles[x][y].isOnScreen) {
					if (tileGrid[x][y] == Tile.DEFAULT) {
						tiles[x][y] = new Tile(x, y, width, height);
					} else if (tileGrid[x][y] == Tile.SNOW) {
						tiles[x][y] = new Snow(x, y, width, height);
					} else if (tileGrid[x][y] == Tile.DIRT) {
						tiles[x][y] = new Dirt(x, y, width, height);
					} else
						tiles[x][y] = new Tile(x, y, width, height);
				}
			}
		}
	}

	public void shouldRender(int delta) {
		Rectangle r1 = new Rectangle(
				(int) player.x - (Platform.WIDTH) / 2 + 10, (int) player.y
						- (Platform.HEIGHT) / 2 + 26, Platform.WIDTH - 4,
				Platform.HEIGHT - 29);
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
		for (int m = 0; m < monsterNumber; m++) {
			r2 = monsters[m].getBounds();
			if (r1.intersects(r2)) {
				monsters[m].shouldRender();
			} else
				monsters[m].shouldNotRender();
		}

	}

	public void CollisionDetect(Entity e, int delta) {
		Rectangle r1;
		Rectangle r2;
		Polygon p;
		r1 = player.getBounds();
		p = player.lg.getFiringBounds();
		/*
		 * r2 = rock.getBounds(); if (r1.intersects(r2)){ rock.collision(player,
		 * delta); } else player.fixMovement();
		 */
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				r2 = tiles[x][y].getBounds();
				if (r1.intersects(r2)) {
					tiles[x][y].collision(e, delta);
				}
			}
		}
		for (int m = 0; m < monsterNumber; m++) {
			r2 = monsters[m].getBounds();
			if (r1.intersects(r2)) {
				monsters[m].collision(player, delta);
			}
		}
		
		for (int m = 0; m < monsterNumber; m++) {
			r2 = monsters[m].getBounds();
			if (p.intersects(r2)) {
				player.lg.collision(monsters[m], delta);
			}
		}
	}
	
	public void respawn(Entity e){
		int x = r.nextInt(width);
		int y = r.nextInt(height);
		if (x == spawnX && y == spawnY){
			respawn(e);
		}
		e.respawn(x, y);
	}
	
}
