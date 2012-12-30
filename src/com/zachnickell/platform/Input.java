package com.zachnickell.platform;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input implements KeyListener{

	public static boolean upPressed = false;
	public static boolean downPressed = false;
	public static boolean rightPressed = false;
	public static boolean leftPressed = false;
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W) {releaseKeys(); upPressed = true;}
		if (e.getKeyCode() == KeyEvent.VK_S) {releaseKeys(); downPressed = true;}
		if (e.getKeyCode() == KeyEvent.VK_A) {releaseKeys(); leftPressed = true;}
		if (e.getKeyCode() == KeyEvent.VK_D) {releaseKeys(); rightPressed = true;}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W) upPressed = false;
		if (e.getKeyCode() == KeyEvent.VK_S) downPressed = false;
		if (e.getKeyCode() == KeyEvent.VK_A) leftPressed = false;
		if (e.getKeyCode() == KeyEvent.VK_D) rightPressed = false;
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public static void releaseKeys(){
		upPressed = false;
		downPressed = false;
		leftPressed = false;
		rightPressed = false;
	}
	
	public static boolean anyMovementKeysPressed(){
		boolean pressed = false;
		if (!upPressed && !downPressed && !leftPressed && !rightPressed){
			pressed = false;
		}
		if (upPressed || downPressed || leftPressed || rightPressed){
			pressed = true;
		}
		return pressed;
	}
	
}
