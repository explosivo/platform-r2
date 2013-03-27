package com.zachnickell.platform.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.zachnickell.platform.Input;
import com.zachnickell.platform.Platform;
import com.zachnickell.platform.gfx.Sprites;

public class Player extends ControllableEntity {

	public int kills = 0;
	public int xp = 5;
	public int maxXP = 100;
	double angle = 0;

	public Player(int spawnX, int spawnY) {
		w = 24;
		h = 24;
		sprite = new BufferedImage(w, h, BufferedImage.TRANSLUCENT);
		maxSpeed = .15;
		//c = Color.red;
		sprite = Sprites.player;
		speed = 0.08;
		isSolid = true;
		x = (double) spawnX * 16;
		y = (double) spawnY * 16;
		maxHealth = 10;
		health = maxHealth;
		level = 1;
		System.out.println(health);
	}

	public void render(Graphics g) {
		if (isOnScreen) {
			Graphics2D gg = (Graphics2D) g;
			gg.setColor(Color.green);
			gg.drawLine((int)x + w/2, (int)y + h/2, Input.x + (int) x - 320/2 + w/2, Input.y + (int) y - 240/2 + h/2);
			//gg.setColor(c);
			gg.rotate(angle, x + w / 2, y + h / 2);
			//g.fillRect((int) x, (int) y, w, h);
			g.drawImage(sprite, (int) x, (int) y, w, h, null);
		}
	}

	public void update(int delta) {
		if (isAlive()) {
			angle = Math.atan2( (y + h/2) - (Input.y + y - 240/2 + h/2), (x + w/2) - (Input.x + x - 320/2 + w/2) ) - 3*Math.PI/2;
			//System.out.println(angle);
			isOnScreen = true;
			move(delta);
			if (xp >= maxXP) {
				xp -= maxXP;
				level++;
			}
			if (invincable) {
				// xp++;
				//c = Color.white;
				sprite = Sprites.playerHurt;
				if (System.currentTimeMillis() - lastTime >= 2000) {
					invincable = false;
				}
			} else
				sprite = Sprites.player;//c = Color.red;
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
