package com.zachnickell.platform.level;

import java.awt.Color;
import java.awt.Graphics;

import com.zachnickell.platform.Platform;
import com.zachnickell.platform.entity.Player;
import com.zachnickell.platform.entity.tile.Tile;

public class PlayerGui {
	Player player;
	public PlayerGui(Player p){
		this.player = p;
	}
	public void render(Graphics g){
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, Platform.WIDTH, 16);
		g.setColor(Color.red);
		g.fillRect(3, 5, (player.health*64/player.maxHealth)-1, 7);
		g.setColor(Color.white);
		g.drawRect(2, 4, 64, 8);
		g.setColor(Color.YELLOW);
		g.fillRect(70, 4, player.xp*128/player.maxXP, 2);
		g.drawString((String.valueOf(player.level)), 256, 12);
	}
	public void update(int delta){
		
	}
}
