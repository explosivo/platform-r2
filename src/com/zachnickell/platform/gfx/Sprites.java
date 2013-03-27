package com.zachnickell.platform.gfx;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.zachnickell.platform.entity.tile.Tile;

public class Sprites {
	static int size = Tile.size;

	public static BufferedImage defaultSprite = new BufferedImage(size, size,
			BufferedImage.TYPE_INT_RGB);
	public static BufferedImage snow = new BufferedImage(size, size,
			BufferedImage.TYPE_INT_RGB);
	public static BufferedImage dirt = new BufferedImage(size, size,
			BufferedImage.TYPE_INT_RGB);
	public static BufferedImage spaceship = new BufferedImage(size, size,
			BufferedImage.TYPE_INT_RGB);
	public static BufferedImage heart = new BufferedImage(size, size,
			BufferedImage.TRANSLUCENT);
	public static BufferedImage player = new BufferedImage(size, size,
			BufferedImage.TRANSLUCENT);
	public static BufferedImage playerHurt = new BufferedImage(size, size,
			BufferedImage.TRANSLUCENT);
	public static BufferedImage zombie = new BufferedImage(size, size,
			BufferedImage.TRANSLUCENT);

	public Sprites() {
		makeDefaultSprite();
		makeSnowSprite();
		makeDirtSprite();
		makeHeartSprite();
		makePlayerSprite();
		makePlayerHurtSprite();
		makeZombieSprite();
	}

	public BufferedImage getImage(String s) {
		BufferedImage i = null;
		try {
			i = ImageIO.read(Sprites.class.getResource(s));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return i;
	}

	public void makeDefaultSprite() {
		Graphics g = defaultSprite.createGraphics();
		g.setColor(Color.white);
		g.fillRect(0 * 8, 0 * 8, 8, 8);
		g.fillRect(0 * 8 + 8, 0 * 8 + 8, 8, 8);
		g.setColor(Color.black);
		g.fillRect(0 * 8 + 8, 0 * 8, 8, 8);
		g.fillRect(0 * 8, 0 * 8 + 8, 8, 8);
		g.dispose();
	}

	public void makePlayerSprite() {
		player = getImage("/joshfront.png");
	}

	public void makePlayerHurtSprite() {
		playerHurt = getImage("/joshhurt.png");
	}

	public void makeZombieSprite() {
		zombie = getImage("/zombie.png");
	}

	public void makeSnowSprite() {
		snow = getImage("/snow.png");
		if (snow == null) {
			Graphics g = snow.createGraphics();
			g.setColor(Color.GRAY);
			g.fillRect(0 * 8, 0 * 8, 8, 8);
			g.fillRect(0 * 8 + 8, 0 * 8 + 8, 8, 8);
			g.setColor(Color.DARK_GRAY);
			g.fillRect(0 * 8 + 8, 0 * 8, 8, 8);
			g.fillRect(0 * 8, 0 * 8 + 8, 8, 8);
			g.dispose();
		}
	}

	public void makeDirtSprite() {
		dirt = getImage("/dirt.png");
		if (dirt == null) {
			Graphics g = dirt.createGraphics();
			g.setColor(Color.ORANGE);
			g.fillRect(0 * 8, 0 * 8, 8, 8);
			g.fillRect(0 * 8 + 8, 0 * 8 + 8, 8, 8);
			g.setColor(Color.BLUE);
			g.fillRect(0 * 8 + 8, 0 * 8, 8, 8);
			g.fillRect(0 * 8, 0 * 8 + 8, 8, 8);
			g.dispose();
		}
	}

	public void makeHeartSprite() {
		if (heart == null) {
			Graphics g = heart.createGraphics();
			g.setColor(Color.RED);
			g.fillOval(0, 0, size, size);
			g.dispose();
		}
	}

	public void makeSpaceShipSprite() {
		if (spaceship == null) {
			Graphics g = spaceship.createGraphics();
			g.setColor(Color.GRAY);
			g.fillRect(0, 0, size, size);
			g.dispose();
		}
	}

}
