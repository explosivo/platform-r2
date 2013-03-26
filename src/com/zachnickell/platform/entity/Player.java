package com.zachnickell.platform.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.zachnickell.platform.Input;
import com.zachnickell.platform.Platform;

public class Player extends ControllableEntity {

	public int kills = 0;
	public int xp = 5;
	public int maxXP = 100;
	double angle = 0;

	public Player(int spawnX, int spawnY) {
		maxSpeed = .15;
		speed = 0;
		c = Color.red;
		w = 16;
		h = 16;
		speed = 0.08;
		isSolid = true;
		this.x = (double) spawnX * 16;
		this.y = (double) spawnY * 16;
		maxHealth = 3;
		health = maxHealth;
		level = 1;
		System.out.println(health);
	}

	public void render(Graphics g) {
		if (isOnScreen) {
			Graphics2D gg = (Graphics2D) g;
			gg.setColor(Color.green);
			gg.drawLine((int)x + w/2, (int)y + h/2, Input.x + (int) x - 320/2 + w/2, Input.y + (int) y - 240/2 + h/2);
			gg.setColor(c);
			gg.rotate(angle, x + w / 2, y + h / 2);
			g.fillRect((int) x, (int) y, w, h);
		}
	}

	public void update(int delta) {
		if (isAlive()) {
			angle = Math.atan2( (y + h/2) - (Input.y + y - 240/2 + h/2), (x + w/2) - (Input.x + x - 320/2 + w/2) ) - Math.PI / 2;
			//System.out.println(angle);
			isOnScreen = true;
			move(delta);
			if (xp >= maxXP) {
				xp -= maxXP;
				level++;
			}
			if (invincable) {
				// xp++;
				c = Color.white;
				if (System.currentTimeMillis() - lastTime >= 2000) {
					invincable = false;
				}
			} else
				c = Color.red;
		}
		if (!isAlive())
			Platform.gameOver();
	}

	public void doesDamage(int damage) {
		if (damage > 0 && !invincable) {
			lastTime = System.currentTimeMillis();
			health -= damage;
			System.out.println(health);
			invincable = true;
		} else
			return;

	}
}
