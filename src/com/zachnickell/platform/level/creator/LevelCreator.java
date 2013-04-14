package com.zachnickell.platform.level.creator;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.zachnickell.platform.entity.Entity;

public class LevelCreator {

	Graphics g;
	
	ArrayList<Entity> entities;
	
	public static BufferedImage map = new BufferedImage(25, 25, BufferedImage.TYPE_INT_RGB);
	
	public LevelCreator(){
		entities = new ArrayList<Entity>();
		try {
			map = ImageIO.read(LevelCreator.class.getResource("/map.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		createLevel();
	}
	
	public void createLevel(){
		int color;
		for (int x = 0; x < 25; x ++){
			for (int y = 0; y < 25; y++){
				color = map.getRGB(x, y);
				
				
			}
		}
	}
	
}
