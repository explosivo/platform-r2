package com.zachnickell.platform;

import javax.sound.sampled.AudioInputStream;
//import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
//import javax.sound.sampled.LineUnavailableException;
//import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import com.zachnickell.platform.gfx.Sprites;
import com.zachnickell.platform.level.IntroCell;
import com.zachnickell.platform.level.Level;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
//import java.io.IOException;

public class Platform extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	public static boolean running = false;
	public static final String NAME = "Platform-r2";
	public static final String VERSION = "Pre-Alpha 0.2.7a";
	public static final int WIDTH = 320;
	public static final int HEIGHT = 240;
	public static final int SCALE = 2;
	public static int FRAMES = 0;
	private Graphics dbg;
	private BufferedImage img = new BufferedImage(WIDTH, HEIGHT,
			BufferedImage.TYPE_INT_RGB);
	public static long lastDeltaTime;

	private Input input;
	private Level level;
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
			input = new Input();
			addKeyListener(input);
			addMouseListener(input);
			addMouseMotionListener(input);
			running = true;
			requestFocus();
			sprites = new Sprites();
			level = new IntroCell(0, 0);
			new Thread(this).start();
		}
	}

	public void run() {
		long lastTime = System.currentTimeMillis();
		while (running) {
			int delta = getDelta();
			if (!hasFocus()) {
				Input.releaseKeys();
			}
			update(delta);
			render();
			drawScreen();
			if (Input.mutePressed) {
				muteSong();
			}
			if (System.currentTimeMillis() - lastTime >= 1000) {
				System.out.printf("fps: %d\n", FRAMES);
				lastTime += 1000;
				FRAMES = 0;
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void update(int delta) {
		level.update(delta);
	}

	public void render() {
		dbg = img.createGraphics();
		dbg.clearRect(0, 0, WIDTH, HEIGHT);
		level.render(dbg);
		Toolkit.getDefaultToolkit().sync();
		dbg.dispose();
	}

	public void drawScreen() {
		FRAMES++;
		Graphics g = getGraphics();
		g.drawImage(img, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		g.dispose();
	}

	public static void gameOver() {
		System.out.println("dead.");
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
		JFrame win = new JFrame(getTitle());
		win.setLayout(new BorderLayout());
		win.add(platform, BorderLayout.CENTER);
		win.setSize(WIDTH * SCALE, HEIGHT * SCALE);
		win.setResizable(false);
		win.setLocationRelativeTo(null);
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win.setVisible(true);
		platform.start();
	}
}
