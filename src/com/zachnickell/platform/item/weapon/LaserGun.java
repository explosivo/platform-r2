package com.zachnickell.platform.item.weapon;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import com.zachnickell.platform.Input;
import com.zachnickell.platform.entity.Entity;

public class LaserGun extends Weapon {
	double x;
	double y;

	public LaserGun(Entity owner) {
		super(owner);
	}

	public void render(Graphics g) {
		if (firing) {
			g.setColor(Color.red);
			// g.drawLine((int) owner.x + owner.w / 2,
			// (int) owner.y + owner.h / 2, Input.x + (int) owner.x - 320
			// / 2 + owner.w / 2, Input.y + (int) owner.y - 240
			// / 2 + owner.h / 2);
			g.drawPolygon(getFiringBounds());
		}
	}

	public Polygon getFiringBounds() {
		Polygon p = new Polygon();
		p.addPoint((int) owner.x + owner.w / 2, (int) owner.y + owner.h / 2);
		p.addPoint(Input.x + (int) owner.x - 320 / 2 + owner.w / 2, Input.y
				+ (int) owner.y - 240 / 2 + owner.h / 2);
		return p;
	}

	public void fire() {
		if (owner.stamina > 0) {
			super.fire();
		} else ceaseFire();
		// System.out.println("Firing!");
		// x = -Math.sin(fireAngle);
		// y = Math.cos(fireAngle) * 320;
		// System.out.println(y);
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
