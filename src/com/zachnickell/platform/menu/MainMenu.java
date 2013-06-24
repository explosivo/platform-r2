package com.zachnickell.platform.menu;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import com.zachnickell.platform.gfx.Sprites;

public class MainMenu {

	public Texture singleplayerButton;
	double x, y, w, h;
	
	public MainMenu(){
		singleplayerButton = Sprites.singleplayerButton;
		x = 20;
		y = 20;
		w = 148;
		h = 24;
	}
	
	public void update(){
		
	}
	
	public void render(){
		singleplayerButton.bind();
		GL11.glPushMatrix();
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
	}
	
}
