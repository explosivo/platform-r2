package com.zachnickell.platform.level;

import java.awt.Color;
import java.awt.Graphics;

import com.zachnickell.platform.Platform;
import com.zachnickell.platform.entity.Player;

public class PlayerGui {
	Player player;

	public PlayerGui(Player p) {
		this.player = p;
	}

	public void render(Graphics g) {
		
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
	}

	public void update(int delta) {

	}
}
