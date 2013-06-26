package com.zachnickell.platform.entity.item.weapon;

import com.zachnickell.platform.entity.Entity;

public class Weapon {
	
	int damage;
	int fireRate;
	double fireAngle;
	int bulletX;
	int bulletY;
	Entity owner;
	boolean firing = false;
	boolean canFire = true;
	int tick;
	
	
	public Weapon (Entity owner){
		this.owner = owner;
	}
	
	public void getFireAngle(){
		//fireAngle = owner.angle;
		//fireAngle = Math.atan2(owner.y - Input.y,
			//	owner.x - Input.x)
			//	- 3
			//	* Math.PI/2;
	}
	
	public void fire(){
		firing = true;
	}
	
	public void ceaseFire(){
		firing = false;
	}
	
	
}
