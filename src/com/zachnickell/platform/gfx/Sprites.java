package com.zachnickell.platform.gfx;

import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Image;
import org.newdawn.slick.ImageBuffer;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import com.zachnickell.platform.entity.tile.Tile;

public class Sprites {
	static int size = Tile.size;

	public static Texture defaultSprite;
	public static Texture snow;
	public static Texture dirt;
	public static Texture spaceship;
	public static Texture heart;
	public static Texture player;
	public static Texture playerHurt;
	public static Texture zombie;
	public static Texture zombieHurt;
	public static Texture grass;
	public static Texture leftWall;
	public static Texture rightWall;
	public static Texture topWall;
	public static Texture bottomWall;
	public static Texture cornerWallTL;
	public static Texture cornerWallTR;
	public static Texture cornerWallBL;
	public static Texture cornerWallBR;
	public static Texture playerGuiBg;
	public static Texture healthProg;
	public static Texture stamProg;
	public static ArrayList<Texture> playerAnim = new ArrayList<Texture>();
	public static ArrayList<Texture> portalAnim = new ArrayList<Texture>();
	
	
	public static Texture oldMan;
	public static Texture portal;

	public Sprites() {
		
		makeDefaultSprite();
		makeSnowSprite();
		//makeDirtSprite();
		makeHeartSprite();
		makePlayerSprite();
		makePlayerHurtSprite();
		makeZombieSprite();
		makeZombieHurtSprite();
		makeGrassSprite();
		makePortalSprite();
		
		makeOldManSprite();
		
		makeLeftWallSprite();
		makeRightWallSprite();
		makeTopWallSprite();
		makeBottomWallSprite();
		makeCornerWallTLSprite();
		makeCornerWallTRSprite();
		makeCornerWallBLSprite();
		makeCornerWallBRSprite();
		
		makePlayerGuiBg();
		makeHealthProg();
		makeStamProg();
		
		
		
		makePlayerAnimation();
		makePortalAnimation();
		
	}

	public Texture getImage(String s) {
		Texture i = null;
		try {
			i = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(s), GL11.GL_NEAREST);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	
	public void makeDefaultSprite() {
		/*Graphics g = defaultSprite.createGraphics();
		g.setColor(Color.white);
		g.fillRect(0 * 8, 0 * 8, 8, 8);
		g.fillRect(0 * 8 + 8, 0 * 8 + 8, 8, 8);
		g.setColor(Color.black);
		g.fillRect(0 * 8 + 8, 0 * 8, 8, 8);
		g.fillRect(0 * 8, 0 * 8 + 8, 8, 8);
		g.dispose();
		*/
	}

	public void makePortalAnimation(){
		portalAnim.add(getImage("portalAnim/1.png"));
		portalAnim.add(getImage("portalAnim/2.png"));
		portalAnim.add(getImage("portalAnim/3.png"));
		portalAnim.add(getImage("portalAnim/4.png"));
		portalAnim.add(getImage("portalAnim/5.png"));
		portalAnim.add(getImage("portalAnim/6.png"));
		portalAnim.add(getImage("portalAnim/7.png"));
	}
	
	public void makePlayerAnimation(){
		playerAnim.add(getImage("playerAnim/1.png"));
		playerAnim.add(getImage("playerAnim/2.png"));
		playerAnim.add(getImage("playerAnim/3.png"));
		playerAnim.add(getImage("playerAnim/4.png"));
	}
	
	public void makePlayerSprite() {
		player = getImage("joshfront.png");
	}

	public void makePlayerHurtSprite() {
		playerHurt = getImage("joshhurt.png");
	}

	public void makeZombieSprite() {
		zombie = getImage("zombie.png");
	}
	
	public void makeZombieHurtSprite() {
		zombieHurt = getImage("zombieHurt.png");
	}
	
	public void makeGrassSprite() {
		defaultSprite = getImage("floor.png");
	}
	public void makePortalSprite(){
		portal = getImage("portal.png");
	}
	public void makeOldManSprite() {
		oldMan = getImage("oldGuy.png");
	}
	
	public void makeLeftWallSprite() {
		leftWall = getImage("leftWall.png");
	}
	public void makeRightWallSprite() {
		rightWall = getImage("rightWall.png");
	}
	public void makeTopWallSprite() {
		topWall = getImage("topWall.png");
	}
	public void makeBottomWallSprite() {
		bottomWall = getImage("bottomWall.png");
	}
	public void makeCornerWallTLSprite() {
		cornerWallTL = getImage("cornerWallTL.png");
	}
	public void makeCornerWallTRSprite() {
		cornerWallTR = getImage("cornerWallTR.png");
	}
	public void makeCornerWallBLSprite() {
		cornerWallBL = getImage("cornerWallBL.png");
	}
	public void makeCornerWallBRSprite() {
		cornerWallBR = getImage("cornerWallBR.png");
	}
	public void makePlayerGuiBg() {
		playerGuiBg = getImage("playerGuiBg.png");
	}
	public void makeHealthProg() {
		healthProg = getImage("healthProg.png");
	}
	public void makeStamProg() {
		stamProg = getImage("stamProg.png");
	}

	public void makeSnowSprite() {
		//snow = getImage("/snow.png");
		if (snow == null) {
			/*
			Graphics g = snow.createGraphics();
			g.setColor(Color.GRAY);
			g.fillRect(0 * 8, 0 * 8, 8, 8);
			g.fillRect(0 * 8 + 8, 0 * 8 + 8, 8, 8);
			g.setColor(Color.DARK_GRAY);
			g.fillRect(0 * 8 + 8, 0 * 8, 8, 8);
			g.fillRect(0 * 8, 0 * 8 + 8, 8, 8);
			g.dispose();
			*/
		}
	}

	public void makeDirtSprite() {
		dirt = getImage("/dirt.png");
		if (dirt == null) {
			/*
			Graphics g = dirt.createGraphics();
			g.setColor(Color.ORANGE);
			g.fillRect(0 * 8, 0 * 8, 8, 8);
			g.fillRect(0 * 8 + 8, 0 * 8 + 8, 8, 8);
			g.setColor(Color.BLUE);
			g.fillRect(0 * 8 + 8, 0 * 8, 8, 8);
			g.fillRect(0 * 8, 0 * 8 + 8, 8, 8);
			g.dispose();
			*/
		}
	}

	public void makeHeartSprite() {
		if (heart == null) {
			/*
			Graphics g = heart.createGraphics();
			g.setColor(Color.RED);
			g.fillOval(0, 0, size, size);
			g.dispose();
			*/
		}
	}

	public void makeSpaceShipSprite() {
		if (spaceship == null) {
			/*
			Graphics g = spaceship.createGraphics();
			g.setColor(Color.GRAY);
			g.fillRect(0, 0, size, size);
			g.dispose();
			*/
		}
	}

}
