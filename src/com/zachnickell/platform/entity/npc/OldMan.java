package com.zachnickell.platform.entity.npc;

import com.zachnickell.platform.entity.Player;
import com.zachnickell.platform.gfx.Sprites;
import com.zachnickell.platform.level.Level;

public class OldMan extends NPC {

	Level level;
	Player player;

	public OldMan(int spawnX, int spawnY, Player player, Level level) {
		sprite = Sprites.oldMan;
		this.x = (double) spawnX * 16;
		this.y = (double) spawnY * 16;
		w = 24;
		h = 24;
		speed = 0;
		isSolid = true;
		maxHealth = 1;
		health = maxHealth;
		this.player = player;
		this.level = level;
	}
	
	
	public void update(int delta){
		angle = Math.atan2((y + h / 2) - (player.y + h / 2), (x + w / 2)
				- (player.x + w / 2))
				+ Math.PI / 2;
	}

}
