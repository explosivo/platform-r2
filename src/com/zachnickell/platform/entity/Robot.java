package com.zachnickell.platform.entity;

import java.util.Collection;

import com.zachnickell.platform.entity.item.weapon.Bullet;
import com.zachnickell.platform.entity.item.weapon.RocketLauncher;
import com.zachnickell.platform.gfx.Sprites;
import com.zachnickell.platform.level.Level;

public class Robot extends Monster{

	int fireTick;
	RocketLauncher rocketLauncher;
	int moveTick;
	boolean shouldFire;
	
	public Robot(int spawnX, int spawnY, Player player, Portal portal) {
		super(spawnX, spawnY, player, portal);
		rocketLauncher = new RocketLauncher(this);
		speed = .5;
		isMoving = true;
	}
	
	public void render(){
		if (shouldRender() && isAlive()) {
			sprite = Sprites.robotAnim.get(frame);
		}
		 super.render();
		 //rocketLauncher.render();
	}
	
	public void update(int delta){
		int fireChoice = r.nextInt(1000);
		if (fireChoice <= 950)
			shouldFire = false;
		if (fireChoice > 950)
			shouldFire = true;
		System.out.println(fireChoice);
		Level.bullets.addAll(rocketLauncher.bullet);
		rocketLauncher.bullet.clear();
		rocketLauncher.update(delta);
		if (isAlive() && shouldRender()){
			if (shouldFire)
				fire();
			moveTick += delta;
			if (moveTick >= 1500){
				isMoving = false;
			}
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
