package com.zachnickell.platform.entity;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

import com.zachnickell.platform.Input;
import com.zachnickell.platform.Platform;
import com.zachnickell.platform.gfx.Sprites;
import com.zachnickell.platform.item.weapon.LaserGun;
import com.zachnickell.platform.item.weapon.Pistol;

public class Player extends ControllableEntity {

	// public int kills = 0;
	// public int xp = 0;
	// public int maxXP = 100;
	// public double angle = 0;
	//public LaserGun lg;
	public Pistol p;

	public Player(int spawnX, int spawnY) {
		w = 24;
		h = 24;
		maxXP = 100;
		//sprite = new BufferedImage(w, h, BufferedImage.TRANSLUCENT);
		maxSpeed = .15;
		maxStamina = 3;
		stamina = maxStamina;
		// c = Color.red;
		sprite = Sprites.player;
		maxSpeed = 0.20;
		speed = 0.08;
		isSolid = true;
		x = (double) spawnX * 16;
		y = (double) spawnY * 16;
		maxHealth = 10;
		health = maxHealth;
		level = 1;
		//System.out.println(health);
		//lg = new LaserGun(this);
		p = new Pistol(this);
	}

	public void render(){//Graphics g) {
		if (isOnScreen) {
			//Graphics2D gg = (Graphics2D) g.create();
			//gg.rotate(angle, x + w / 2, y + h / 2);
			//gg.drawImage(sprite, (int) x, (int) y, w, h, null);
			//p.render(g);
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
			p.render();
			GL11.glPopMatrix();
		}
	}

	public void update(int delta) {
		if (isAlive()) {
 			xp = maxXP - 1;
			angle = Math.atan2((y + h / 2) - (Input.y + y - 240 / 2 + h / 2),
					(x + w / 2) - (Input.x + x - 320 / 2 + w / 2))
					- 3
					* Math.PI / 2;
			// System.out.println(angle);
			// lg.getFireAngle();
			p.update(delta);
			if (Input.mousePress) {
				decreaseStamina(1);
				//lg.fire();
				p.fire();
				reloadStamina = false;
			}
			if (!Input.mousePress) {
				//lg.ceaseFire();
				p.ceaseFire();
				reloadStamina = true;
			}
			if (reloadStamina) {
				increaseStamina(1);
			}
			isOnScreen = true;
			move(delta);
			if (xp >= maxXP) {
				xp -= maxXP;
				level++;
				maxStamina += level / 3;
				maxHealth += level / 2;
				stamina = maxStamina;
				health = maxHealth;
				if (speed < maxSpeed) {
					speed += .005;
				}
				maxXP += 2 * level;
			}
			if (invincable) {
				// xp++;
				// c = Color.white;
				sprite = Sprites.playerHurt;
				if (System.currentTimeMillis() - lastTime >= 2000) {
					invincable = false;
				}
			} else
				sprite = Sprites.player;// c = Color.red;
		}
		if (!isAlive())
			Platform.gameOver();
	}

	public void doesDamage(int damage) {
		if (damage > 0 && !invincable) {
			lastTime = System.currentTimeMillis();
			health -= damage;
			//System.out.println(health);
			invincable = true;
		} else
			return;

	}
}
