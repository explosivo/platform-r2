package com.zachnickell.platform.entity.item.weapon;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import com.zachnickell.platform.gfx.Sprites;

public class Rocket extends Bullet {

	Texture sprite;
	double w, h;
	double x1, x2, x3, x4, y1, y2, y3, y4;
	double originx, originy;
	
	public Rocket(int x, int y, double angle) {
		super(x, y, angle);
		originx = x;
		originy = y;
		sprite = Sprites.rocket;
		w = 8;
		h = 16;
		damage = 3;
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
	
	
	public void movement(int delta) {
		// System.out.println(Math.toDegrees(angle));
		double dx, dy, dxx, dyy;

		dx = Math.sin(angle) * delta * .175;
		dy = Math.cos(angle) * delta * .175;
	
		x += dx;
		y += dy;



	}

}
