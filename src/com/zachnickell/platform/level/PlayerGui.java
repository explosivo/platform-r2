package com.zachnickell.platform.level;

import java.awt.Color;
import java.awt.Graphics;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Font;
import org.newdawn.slick.TrueTypeFont;

import com.zachnickell.platform.Platform;
import com.zachnickell.platform.entity.Player;
import com.zachnickell.platform.gfx.Sprites;

public class PlayerGui {
	Player player;
	Font font;
	public PlayerGui(Player p) {
		this.player = p;
		java.awt.Font awtFont = new java.awt.Font("Times New Roman", 0, 12);
		font = new TrueTypeFont(awtFont, false);
	}

	public void render(){//Graphics g) {
		/*
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, Platform.WIDTH, 16);
		g.setColor(Color.red);
		g.fillRect(3, 5, (player.health * 64 / player.maxHealth) - 1, 7);
		g.setColor(Color.white);
		g.drawRect(2, 4, 64, 8);
		g.setColor(Color.red);
		g.fillRect(3, 5, (player.health * 64 / player.maxHealth) - 1, 7);
		g.setColor(Color.white);
		g.drawRect(64+6, 4, 64, 8);
		g.setColor(Color.blue);
		g.fillRect(64 + 7, 5, (player.stamina * 64 / player.maxStamina) - 1, 7);
		g.setColor(Color.white);
		g.drawRect(64*2+9, 7, 116, 3);
		g.setColor(Color.YELLOW);
		g.fillRect(64 * 2 + 10, 8, player.xp * 115 / player.maxXP, 2);
		g.drawString((String.valueOf(player.level)), 256, 12);
		g.setColor(Color.WHITE);
		g.drawString("Kills:" + (String.valueOf(player.kills)), 2, 28);
		*/
		//GL11.glLoadIdentity();
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glTranslated(0, 0, 0);
		GL11.glLineWidth(2f);
		//background
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glColor3f(0.75f, 0.75f, 0.75f);
			GL11.glVertex3i(0, 0, 0);
			GL11.glVertex3i(Platform.WIDTH * Platform.SCALE, 0, 0);
			GL11.glVertex3i(Platform.WIDTH * Platform.SCALE, 16, 0);
			GL11.glVertex3i(0, 16, 0);
		GL11.glEnd();
		//healthBar
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glColor3f(1f, 0f, 0f);
			GL11.glVertex3i(3, 5, 0);
			GL11.glVertex3i(3 + (player.health * 64 / player.maxHealth), 5, 0);
			GL11.glVertex3i(3 + (player.health * 64 / player.maxHealth), 5 + 7, 0);
			GL11.glVertex3i(3, 5 + 7, 0);
		GL11.glEnd();
		GL11.glBegin(GL11.GL_LINES);
			GL11.glColor3f(1f, 1f, 1f);
			GL11.glVertex3i(3, 5, 0);
			GL11.glVertex3i(3 + 64, 5, 0);
			GL11.glVertex3i(3, 5, 0);
			GL11.glVertex3i(3, 5 + 7, 0);
			GL11.glVertex3i(3 + 64, 5 + 7, 0);
			GL11.glVertex3i(3, 5 + 7, 0);
			GL11.glVertex3i(3 + 64, 5 + 7, 0);
			GL11.glVertex3i(3 + 64, 5, 0);
		GL11.glEnd();
		//stamina bar
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glColor3f(0f, 0f, 1f);
			GL11.glVertex3i(64+6, 5, 0);
			GL11.glVertex3i(64+6 + (player.stamina * 64 / player.maxStamina), 5, 0);
			GL11.glVertex3i(64+6 + (player.stamina * 64 / player.maxStamina), 5 + 7, 0);
			GL11.glVertex3i(64+6, 5 + 7, 0);
		GL11.glEnd();
		GL11.glBegin(GL11.GL_LINES);
			GL11.glColor3f(1f, 1f, 1f);
			GL11.glVertex3i(64+6, 5, 0);
			GL11.glVertex3i(64+6 + 64, 5, 0);
			GL11.glVertex3i(64+6, 5, 0);
			GL11.glVertex3i(64+6, 5+7, 0);
			GL11.glVertex3i(64+6 + 64, 5 + 7, 0);
			GL11.glVertex3i(64+6, 5 + 7, 0);
			GL11.glVertex3i(64+6 + 64, 5 + 7, 0);
			GL11.glVertex3i(64+6 + 64, 5, 0);
		GL11.glEnd();
		//xp bar
		//g.fillRect(64 * 2 + 10, 8, player.xp * 115 / player.maxXP, 2);
		//g.drawString((String.valueOf(player.level)), 256, 12);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glColor3f(0f, .5f, 0f);
		GL11.glVertex3i(138, 5, 0);
		GL11.glVertex3i(138 + (player.xp * 115 / player.maxXP), 5, 0);
		GL11.glVertex3i(138 + (player.xp * 115 / player.maxXP), 5 + 7, 0);
		GL11.glVertex3i(138, 5 + 7, 0);
		GL11.glEnd();
		GL11.glBegin(GL11.GL_LINES);
			GL11.glColor3f(1f, 1f, 1f);
			GL11.glVertex3i(138, 5, 0);
			GL11.glVertex3i(138 + 113, 5, 0);
			GL11.glVertex3i(138 + 113, 5, 0);
			GL11.glVertex3i(138 + 113, 5+7, 0);
			GL11.glVertex3i(138 + 113, 5+7, 0);
			GL11.glVertex3i(138, 5+7, 0);
			GL11.glVertex3i(138, 5+7, 0);
			GL11.glVertex3i(138, 5, 0);
		GL11.glEnd();
		font.drawString(138 + 120, 2, String.valueOf(player.level), org.newdawn.slick.Color.white);
		font.drawString(0, 16, "Kills: " + player.kills, org.newdawn.slick.Color.white);
		GL11.glPopMatrix();
		GL11.glColor3f(1f, 1f, 1f);

	}

	public void update(int delta) {

	}
}
