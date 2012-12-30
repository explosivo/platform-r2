package com.zachnickell.platform.entity.tile;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.zachnickell.platform.entity.Entity;

public class Tile extends Entity{
	int x;
	int y;
	public static int size = 16;
	public BufferedImage sprite = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
	public static BufferedImage defaultSprite = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
	public static BufferedImage snow = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
	public static BufferedImage dirt = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
	public static BufferedImage spaceship = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
	public static BufferedImage heart = new BufferedImage(size, size, BufferedImage.TRANSLUCENT);
	public int id;
	public static final int DEFAULT = 0;
	public static final int SNOW = 1;
	public static final int DIRT = 2;
	int w = size;
	int h = size;
	public Tile(int x, int y){
		sprite = defaultSprite;
		this.x = x;
		this.y = y;
		makeDefaultSprite();
		makeSnowSprite();
		makeDirtSprite();
		makeHeartSprite();
	}
	
	public void render(Graphics g){
		g.drawImage(sprite, x*size, y*size, null);
		}
	
	public void makeDefaultSprite(){
		Graphics g = defaultSprite.createGraphics();
		g.setColor(Color.white);
		g.fillRect(0*8, 0*8, 8, 8);
		g.fillRect(0*8+8, 0*8+8, 8, 8);
		g.setColor(Color.magenta);
		g.fillRect(0*8+8, 0*8, 8, 8);
		g.fillRect(0*8, 0*8+8, 8, 8);
		g.dispose();
	}
	public void makeSnowSprite(){
		Graphics g = snow.createGraphics();
		g.setColor(Color.GRAY);
		g.fillRect(0*8, 0*8, 8, 8);
		g.fillRect(0*8+8, 0*8+8, 8, 8);
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0*8+8, 0*8, 8, 8);
		g.fillRect(0*8, 0*8+8, 8, 8);
		g.dispose();
	}
	public void makeDirtSprite(){
		Graphics g = dirt.createGraphics();
		g.setColor(Color.ORANGE);
		g.fillRect(0*8, 0*8, 8, 8);
		g.fillRect(0*8+8, 0*8+8, 8, 8);
		g.setColor(Color.BLUE);
		g.fillRect(0*8+8, 0*8, 8, 8);
		g.fillRect(0*8, 0*8+8, 8, 8);
		g.dispose();
	}
	
	public void makeHeartSprite(){
		Graphics g = heart.createGraphics();
		g.setColor(Color.RED);
		g.fillOval(0, 0, size, size);
		g.dispose();
	}
	public void makeSpaceShipSprite(){
		Graphics g = spaceship.createGraphics();
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, size, size);
		g.dispose();
	}
	
	/*public void collision(Entity e, int delta){
		System.out.printf("%d, %d\n", x, y);
	}*/
	
	public void collision(Entity e, int delta){
		if (isSolid && e.isSolid){
			e.doesDamage(damage);
			if (e.direction == UP){
				//e.canMoveUp = false;
				e.y = y*size + h;
				if (isMovable){
					y -= e.speed * delta;
				}
			}
			else if (e.direction == DOWN){
				//e.canMoveDown = false;
				e.y = y*size - e.h;
				if (isMovable){
					y += e.speed * delta;
				}
			}
			else if (e.direction == LEFT){
				//e.canMoveLeft = false;
				e.x = x*size + w;
				if (isMovable){
					x -= e.speed * delta;
				}
			}
			else if (e.direction == RIGHT){
				//e.canMoveRight = false;
				e.x = x*size - e.w;
				if (isMovable){
					x += e.speed * delta;
				}
			}
		}
	}
	
	public Rectangle getBounds(){
		return new Rectangle(this.x*size, this.y*size, size, size);
	}
}
