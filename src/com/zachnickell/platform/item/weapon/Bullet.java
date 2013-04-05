package com.zachnickell.platform.item.weapon;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

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
	
	public void render(Graphics g){
		g.setColor(Color.yellow);
		g.drawLine(x, y, xx, yy);
	}
	
	public void update (int delta){
		x += Math.sin(angle - Math.PI) * delta;
		y += Math.cos(angle) * delta;
		xx += Math.sin(angle - Math.PI) * delta;
		yy += Math.cos(angle) * delta;
	}
	
	public Polygon getFiringBounds() {
		Polygon p = new Polygon();
		p.addPoint(x, y);
		p.addPoint(xx, yy);
		return p;
	}
	
}
