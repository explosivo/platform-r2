package com.zachnickell.platform.entity;

import java.awt.Color;
import java.awt.Graphics;

import com.zachnickell.platform.Input;
import com.zachnickell.platform.Platform;

public class Player extends ControllableEntity{
	
	public int xp = 5;
	public int maxXP = 100;
	
	public Player(int spawnX, int spawnY){
		maxSpeed = .15;
		speed = 0;
		c = Color.red;
		w = 10;
		h = 10;
		speed = 0.08;
		isSolid = true;
		this.x = (double)spawnX*16;
		this.y = (double)spawnY*16;
		maxHealth = 3;
		health = maxHealth;
		level = 1;
		System.out.println(health);
	}
	
	public void update(int delta){
		if (isAlive()){
			move(delta);
			if (xp >= maxXP){
				xp -= maxXP;
				level++;
			}
			if (invincable){
				xp++;
				c = Color.white;
				if (System.currentTimeMillis() - lastTime >= 2000){
					invincable = false;
				}
			} else c = Color.red;
		}
		if(!isAlive())
			Platform.gameOver();
	}
	public void doesDamage(int damage){
		if(damage > 0 && !invincable){
			lastTime = System.currentTimeMillis();
			health -= damage;
			System.out.println(health);
			invincable = true;
		}
		else return;
		
	}
}
