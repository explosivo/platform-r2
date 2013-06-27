package com.zachnickell.platform;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class Sounds {
	
	public static Sound explosion;
	public static Sound shoot;
	public static Sound fireRocket;
	public static Sound hurt;
	public static Sound getItem;
	public static Sound dead;
	public static Sound brains;

	public Sounds(){
		try {
			explosion = new Sound("sounds/explosion.wav");
			shoot = new Sound("sounds/shoot.wav");
			fireRocket = new Sound("sounds/fireRocket.wav");
			hurt = new Sound("sounds/hurt2.wav");
			getItem = new Sound("sounds/getItem.wav");
			dead = new Sound("sounds/dead.wav");
			brains = new Sound("sounds/brains.wav");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
