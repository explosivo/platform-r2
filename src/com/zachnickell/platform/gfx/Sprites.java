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
	public static Texture item;
	public static Texture healthItem;
	public static Texture staminaItem;
	public static Texture powerItem;
	public static Texture explosion;
	public static Texture rocket;
	public static Texture bullet;
	public static Texture rocketLauncher;
	public static ArrayList<Texture> playerAnim = new ArrayList<Texture>();
	public static ArrayList<Texture> portalAnim = new ArrayList<Texture>();
	public static ArrayList<Texture> prismAnim = new ArrayList<Texture>();
	public static ArrayList<Texture> robotAnim = new ArrayList<Texture>();
	
	public static Texture multiplayerButton;
	public static Texture singleplayerButton;
	
	
	public static Texture oldMan;
	public static Texture portal;
	
	//bosses
	
	//tubs
	public static Texture burger;
	public static ArrayList<Texture> tubsBodyAnim = new ArrayList<Texture>();
	public static ArrayList<Texture> tubsLeftArmAnim = new ArrayList<Texture>();
	public static ArrayList<Texture> tubsRightArmAnim = new ArrayList<Texture>();
	public static ArrayList<Texture> tubsLeftLegAnim = new ArrayList<Texture>();
	public static ArrayList<Texture> tubsRightLegAnim = new ArrayList<Texture>();

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
		
		makeItems();
		
		makePlayerAnimation();
		makePortalAnimation();
		makePrismAnimation();
		makeRobotAnimation();
		
		makeExplosionSprite();
		makeRocketSprite();
		makeBulletSprite();
		makeBurgerSprite();
		
		makeTubs();
		
		makeMenuUI();
		
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
	
	public void makePrismAnimation(){
		prismAnim.add(getImage("prismAnim/1.png"));
		prismAnim.add(getImage("prismAnim/2.png"));
		prismAnim.add(getImage("prismAnim/3.png"));
		prismAnim.add(getImage("prismAnim/4.png"));
	}
	
	public void makeRobotAnimation(){
		robotAnim.add(getImage("robotAnim/1.png"));
		robotAnim.add(getImage("robotAnim/2.png"));
		robotAnim.add(getImage("robotAnim/3.png"));
		robotAnim.add(getImage("robotAnim/4.png"));
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
	
	public void makeExplosionSprite() {
		explosion = getImage("explosion.png");
	}
	public void makeRocketSprite() {
		rocket = getImage("rocket.png");
	}
	public void makeBulletSprite() {
		bullet = getImage("bullet.png");
	}
	public void makeBurgerSprite() {
		burger = getImage("burger.png");
	}
	
	public void makeItems() {
		item = getImage("Items/Item.png");
		healthItem = getImage("Items/healthItem.png");
		staminaItem = getImage("Items/staminaItem.png");
		powerItem = getImage("Items/powerItem.png");
		rocketLauncher = getImage("Items/Weapons/rocketLauncher.png");
	}
	
	public void makeTubs(){
		tubsBodyAnim.add(getImage("boss/tubsAnim/body/1.png"));
		tubsBodyAnim.add(getImage("boss/tubsAnim/body/2.png"));
		tubsLeftArmAnim.add(getImage("boss/tubsAnim/leftArm/1.png"));
		tubsLeftArmAnim.add(getImage("boss/tubsAnim/leftArm/2.png"));
		tubsRightArmAnim.add(getImage("boss/tubsAnim/rightArm/1.png"));
		tubsRightArmAnim.add(getImage("boss/tubsAnim/rightArm/2.png"));
		tubsLeftLegAnim.add(getImage("boss/tubsAnim/leftLeg/1.png"));
		tubsLeftLegAnim.add(getImage("boss/tubsAnim/leftLeg/2.png"));
		tubsRightLegAnim.add(getImage("boss/tubsAnim/rightLeg/1.png"));
		tubsRightLegAnim.add(getImage("boss/tubsAnim/rightLeg/2.png"));
	}
	
	public void makeMenuUI(){
		multiplayerButton = getImage("menuUI/buttons/multiplayer.png");
		singleplayerButton = getImage("menuUI/buttons/singleplayer.png");
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
