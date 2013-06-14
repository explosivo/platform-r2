package com.zachnickell.platform.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.zachnickell.platform.entity.item.Item;
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
	//Level level;
	Portal portal;
	boolean killed;
	boolean xpAwarded = false;
	double dx, dy;
	int currentw;
	int currenth;
	

	public Monster(int spawnX, int spawnY, Player player,  Portal portal) {
		
		damage = 1;
		invincable = false;
		ID = totalMonsters;
		totalMonsters++;
		maxSpeed = .15;
		c = Color.BLUE;
		w = 24;
		h = 24;
		speed = 0.50;
		isSolid = true;
		this.x = (double) spawnX * 16;
		this.y = (double) spawnY * 16;
		maxHealth = 5;
		health = maxHealth;
		this.player = player;
		sprite = Sprites.zombie;
	}

	public void render(){
		if (shouldRender() && isAlive()) {
			sprite.bind();
			GL11.glPushMatrix();
			GL11.glTranslated((x + currentw / 2), (y + currentw/2), 0);
			GL11.glRotated(Math.toDegrees(angle), 0, 0, 1);
			GL11.glTranslated(-(x + currentw / 2), -(y + currentw/2), 0);
			GL11.glBegin(GL11.GL_QUADS);
				GL11.glTexCoord2d(0, 0);
				GL11.glVertex2d(x, y);
				GL11.glTexCoord2d(1, 0);
				GL11.glVertex2d(x + currentw, y);
				GL11.glTexCoord2d(1, 1);
				GL11.glVertex2d(x + currentw, y + currenth);
				GL11.glTexCoord2d(0, 1);
				GL11.glVertex2d(x, y + currenth);
			GL11.glEnd();
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			if (isHurt()){
				GL11.glColor3d(0, 0, 0);
				GL11.glBegin(GL11.GL_QUADS);
				GL11.glVertex2d(x, y - 3);
				GL11.glVertex2d(x - 1 + w, y - 3);
				GL11.glVertex2d(x + w, y - 3 - 3);
				GL11.glVertex2d(x - 1, y - 3 - 3);
				GL11.glEnd();
				GL11.glColor3d(0, 1, 0);
				GL11.glBegin(GL11.GL_QUADS);
				GL11.glVertex2d(x, y - 3);
				GL11.glVertex2d(x - 1 + (health * w /maxHealth) + 2, y - 3);
				GL11.glVertex2d(x + (health * w /maxHealth) + 2, y - 3 - 3);
				GL11.glVertex2d(x - 1, y - 3 - 3);
				GL11.glEnd();
			}
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glColor3d(1, 1, 1);
			GL11.glPopMatrix();
		}
	}

	public void update(int delta) {
		if (isAlive()) {
			if (currentw < w){
				currentw ++;
			}
			if (currenth < h){
				currenth ++;
			}
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
				if (System.currentTimeMillis() - lastTime >= 0) {
					invincable = false;
				}
			} else
				sprite = Sprites.zombie;// c = Color.red;
		}
		if (!isAlive()){
			if (!killed){
				killed = true;
				x = -1;
				y = -1;
			}
			if (killed && !xpAwarded){
				player.xp += 2;
				player.kills ++;
				killed = false;
				xpAwarded = true;
			}
			if (System.currentTimeMillis() - lastAlive > 5000){
				portal.respawn(this);
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
	
	public void die(){
		Level.entities.add(new Item((int)x/16, (int)y/16, Item.POWER));
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
