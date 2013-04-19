package com.zachnickell.platform.entity.tile;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.*;
import org.newdawn.slick.opengl.Texture;

import com.zachnickell.platform.entity.Entity;
import com.zachnickell.platform.gfx.Sprites;

public class Tile extends Entity {
	int x;
	int y;
	public static int size = 16;
	public Texture sprite = Sprites.defaultSprite;
	public int id;
	public static final int DEFAULT = 0;
	public static final int SNOW = 1;
	public static final int DIRT = 2;
	//int w = size;
	//int h = size;
	int maxLevelWidth, maxLevelHeight;

	public Tile(int x, int y) {
		//sprite = Sprites.defaultSprite;
		this.x = x;
		this.y = y;
		w = size;
		h = size;
		this.maxLevelWidth = maxLevelWidth;
		this.maxLevelHeight = maxLevelHeight;
	}

	public void render(){//Graphics g) {
		if (shouldRender()) {
			//g.drawImage(sprite, x * size, y * size, null);
			sprite.bind();
			GL11.glBegin(GL11.GL_QUADS);
				GL11.glTexCoord2d(0, 0);
				GL11.glVertex2d(x * size, y * size);
				GL11.glTexCoord2d(1, 0);
				GL11.glVertex2d(x * size + size, y * size);
				GL11.glTexCoord2d(1, 1);
				GL11.glVertex2d(x * size + size, y * size + size);
				GL11.glTexCoord2d(0, 1);
				GL11.glVertex2d(x * size, y * size + size);
			GL11.glEnd();
		} else {

		}
	}

	/*
	 * public void collision(Entity e, int delta){ System.out.printf("%d, %d\n",
	 * x, y); }
	 */

	/*public void collision(Entity e, int delta) {
		if (isOnScreen) {
			if (isSolid && e.isSolid) {
				e.doesDamage(damage);
				if (e.direction == UP) {
					// e.canMoveUp = false;
					e.y = y * size + h;
					if (isMovable) {
						y -= e.speed * delta;
					}
				} else if (e.direction == DOWN) {
					// e.canMoveDown = false;
					e.y = y * size - e.h;
					if (isMovable) {
						y += e.speed * delta;
					}
				} else if (e.direction == LEFT) {
					// e.canMoveLeft = false;
					e.x = x * size + w;
					if (isMovable) {
						x -= e.speed * delta;
					}
				} else if (e.direction == RIGHT) {
					// e.canMoveRight = false;
					e.x = x * size - e.w;
					if (isMovable) {
						x += e.speed * delta;
					}
				}
			}
		}
	}*/

	public Rectangle getBounds() {
		return new Rectangle(this.x * size, this.y * size, size, size);
	}
}
