package com.zachnickell.platform.entity.item;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.zachnickell.platform.Sounds;
import com.zachnickell.platform.entity.Entity;
import com.zachnickell.platform.entity.Player;
import com.zachnickell.platform.gfx.Sprites;
import com.zachnickell.platform.level.Level;

public class Item extends Entity {
	
	public String name = "item";
	public boolean item = true;
	public int itemNumber;
	public static final int HEALTH = 0;
	public static final int STAMINA = 1;
	public static final int POWER = 2;
	public Random random = new Random();
	public Item(int x, int y){
		itemNumber = random.nextInt(3);
		sprite = Sprites.item;
		if (itemNumber == HEALTH) {
			name = "Health Up";
			sprite = Sprites.healthItem;
		}
		if (itemNumber == STAMINA) {
			name = "Stamina Up";
			sprite = Sprites.staminaItem;
		}
		if (itemNumber == POWER) {
			name = "Power Up";
			sprite = Sprites.powerItem;
		}
		
		
		w = 16;
		h = 16;
		this.x = (double) x * 16;
		this.y = (double) y * 16;
		canPickUp = true;
		interacts = false;
	}
	
	public Item(int x, int y, int itemNumber){
		this.itemNumber = itemNumber;
		sprite = Sprites.item;
		if (itemNumber == HEALTH) {
			name = "Health Up";
			sprite = Sprites.healthItem;
		}
		if (itemNumber == STAMINA) {
			name = "Stamina Up";
			sprite = Sprites.staminaItem;
		}
		if (itemNumber == POWER) {
			name = "Power Up";
			sprite = Sprites.powerItem;
		}
		
		
		w = 16;
		h = 16;
		this.x = (double) x * 16;
		this.y = (double) y * 16;
		canPickUp = true;
		interacts = false;
	}
	
	public void use(Player owner){
		Sounds.getItem.play();
		if (itemNumber == HEALTH) {
			owner.health += owner.maxHealth/4;
		}
		if (itemNumber == STAMINA) {
			owner.stamina += owner.maxStamina/8;
		}
		if (itemNumber == POWER) {
			owner.xp += 5;
		}
	}
	
	public void notification(){
		Sounds.getItem.play();
	}
	

	
	public void render(){
		if (shouldRender()) {
			sprite.bind();
			GL11.glPushMatrix();
			GL11.glBegin(GL11.GL_QUADS);
				GL11.glTexCoord2d(0, 0);
				GL11.glVertex2d(x, y);
				GL11.glTexCoord2d(1, 0);
				GL11.glVertex2d(x+w, y);
				GL11.glTexCoord2d(1, 1);
				GL11.glVertex2d(x+w, y+h);
				GL11.glTexCoord2d(0, 1);
				GL11.glVertex2d(x, y+h);
			GL11.glEnd();
			GL11.glPopMatrix();
		}
	}

}
