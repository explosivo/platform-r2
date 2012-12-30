package com.zachnickell.platform.entity;

import java.awt.Color;
import java.awt.Graphics;

import com.zachnickell.platform.Input;

public class ControllableEntity extends Entity{

	
	public void move(int delta){
		if (!Input.anyMovementKeysPressed())
			direction = IDLE;
		else if (Input.upPressed && canMoveUp){
				direction = UP;
			y -= speed * delta;
		}
		else if (Input.downPressed && canMoveDown){
				direction = DOWN;
			y += speed * delta;
		}
		else if (Input.leftPressed && canMoveLeft){
				direction = LEFT;
			x -= speed * delta;
		}
		else if (Input.rightPressed && canMoveRight){
				direction = RIGHT;
			x += speed * delta;
		}
	}
	public void fixMovement(){
		canMoveUp = true;
		canMoveDown = true;
		canMoveLeft = true;
		canMoveRight = true;
	}
	public boolean canMove(Entity e){
		boolean movable = false;
		if(isSolid){
			if(!e.isSolid){
				movable = true;
			}
			else if(e.isSolid){
				movable =  false;
			}
		}
		else
			movable = true;
		return movable;
	}
	
	public void update(int delta){
		move(delta);
	}
	
}
