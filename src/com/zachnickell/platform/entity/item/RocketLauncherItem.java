package com.zachnickell.platform.entity.item;

import com.zachnickell.platform.entity.Player;
import com.zachnickell.platform.entity.item.weapon.RocketLauncher;
import com.zachnickell.platform.gfx.Sprites;

public class RocketLauncherItem extends Item {

	public RocketLauncherItem(int x, int y) {
		super(x, y);
		name = "Rocket Launcher";
		canPickUp = true;
		sprite = Sprites.rocketLauncher;
	}
	
	public void use(Player owner){
		owner.p = new RocketLauncher(owner);
	}

}
