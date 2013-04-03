package com.zachnickell.platform.entity.npc;

import java.awt.Graphics;
import java.awt.Graphics2D;

import com.zachnickell.platform.entity.Entity;

public class NPC extends Entity {

	public void render(Graphics g) {
		if (isOnScreen) {
			Graphics2D gg = (Graphics2D) g.create();
			gg.rotate(angle, x + w / 2, y + h / 2);
			gg.drawImage(sprite, (int) x, (int) y, w, h, null);
		}
	}
	

}
