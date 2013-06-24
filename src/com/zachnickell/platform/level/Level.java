package com.zachnickell.platform.level;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Project;

import com.zachnickell.platform.Input;
import com.zachnickell.platform.Platform;
import com.zachnickell.platform.entity.*;
import com.zachnickell.platform.entity.item.Item;
import com.zachnickell.platform.entity.item.weapon.Bullet;
import com.zachnickell.platform.entity.tile.*;
import com.zachnickell.platform.level.creator.LevelCreator;

public class Level {
	// screen size is 20 x 15 @(16x16)size tiles
	// 50 x 50 map is highest efficient running size so far 3/24/13
	// 256 x 256 map at 56% cpu and ~30 fps 3/25/13
	// 175 x 175 map running smoothly at 4% cpu and ~60 fps 3/25/13 2:35PM

	Random r = new Random();
	int width;// = 25;
	int height;// = 25;
	int spawnX;// = width / 2;
	int spawnY;// = height / 2;
	public static Player player;
	static PlayerGui pg;
	int[][] tileGrid;// = new int[width][height];
	Portal portal;
	public static ArrayList<Entity> entities;
	ArrayList<Tile> tiles;

	public Level(LevelCreator levelcreator) {
		player = levelcreator.getPlayer();
		pg = new PlayerGui(player);

		tiles = levelcreator.getTiles();
		entities = levelcreator.getEntities();

	}

	public void render() {
		GL11.glPushMatrix();
		GL11.glTranslated(-player.x + Platform.WIDTH / 2 - 10, -player.y
				+ Platform.HEIGHT / 2 - 10, 0);
		for (int t = 0; t < tiles.size(); t++) {
			tiles.get(t).render();
		}
		for (int e = 0; e < entities.size(); e++) {
			entities.get(e).render();
		}
		// portal.render();
		// drenderMonsters();
		player.render();
		GL11.glPopMatrix();
		pg.render();
		// GL11.glTranslated(0, 0, 0);
	}

	public void renderMonsters() {
		ArrayList<Monster> mons = portal.getMonsters();
		for (int m = 0; m < mons.size(); m++) {
			Monster monster = mons.get(m);
			monster.render();
		}
	}

	public void update(int delta) {
		player.update(delta);
		for (int e = 0; e < entities.size(); e++) {
			Entity entity = entities.get(e);
			if (!entity.isAlive()) {
				entity.die();
				entities.remove(entity);
				break;
			}
			entity.update(delta);
		}
		bulletCollision();
		entityCollision();

	}

	public void updateMonsters(int delta) {
		ArrayList<Monster> mons = portal.getMonsters();
		for (int m = 0; m < mons.size(); m++) {
			Monster monster = mons.get(m);
			monster.update(delta);
		}
	}

	public Rectangle renderZone() {
		Rectangle r1 = new Rectangle((int) player.x + player.w / 2
				- (Platform.WIDTH) / 2 - 2, (int) player.y + player.h / 2
				- (Platform.HEIGHT) / 2 + 14, Platform.WIDTH,
				Platform.HEIGHT - 16);
		return r1;

	}

	public void bulletCollision() {
		ArrayList<Bullet> bullets = player.p.getBullets();
		for (int b = 0; b < bullets.size(); b++) {
			Bullet bullet = (Bullet) bullets.get(b);

			Line2D p1 = bullet.getBounds();

			/*
			 * Rectangle r = portal.getBounds(); if (p1.intersects(r)){
			 * portal.doesDamage(bullet.damage); bullets.remove(b); break; }
			 */
			for (int m = 0; m < entities.size(); m++) {
				Entity entity = entities.get(m);
				if (entity.interacts) {
					Rectangle r1 = entity.getBounds();

					if (p1.intersects(r1)) {
						entity.doesDamage(bullet.damage);
						bullets.remove(b);
						return;
					}
				}
			}
			for (int t = 0; t < tiles.size(); t++) {
				Tile tile = tiles.get(t);
				if (tile.isSolid) {
					Rectangle r1 = tile.getBounds();
					if (p1.intersects(r1)) {
						bullets.remove(b);									//fix this.. sometimes crashes..... maybe fixed?
						break;
					}
				}
			}
		}
	}

	public void entityCollision() {

		for (int e = 0; e < entities.size(); e++) {
			Entity entity = entities.get(e);
			if (entity.canPickUp) {
				Rectangle r1 = entity.getBounds();
				Rectangle r2 = player.getBounds();
				if (r2.intersects(r1)) {
					player.inventory.add((Item) entity);
					entities.remove(e);
					break;
				}
			}
			if (entity.interacts) {
				Rectangle r1 = entity.getBounds();
				Rectangle r2 = player.getBounds();
				if (r2.intersects(r1)) {
					System.out.println(1);
					player.doesDamage(entity.damage);
				}

			}
		}
	}

	public boolean isFree(Entity e, int dx, int dy) {
		Tile tile;
		Rectangle r1, newR1, r2;
		r1 = e.getBounds();
		r1.setBounds(r1.x + dx, r1.y + dy, r1.width, r1.height);
		for (int t = 0; t < tiles.size(); t++) {
			tile = tiles.get(t);
			if (tile.isSolid) {
				r2 = tile.getBounds();
				if (r1.intersects(r2)) {

					return false;
				}
			}
		}
		return true;
	}

	public boolean isInteracting(int x, int y) {

		return false;
	}

	public void respawn(Entity e) {
		int x = r.nextInt(width);
		int y = r.nextInt(height);
		if (x == spawnX && y == spawnY) {
			respawn(e);
		}
		e.respawn(x, y);
	}

}
