package com.zachnickell.platform.entity.item.weapon;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import org.lwjgl.opengl.GL11;

import com.zachnickell.platform.Input;
import com.zachnickell.platform.entity.Entity;

public class Bullet {

	double x, y, xx, yy;
	double angle;
	public int damage = 1;
	
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
		
		movement(delta);
			
	}
	
	public void movement(int delta){
		//System.out.println(Math.toDegrees(angle));
		x += Math.sin(angle - Math.PI) * delta * 0.5;
		y += Math.cos(angle) * delta * 0.5;
		xx += Math.sin(angle - Math.PI) * delta * 0.5;
		yy += Math.cos(angle) * delta * 0.5;
	}
	
	public Polygon getBounds() {
		Polygon p = new Polygon();
		p.addPoint((int)x, (int)y);
		p.addPoint((int)xx, (int)yy);
		return p;
	}
	
}
