package com.zachnickell.platform.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Entity {
	public double x, y;
	public int w, h;
	public double speed;
	public double maxSpeed;
	public int maxHealth = 1;
	public int health = maxHealth;
	public int damage;
	public boolean invincable = false;
	public Color c;
	public boolean isSolid = false;
	public boolean isMovable = false;
	public boolean alive = true;
	public int direction = 0;
	public static final int IDLE = 0;
	public static final int UP = 1;
	public static final int DOWN = 2;
	public static final int LEFT = 3;
	public static final int RIGHT = 4;
	public boolean canMoveUp = true;
	public boolean canMoveDown = true;
	public boolean canMoveLeft = true;
	public boolean canMoveRight = true;
	public int level;
	public int giveXP;
	public boolean isOnScreen = true;

	long lastTime;

	public void render(Graphics g) {
		if (isOnScreen) {
			g.setColor(c);
			g.fillRect((int) x, (int) y, w, h);
		}
	}

	public void shouldRender() {
		isOnScreen = true;
	}

	public void shouldNotRender() {
		isOnScreen = false;
	}

	public void update(int delta) {

	}

	public boolean isAlive() {
		if (health <= 0)
			alive = false;
		if (health > 0)
			alive = true;
		return alive;
	}

	public void collision(Entity e, int delta) {
		if (isOnScreen) {
			if (isSolid && e.isSolid) {
				if (e.direction == UP) {
					// e.canMoveUp = false;
					e.y = y + h;
					if (isMovable) {
						y -= e.speed * delta;
					}
				} else if (e.direction == DOWN) {
					// e.canMoveDown = false;
					e.y = y - e.h;
					if (isMovable) {
						y += e.speed * delta;
					}
				} else if (e.direction == LEFT) {
					// e.canMoveLeft = false;
					e.x = x + w;
					if (isMovable) {
						x -= e.speed * delta;
					}
				} else if (e.direction == RIGHT) {
					// e.canMoveRight = false;
					e.x = x - e.w;
					if (isMovable) {
						x += e.speed * delta;
					}
				}
			}
		}
	}

	public void doesDamage(int damage) {
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, w, h);
	}

}