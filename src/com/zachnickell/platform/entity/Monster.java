package com.zachnickell.platform.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.zachnickell.platform.gfx.Sprites;
import com.zachnickell.platform.level.Level;

public class Monster extends Entity {
	Random r = new Random();
	Player player;
	double angle;
	boolean following = false;
	Monster[] monsters;
	public static int totalMonsters;
	public int ID;
	int dec = 0;
	long lastAlive;
	Level level;
	boolean killed;
	boolean xpAwarded = false;
	double dx, dy;

	public Monster(int spawnX, int spawnY, Player player, Monster[] monsters, Level level) {

		this.level = level;
		invincable = false;
		ID = totalMonsters;
		totalMonsters++;
		this.monsters = monsters;
		maxSpeed = .15;
		c = Color.BLUE;
		w = 24;
		h = 24;
		speed = 0.50;
		isSolid = false;
		this.x = (double) spawnX * 16;
		this.y = (double) spawnY * 16;
		maxHealth = 2;
		health = maxHealth;
		this.player = player;
		sprite = Sprites.zombie;
	}

	public void render(){//Graphics g) {
		if (shouldRender() && isAlive()) {
			//Graphics2D gg = (Graphics2D) g.create();
			// gg.setColor(c);
			//gg.rotate(angle, x + w / 2, y + h / 2);
			//gg.drawImage(sprite, (int) x, (int) y, w, h, null);
			// gg.fillRect((int) x, (int) y, w, h);
			// gg.setColor(Color.red);
			// gg.drawLine((int) x + w / 2, (int) y + h / 2, (int) x + w / 2,
			// (int) y + h / 2 + 20);
			sprite.bind();
			GL11.glPushMatrix();
			GL11.glTranslated((x + w / 2), (y + w/2), 0);
			GL11.glRotated(Math.toDegrees(angle), 0, 0, 1);
			GL11.glTranslated(-(x + w / 2), -(y + w/2), 0);
			GL11.glBegin(GL11.GL_QUADS);
				GL11.glTexCoord2d(0, 0);
				GL11.glVertex2d(x, y);
				GL11.glTexCoord2d(1, 0);
				GL11.glVertex2d(x + w, y);
				GL11.glTexCoord2d(1, 1);
				GL11.glVertex2d(x + w, y + h);
				GL11.glTexCoord2d(0, 1);
				GL11.glVertex2d(x, y + h);
			GL11.glEnd();
			GL11.glPopMatrix();
		}
	}

	public void update(int delta) {
		if (isAlive()) {
			lastAlive = System.currentTimeMillis();
			if (!invincable) {
				time += delta;
				if (time > 1000) {
					dec = (r.nextInt(120));
					if (dec > 50) {
						following = true;
					} else
						following = false;
					time = 0;
				}
				if (dec > 10 && dec < 20) {
					dx = -Math.sin(angle) * speed;
					moveCheck(dx,0);
				}
				if (dec > 20 && dec < 30) {
					dx = Math.sin(angle) * speed;
					moveCheck(dx,0);
				}
				if (dec > 30 && dec < 40) {
					dy = Math.cos(angle) * speed;
					moveCheck(0,dy);
				}
				if (dec > 40 && dec < 50) {
					dy = -Math.cos(angle) * speed;
					moveCheck(0,dy);
				}
				moveTowards();
			}
			if (invincable) {
				// xp++;
				// c = Color.white;
				sprite = Sprites.zombieHurt;
				if (System.currentTimeMillis() - lastTime >= 500) {
					invincable = false;
				}
			} else
				sprite = Sprites.zombie;// c = Color.red;
		}
		if (!isAlive()){
			if (!killed){
				killed = true;
			}
			if (killed && !xpAwarded){
				player.xp += 2;
				player.kills ++;
				killed = false;
				xpAwarded = true;
			}
			if (System.currentTimeMillis() - lastAlive > 5000){
				level.respawn(this);
				xpAwarded = false;
			}
		}
	}

	public void moveTowards() {
		angle = Math.atan2((y + h / 2) - (player.y + h / 2), (x + w / 2)
				- (player.x + w / 2))
				+ Math.PI / 2;
		
		if (following) {
			dx = -Math.sin(angle) * speed;
			dy = Math.cos(angle) * speed;
		}
		moveCheck(dx,dy);
	}

	public void collision(Entity e, int delta) {
		if (isAlive())
			e.doesDamage(1);
	}

	public void doesDamage(int damage) {
		if (damage > 0 && !invincable) {
			lastTime = System.currentTimeMillis();
			health -= damage;
			// System.out.println(health);
			invincable = true;
		} else
			return;

	}

}
