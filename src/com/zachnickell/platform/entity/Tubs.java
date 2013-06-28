package com.zachnickell.platform.entity;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import com.zachnickell.platform.gfx.Sprites;

public class Tubs extends Entity{

	Texture body;
	Texture leftArm;
	Texture rightArm;
	Texture leftLeg;
	Texture rightLeg;
	
	int scale = 3;
	
	int bodyw = 17 * scale;
	int bodyh = 19 * scale;
	
	int leftArmw = 5 * scale;
	int leftArmh = 10 * scale;
	int rightArmw;
	int rightArmh;
	
	int leftLegw;
	int leftLegh;
	int rightLegw;
	int rightLegh;
	
	int frame;
	
	public Tubs(int spawnX, int spawnY, Player player) {
		//super(spawnX, spawnY, player, null);
		x = spawnX * 16;
		y = spawnY * 16;
		body = Sprites.tubsBodyAnim.get(frame);
		leftArm = Sprites.tubsLeftArmAnim.get(frame);
		rightArm = Sprites.tubsRightArmAnim.get(frame);
		leftLeg = Sprites.tubsLeftLegAnim.get(frame);
		rightLeg = Sprites.tubsRightLegAnim.get(frame);
	
		//w = 1; h =1;
		
		maxHealth = 10;
		health = maxHealth;
	}
	
	public void render(){
		if (isAlive()) {
			
			//body
			Sprites.tubsBodyAnim.get(frame).bind();
			GL11.glPushMatrix();
			//GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glTranslated((x + bodyw / 2), (y + bodyh /2), 0);
			GL11.glRotated(Math.toDegrees(angle), 0, 0, 1);
			GL11.glTranslated(-(x + bodyw / 2), -(y + bodyh /2), 0);
			GL11.glBegin(GL11.GL_QUADS);
				GL11.glTexCoord2d(0, 0);
				GL11.glVertex2d(x, y);
				GL11.glTexCoord2d(1, 0);
				GL11.glVertex2d(x + bodyw, y);
				GL11.glTexCoord2d(1, 1);
				GL11.glVertex2d(x + bodyw, y + bodyh);
				GL11.glTexCoord2d(0, 1);
				GL11.glVertex2d(x, y + bodyh);
			GL11.glEnd();
			
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			if (isHurt()){
				GL11.glColor3d(0, 0, 0);
				GL11.glBegin(GL11.GL_QUADS);
				GL11.glVertex2d(x, y - 3);
				GL11.glVertex2d(x - 1 + w, y - 3);
				GL11.glVertex2d(x + w, y - 3 - 3);
				GL11.glVertex2d(x - 1, y - 3 - 3);
				GL11.glEnd();
				GL11.glColor3d(0, 1, 0);
				GL11.glBegin(GL11.GL_QUADS);
				GL11.glVertex2d(x, y - 3);
				GL11.glVertex2d(x - 1 + (health * w /maxHealth) + 2, y - 3);
				GL11.glVertex2d(x + (health * w /maxHealth) + 2, y - 3 - 3);
				GL11.glVertex2d(x - 1, y - 3 - 3);
				GL11.glEnd();
			}
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glColor3d(1, 1, 1);
			GL11.glPopMatrix();
		}
	}
	
	public void update(int delta){}

}