package com.zachnickell.platform.entity;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import com.zachnickell.platform.Input;
import com.zachnickell.platform.Platform;
import com.zachnickell.platform.Sounds;
import com.zachnickell.platform.entity.item.Inventory;
import com.zachnickell.platform.entity.item.Item;
import com.zachnickell.platform.entity.item.weapon.Pistol;
import com.zachnickell.platform.entity.item.weapon.RocketLauncher;
import com.zachnickell.platform.entity.item.weapon.Weapon;
import com.zachnickell.platform.gfx.Sprites;

public class Player extends ControllableEntity {

	// public int kills = 0;
	// public int xp = 0;
	// public int maxXP = 100;
	// public double angle = 0;
	//public LaserGun lg;
	//public Pistol p;
	public Weapon p;
	int time;
	public Inventory inventory;
	
	public Player(int spawnX, int spawnY) {
		w = 24;
		h = 24;
		maxXP = 100;
		maxSpeed = .15;
		maxStamina = 100;
		stamina = maxStamina;
		time = 0;
		//sprite = Sprites.playerAnim.get(0);
		maxSpeed = 0.20;
		speed = 0.08;
		isSolid = true;
		x = (double) spawnX * 16;
		y = (double) spawnY * 16;
		maxHealth = 10;
		health = maxHealth;
		level = 1;
		//p = new Pistol(this);
		p = new Pistol(this);
		inventory = new Inventory(this);
	}

	public void render(){
		if (shouldRender() && isAlive()) {
			sprite = Sprites.playerAnim.get(0);
			sprite = Sprites.playerAnim.get(time);
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
			p.render();
		}
	}
	int tick;
	public void update(int delta) {
		if (Input.downPressed || Input.upPressed || Input.leftPressed || Input.rightPressed){
		tick += delta;
		}
		if (tick > 100){
		time++;
		tick = 0;
		}
		if (health > maxHealth) health = maxHealth;
		if (stamina> maxStamina) stamina = maxStamina;
		if (time > 3) time = 0;
		if (isAlive()) {
			angle = -Math.atan2((y + h / 2) - (Input.y + y - 240 / 2 + h / 2),
					(x + w / 2) - (Input.x + x - 320 / 2 + w / 2))
					- 3
					* Math.PI / 2;
			// System.out.println(angle);
			// lg.getFireAngle();
			p.playerUpdate(delta);
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
				//stamina = maxStamina;
				//health = maxHealth;
				if (speed < maxSpeed) {
					speed += .005;
				}
				maxXP += 2 * level;
			}
			if (invincable) {
				// xp++;
				// c = Color.white;
				sprite = Sprites.playerHurt;
				if (System.currentTimeMillis() - lastTime >= 350) {
					invincable = false;
				}
			} else
				sprite = Sprites.player;// c = Color.red;
		}
		if (!isAlive()){
			if (Platform.running)
				Sounds.dead.play();
			Platform.gameOver();
		}
	}

	public void doesDamage(int damage) {
		if (damage > 0 && !invincable) {
			if(isAlive())
				Sounds.hurt.play();
			lastTime = System.currentTimeMillis();
			health -= damage;
			//System.out.println(health);
			invincable = true;
		} else
			return;

	}
}
