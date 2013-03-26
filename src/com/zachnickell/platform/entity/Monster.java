package com.zachnickell.platform.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import com.zachnickell.platform.Input;

public class Monster extends Entity {
	Random r = new Random();
	Player player;
	double angle;
	boolean following = false;
	Monster[] monsters;
	public static int totalMonsters;
	public int ID;
	int dec = 0;
	
	public Monster(int spawnX, int spawnY, Player player, Monster[] monsters) {
		
		ID = totalMonsters;
		totalMonsters++;
		this.monsters = monsters;
		maxSpeed = .15;
		speed = 0;
		c = Color.BLUE;
		w = 16;
		h = 16;
		speed = 0.50;
		isSolid = true;
		this.x = (double) spawnX * 16;
		this.y = (double) spawnY * 16;
		maxHealth = 2;
		health = maxHealth;
		this.player = player;
	}

	public void render(Graphics g) {
		if (isOnScreen) {
			Graphics2D gg = (Graphics2D) g.create();
			gg.setColor(c);
			gg.rotate(angle, x + w / 2, y + h / 2);
			gg.fillRect((int) x, (int) y, w, h);
			gg.setColor(Color.red);
			gg.drawLine((int) x + w / 2, (int) y + h / 2, (int) x + w / 2,
					(int) y + h / 2 + 20);
		}
	}

	public void update(int delta) {
		time += delta;
		if (time > 1000) {
			dec = (r.nextInt(120));
			if (dec > 50) {
				following = true;
			} else
				following = false;
			time = 0;
		}
		if (dec > 10 && dec < 20){
			x += -Math.sin(angle) * speed;
		}
		if (dec > 20 && dec < 30){
			x += Math.sin(angle) * speed;
		}
		if (dec > 30 && dec < 40){
			y += Math.cos(angle) * speed;
		}
		if (dec > 40 && dec < 50){
			y += -Math.cos(angle) * speed;
		}
		moveTowards();
	}

	public void moveTowards() {
		angle = Math.atan2((y + h / 2) - (player.y + h / 2), (x + w / 2)
				- (player.x + w / 2))
				+ Math.PI / 2;
		if (following) {
			x += -Math.sin(angle) * speed;
			y += Math.cos(angle) * speed;
		}
	}
}
