package com.zachnickell.platform.item.weapon;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;

import com.zachnickell.platform.Input;
import com.zachnickell.platform.entity.Entity;

public class Pistol extends Weapon {

	public ArrayList<Bullet> bullet = new ArrayList<Bullet>();

	double x, y;

	public Pistol(Entity owner) {
		super(owner);
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
		for (int i = 0; i < bullet.size(); i++) {
			bullet.get(i).update(delta);

		}
	}


	public void fire() {
		if (owner.stamina > 0) {
			super.fire();
			bullet.add(new Bullet((int) owner.x+owner.w/2, (int) owner.y+owner.h/2, owner.angle));
		} else
			ceaseFire();
	}

	public void ceaseFire() {
		if (firing) {
			super.ceaseFire();
			x = 0;
			y = 0;
			// System.out.println("firing stopped.");
		}
	}

	public void collision(Entity e, int delta) {
		if (firing) {
			// System.out.println("HIT!");
			e.doesDamage(1);

		}
	}

}
