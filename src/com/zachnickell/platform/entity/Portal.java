package com.zachnickell.platform.entity;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import com.zachnickell.platform.gfx.Sprites;

public class Portal extends Entity {

	ArrayList monsters;
	
	public Portal(int x, int y) {
		sprite = Sprites.portal;
		this.x = x * 16;
		this.y = y * 16;
		w = 16;
		h = 16;
	}
	
	public void render(){
		if (shouldRender()){
			//System.out.println("mawnstah spaawwwn!");
			sprite.bind();
			//GL11.glEnable(GL11.GL_TEXTURE_2D);
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
