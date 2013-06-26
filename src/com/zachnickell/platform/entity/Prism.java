package com.zachnickell.platform.entity;

import com.zachnickell.platform.gfx.Sprites;
import com.zachnickell.platform.level.Level;

public class Prism extends Portal{

	public Prism(int x, int y) {
		super(x, y);
		maxFrame = 3;
		maxHealth = 3;
		maxMonsters = 3;
		}
	
	public void render(){
		if (shouldRender() && isAlive()){
			sprite = Sprites.prismAnim.get(frame);
		}
		super.render();
	}
	
	public void addMonster(int mx, int my){
		Level.entities.add(new Robot((mx)/16, (my)/16, Level.player, this));
	}

	public void getFrame(){
		sprite = Sprites.prismAnim.get(frame);
	}
	
}
