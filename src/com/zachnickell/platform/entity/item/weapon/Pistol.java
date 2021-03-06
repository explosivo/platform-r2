package com.zachnickell.platform.entity.item.weapon;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;

import com.zachnickell.platform.Input;
import com.zachnickell.platform.Sounds;
import com.zachnickell.platform.entity.Entity;
import com.zachnickell.platform.level.Level;

public class Pistol extends Weapon {

	public ArrayList<Bullet> bullet = new ArrayList<Bullet>();

	double x, y;
	Level level;

	public Pistol(Entity owner) {
		super(owner);
		level = owner.lvl;
		// TODO Auto-generated constructor stub
	}

	public void render(){//Graphics g) {
		// if (firing) {
		// g.setColor(Color.red);
		// g.drawLine((int) owner.x + owner.w / 2,
		// (int) owner.y + owner.h / 2, Input.x + (int) owner.x - 320
		// / 2 + owner.w / 2, Input.y + (int) owner.y - 240
		// / 2 + owner.h / 2);
		// g.drawPolygon(getFiringBounds());

		// }
		for (int i = 0; i < bullet.size(); i++) {
			bullet.get(i).render();
		}
	}

	public void update(int delta) {
		if (!canFire)
			tick += delta;
		for (int i = 0; i < bullet.size(); i++) {
			//bullet.get(i).update(delta);
			if (!bullet.get(i).getBounds().intersects(Entity.lvl.renderZone())){
				bullet.remove(i);
			}
		}	
	}
	
	public void playerUpdate(int delta){
		if (!canFire)
			tick += delta;
		for (int i = 0; i < bullet.size(); i++) {
			bullet.get(i).update(delta);
			if (!bullet.get(i).getBounds().intersects(Entity.lvl.renderZone())){
				bullet.remove(i);
			}
		}	
	}


	public void fire() {
		if (canFire){
			Sounds.shoot.play();
			bullet.add(new Bullet((int) owner.x+owner.w/2, (int) owner.y+owner.h/2, owner.angle));
			canFire = false;
		}
	}

	public void ceaseFire() {
		canFire = true;
	}
	
	public ArrayList<Bullet> getBullets(){
		return bullet;
	}

	public void collision(Entity e, int delta) {
		if (firing) {
			// System.out.println("HIT!");
			e.doesDamage(1);

		}
	}

}
