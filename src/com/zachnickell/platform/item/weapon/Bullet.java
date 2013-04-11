package com.zachnickell.platform.item.weapon;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import org.lwjgl.opengl.GL11;

import com.zachnickell.platform.Input;

public class Bullet {

	double x, y, xx, yy;
	double angle;
	
	public Bullet (int x, int y, double angle){
		this.x = x;
		this.y = y;
		this.angle = angle;
		xx = (x + 5 * Math.sin(angle - Math.PI));
		yy = (y + 5 * Math.cos(angle)) ;
	}
	
	public void render(){//Graphics g){
		//g.setColor(Color.yellow);
		//g.drawLine(x, y, xx, yy);
		GL11.glPushMatrix();
		GL11.glTranslated(0, 0, 0);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		//GL11.glLineWidth(100f);
		GL11.glBegin(GL11.GL_LINES);
			GL11.glColor3f(1, 0, 0);
			GL11.glVertex2d(x, y);
			GL11.glVertex2d(xx, yy);
		GL11.glEnd();
		GL11.glPopMatrix();
		GL11.glColor3f(1, 1, 1);
	}
	
	public void update (int delta){
		x += Math.sin(angle - Math.PI) * delta;
		y += Math.cos(angle) * delta;
		xx += Math.sin(angle - Math.PI) * delta;
		yy += Math.cos(angle) * delta;
	}
	
	public Polygon getFiringBounds() {
		Polygon p = new Polygon();
		p.addPoint((int)x, (int)y);
		p.addPoint((int)xx, (int)yy);
		return p;
	}
	
}
