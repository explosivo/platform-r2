package com.zachnickell.platform.entity.item.weapon;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.geom.Line2D;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import com.zachnickell.platform.Input;
import com.zachnickell.platform.entity.Entity;
import com.zachnickell.platform.gfx.Sprites;

public class Bullet {

	double x, y, xx, yy;
	double angle;
	public int damage = 1;
	Texture sprite;
	double w, h;

	public Bullet(int x, int y, double angle) {
		this.x = x;
		this.y = y;
		this.angle = -angle;
		sprite = Sprites.bullet;
		w = 2;
		h = 6;
		xx = (x + 5 * Math.sin(angle - Math.PI));
		yy = (y + 5 * Math.cos(angle));
	}

	public void render() {
		GL11.glPushMatrix();
		GL11.glTranslated(0, 0, 0);
		GL11.glTranslated((x + w / 2), (y + w/2), 0);
		GL11.glRotated(Math.toDegrees(-angle), 0, 0, 1);
		GL11.glTranslated(-(x + w / 2), -(y + w/2), 0);
		sprite.bind();
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
		GL11.glColor3f(1, 1, 1);
	}

	public void update(int delta) {

		movement(delta);

	}

	public void movement(int delta) {
		// System.out.println(Math.toDegrees(angle));
		double dx, dy, dxx, dyy;

		dx = Math.sin(angle) * delta * 0.5;
		dy = Math.cos(angle) * delta * 0.5 + 1;
		dxx = Math.sin(angle) * delta * 0.5;
		dyy = Math.cos(angle) * delta * 0.5 + 1;

		x += dx;
		y += dy;
		xx += dxx;
		yy += dyy;


	}

	public Line2D getBounds() {
		Line2D.Double l = new Line2D.Double(x, y, xx, yy);
		return l;
	}

}
