package com.zachnickell.platform.item.weapon;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import org.lwjgl.opengl.GL11;

import com.zachnickell.platform.Input;

public class Bullet {

	int x, y, xx, yy;
	double angle;
	
	public Bullet (int x, int y, double angle){
		this.x = x;
		this.y = y;
		this.angle = angle;
		xx =(int) (x + Math.sin(angle));
		yy =(int) (y + Math.cos(angle)) ;
	}
	
	public void render(){//Graphics g){
		//g.setColor(Color.yellow);
		//g.drawLine(x, y, xx, yy);
		GL11.glPushMatrix();
		GL11.glTranslated(0, 0, 0);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glLineWidth(10);
		GL11.glBegin(GL11.GL_LINES);
			GL11.glColor3f(1, 0, 0);
			GL11.glVertex2i(x, y);
			GL11.glVertex2i(xx, yy);
		GL11.glEnd();
		GL11.glPopMatrix();
		GL11.glColor3f(1, 1, 1);
	}
	
	public void update (int delta){
		x += Math.sin(angle - Math.PI);
		y += Math.cos(angle);
		xx += Math.sin(angle - Math.PI);
		yy += Math.cos(angle);
	}
	
	public Polygon getFiringBounds() {
		Polygon p = new Polygon();
		p.addPoint(x, y);
		p.addPoint(xx, yy);
		return p;
	}
	
}
