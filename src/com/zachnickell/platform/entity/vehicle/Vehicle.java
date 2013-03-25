package com.zachnickell.platform.entity.vehicle;

import com.zachnickell.platform.entity.ControllableEntity;
import com.zachnickell.platform.entity.Entity;

public class Vehicle extends ControllableEntity {

	public int boostTime;
	public int boostSpeed;
	public boolean isBoosting = false;
	public boolean isFlying = false;
	public boolean isDriving = false;
	public boolean isSwimming = false;

	public void update(int delta, Entity e) {
		if (e.alive) {
			move(delta);
		}
	}

	public void boost() {
		if (isBoosting) {
			speed = boostSpeed;
		}

	}

}
