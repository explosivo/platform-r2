package com.zachnickell.platform.entity;

import org.lwjgl.opengl.GL11;

import com.zachnickell.platform.Sounds;
import com.zachnickell.platform.gfx.Sprites;

public class Explosion extends Entity {

	double currenth, currentw;
	boolean cooldown = false;
	boolean running = true;

	public Explosion(double x, double y) {
		Sounds.explosion.play();
		sprite = Sprites.explosion;
		this.x = x;
		this.y = y;
		h = 16;
		w = 16;
	}

	public void render() {
		sprite.bind();
		GL11.glPushMatrix();
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
	}

	public void update(int delta) {
		if (running) {
			if (!cooldown) {
				if (currentw < w) {
					currentw += 2;
				}
				if (currenth < h) {
					currenth += 2;
				}
				if (currenth >= 16)
					cooldown = true;
			}
			if (cooldown) {
				currentw -= .5;
				currenth -= .5;
				if (currenth <= 0)
					running = false;
			}
		}
	}
}
