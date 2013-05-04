package com.zachnickell.platform.entity.item;

import org.lwjgl.opengl.GL11;

import com.zachnickell.platform.entity.Entity;
import com.zachnickell.platform.entity.Player;
import com.zachnickell.platform.gfx.Sprites;

public class Item extends Entity {
	
	public Player owner = null;
	public String name = "item";
	public boolean item = true;
	public Item(int x, int y){
		sprite = Sprites.item;
		w = 16;
		h = 16;
		this.x = (double) x * 16;
		this.y = (double) y * 16;
		canPickUp = true;
		interacts = false;
	}
	
	public void render(){
		if (shouldRender()) {
			sprite.bind();
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
			GL11.glPopMatrix();
		}
	}

}
