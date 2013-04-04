package com.zachnickell.platform.entity.tile;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.zachnickell.platform.entity.Entity;
import com.zachnickell.platform.gfx.Sprites;

public class Tile extends Entity {
	int x;
	int y;
	public static int size = 16;
	public BufferedImage sprite = new BufferedImage(size, size,
			BufferedImage.TYPE_INT_RGB);
	public int id;
	public static final int DEFAULT = 0;
	public static final int SNOW = 1;
	public static final int DIRT = 2;
	int w = size;
	int h = size;
	int maxLevelWidth, maxLevelHeight;

	public Tile(int x, int y, int maxLevelWidth, int maxLevelHeight) {
		sprite = Sprites.defaultSprite;
		this.x = x;
		this.y = y;
		this.maxLevelWidth = maxLevelWidth;
		this.maxLevelHeight = maxLevelHeight;
	}

	public void render(Graphics g) {
		if (isOnScreen) {
			g.drawImage(sprite, x * size, y * size, null);
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
