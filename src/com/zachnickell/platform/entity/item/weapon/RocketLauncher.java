package com.zachnickell.platform.entity.item.weapon;

import com.zachnickell.platform.entity.Entity;

public class RocketLauncher extends Pistol{

	int tick;
	boolean canFire = true;
	
	
	public RocketLauncher(Entity owner) {
		super(owner);
		// TODO Auto-generated constructor stub
	}
	
	public void update(int delta){
		if (!canFire)
			tick += delta;
		super.update(delta);
	}
	
	public void fire(double angle){
		if (!canFire){
			if (tick >= 500){
				canFire = true;
				tick = 0;
			}
		}
		if (canFire){
			bullet.add(new Rocket((int) owner.x+owner.w/2, (int) owner.y+owner.h/2, angle));
			canFire = false;
		}
	}

}
