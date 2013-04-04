package com.zachnickell.platform.entity;

import com.zachnickell.platform.Input;

public class ControllableEntity extends Entity {

	public void move(int delta) {
		//if (!Input.anyMovementKeysPressed())
			//direction = IDLE;
		if (Input.upPressed && canMoveUp) {
			//direction = UP;
			x += -Math.sin(angle) * speed * delta;
			y += Math.cos(angle) * speed * delta;
			//y -= speed * delta;
		} if (Input.downPressed && canMoveDown) {
			//direction = DOWN;
			x += Math.sin(angle) * speed * delta;
			y += -Math.cos(angle) * speed * delta;
			//y += speed * delta;
		} if (Input.leftPressed && canMoveLeft) {
			angle += Math.PI/55;
			//direction = LEFT;
			//x += Math.sin(angle + Math.PI/2) * speed * delta;
			//y += -Math.cos(angle + Math.PI/2) * speed * delta;
			//x -= speed * delta;
		} if (Input.rightPressed && canMoveRight) {
			angle -= Math.PI/55;
			//direction = RIGHT;
			//x += Math.sin(angle - Math.PI/2) * speed * delta;
			//y += -Math.cos(angle - Math.PI/2) * speed * delta;
			//x += speed * delta;
		}
	}

	public void fixMovement() {
		canMoveUp = true;
		canMoveDown = true;
		canMoveLeft = true;
		canMoveRight = true;
	}

	public boolean canMove(Entity e) {
		boolean movable = false;
		if (isSolid) {
			if (!e.isSolid) {
				movable = true;
			} else if (e.isSolid) {
				movable = false;
			}
		} else
			movable = true;
		return movable;
	}

	public void update(int delta) {
		move(delta);
	}

}
