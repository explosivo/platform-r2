package com.zachnickell.platform;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;


import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.*;

import static org.lwjgl.opengl.GL11.*;

import com.zachnickell.platform.entity.Entity;
import com.zachnickell.platform.entity.tile.Tile;
import com.zachnickell.platform.gfx.Sprites;
import com.zachnickell.platform.level.Level;
import com.zachnickell.platform.level.creator.LevelCreator;
import com.zachnickell.platform.menu.MainMenu;

import java.awt.Canvas;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;


public class Platform extends Canvas{
	private static final long serialVersionUID = 1L;
	public static boolean running = false;
	public static final String NAME = "Platform-r2";
	public static final String VERSION = "Alpha 0.5a";
	public static final int WIDTH = 320;
	public static final int HEIGHT = 240;
	public static final int SCALE = 2;
	public static int FRAMES = 0;
	public static long lastDeltaTime;
	
	private Input input;
	private MainMenu mainmenu;
	private Level level;
	private Sounds sounds;
	
	Sprites sprites;

	public boolean songPlaying = true;

	AudioInputStream audioIn;
	Clip clip;

	public static String getTitle() {
		String title = NAME + " " + VERSION;
		return title;
	}

	public int getDelta() {
		int delta = (int) (System.currentTimeMillis() - lastDeltaTime);
		lastDeltaTime = System.currentTimeMillis();
		return delta;
	}

	public void start() {
		if (!running) {
			/*try {
				audioIn = AudioSystem.getAudioInputStream(Platform.class
						.getResource("/music.wav"));
				clip = AudioSystem.getClip();
				clip.open(audioIn);
				clip.loop(Clip.LOOP_CONTINUOUSLY);
				clip.start();
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			getDelta();
			try {
				//entities = (ArrayList<Entity>) object;
				Display.setDisplayMode(new DisplayMode(WIDTH * SCALE, HEIGHT * SCALE));
				Display.setTitle(getTitle());
				Display.setVSyncEnabled(false);
				Display.create();
			} catch (LWJGLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.exit(0);
			}
			
			input = new Input();
			running = true;
			sprites = new Sprites();
			sounds = new Sounds();
			//mainmenu = new MainMenu();
			LevelCreator levelCreator = new LevelCreator();
			level = new Level(levelCreator);
			new Entity().init(level);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			
			GL11.glClearColor(0f, 0f, 0f, 0f);
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glMatrixMode(GL11.GL_PROJECTION);
			GL11.glLoadIdentity();
			GL11.glOrtho(0, WIDTH, HEIGHT, 0, 1, -1);
			GL11.glMatrixMode(GL11.GL_MODELVIEW);

			
			long lastTime = System.currentTimeMillis();
			while (!Display.isCloseRequested()) {
				int delta = getDelta();
				//if (!hasFocus()) {
				//	Input.releaseKeys();
				//}
				Input.checkInput();
				update(delta);
				render();
				Display.update();
				Display.sync(30);
				if (Input.mutePressed) {
					muteSong();
				}
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			Display.destroy();
		}
	}


	public void update(int delta) {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		//mainmenu.update();
		level.update(delta);
	}

	public void render() {
		glLoadIdentity();
		//mainmenu.render();
		level.render();
	}

	public void drawScreen() {
		FRAMES++;
	}

	public static void gameOver() {
		running = false;
	}

	long lastPress = System.currentTimeMillis();

	public void muteSong() {
		if (System.currentTimeMillis() - lastPress > 250) {
			 lastPress = System.currentTimeMillis();
			if (songPlaying) {
				clip.stop();
				songPlaying = false;
				
			} else if (!songPlaying) {
				clip.start();
				songPlaying = true;
			}
		}

	}

	public static void main(String[] args) {
		Platform platform = new Platform();
		platform.start();
	}
}
