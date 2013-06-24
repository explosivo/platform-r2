package com.zachnickell.platform.entity;

import com.zachnickell.platform.entity.item.weapon.RocketLauncher;
import com.zachnickell.platform.gfx.Sprites;

public class Robot extends Monster{

	int fireTick;
	RocketLauncher rocketLauncher;
	
	public Robot(int spawnX, int spawnY, Player player, Portal portal) {
		super(spawnX, spawnY, player, portal);
		rocketLauncher = new RocketLauncher(this);
		speed = .5;
	}
	
	public void render(){
		if (shouldRender() && isAlive()) {
			sprite = Sprites.robotAnim.get(frame);
		}
		 super.render();
		 rocketLauncher.render();
	}
	
	public void update(int delta){
		
		rocketLauncher.update(delta);
		if (isAlive() && shouldRender()){
			fire();
			tick += delta;
			if (tick > 150){
				frame++;
				tick = 0;
			}
			if (frame > 3) frame = 0;
		}
		super.update(delta);
	}
	
	public void fire(){
		rocketLauncher.fire(angle);
	}

}
