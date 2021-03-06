package com.zachnickell.platform.entity;

import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.zachnickell.platform.gfx.Sprites;
import com.zachnickell.platform.level.Level;

public class Portal extends Entity {

	ArrayList<Monster> monsters;
	int maxMonsters = 10;
	Random random = new Random();
	
	public Portal(int x, int y) {
		time = System.currentTimeMillis();
		monsters = new ArrayList<Monster>();
		//sprite = Sprites.portalAnim.get(frame);
		this.x = x * 16;
		this.y = y * 16;
		w = 56;
		h = 56;
		maxHealth = 5;
		health = maxHealth;
		interacts = false;
		maxFrame = 6;
	}
	
	public void render(){
		if (shouldRender() && isAlive()){
			//System.out.println("mawnstah spaawwwn!");
			getFrame();
			sprite.bind();
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glPushMatrix();
			GL11.glBegin(GL11.GL_QUADS);
				GL11.glTexCoord2d(0, 0);
				GL11.glVertex2d(x, y);
				GL11.glTexCoord2d(1, 0);
				GL11.glVertex2d(x+w, y);
				GL11.glTexCoord2d(1, 1);
				GL11.glVertex2d(x+w, y+h);
				GL11.glTexCoord2d(0, 1);
				GL11.glVertex2d(x, y+h);
			GL11.glEnd();
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			if (isHurt()){
				GL11.glColor3d(0, 0, 0);
				GL11.glBegin(GL11.GL_QUADS);
				GL11.glVertex2d(x, y + h + 3);
				GL11.glVertex2d(x + w, y + h + 3);
				GL11.glVertex2d(x + w, y + h + 3 + 3);
				GL11.glVertex2d(x, y + h + 3 + 3);
				GL11.glEnd();
				GL11.glColor3d(0, 1, 0);
				GL11.glBegin(GL11.GL_QUADS);
				GL11.glVertex2d(x, y + h + 3);
				GL11.glVertex2d(x + (health * w /maxHealth) + 2, y + h + 3);
				GL11.glVertex2d(x + (health * w /maxHealth) + 2, y + h + 3 + 3);
				GL11.glVertex2d(x, y + h + 3 + 3);
				GL11.glEnd();
			}
			GL11.glColor3d(1, 1, 1);
			GL11.glPopMatrix();
		}
	}
	
	public void update(int delta){
		if (isAlive() && shouldRender()){
			tick += delta;
			if (tick > 100){
			frame++;
			tick = 0;
			}
			if (frame > maxFrame) frame = 0;
			if (System.currentTimeMillis() - time > 500){
				if (monsters.size() < maxMonsters){
					health --;
					time = System.currentTimeMillis();
					int mx = (int)((x) + (Math.random() * ((((x) + (w)) - x) + 1)));
					int my = (int)((y) + (Math.random() * ((((y) + (h)) - y) + 1)));;
					addMonster(mx, my);
				}
			}
		}
	}
	
	
	
	public void addMonster(int mx, int my){
		Level.entities.add(new Monster((mx)/16, (my)/16, Level.player, this));
	}
	
	public void respawn(Entity e){
		if (isAlive()){
			health--;
			e.respawn((int)x/16, (int)y/16);
		}
	}
	
	public void getFrame(){
		sprite = Sprites.portalAnim.get(frame);
	}
	
	public ArrayList<Monster> getMonsters(){
		return monsters;
	}

}
