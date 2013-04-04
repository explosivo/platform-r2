package com.zachnickell.platform;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class Input implements KeyListener, MouseMotionListener, MouseListener{

	// keyboard switches/data
	public static boolean upPressed = false;
	public static boolean downPressed = false;
	public static boolean rightPressed = false;
	public static boolean leftPressed = false;
	public static boolean mutePressed = false;
	public static boolean playPressed = false;
	
	public static boolean firePressed = false;
	
	//mouse switches/data
	public static int x, y;
	public static boolean mousePress = false;
	
	@Override
	public void keyPressed(KeyEvent e) {
		//movement
		if (e.getKeyCode() == KeyEvent.VK_W) {upPressed = true;}
		if (e.getKeyCode() == KeyEvent.VK_S) {downPressed = true;}
		if (e.getKeyCode() == KeyEvent.VK_A) {leftPressed = true;}
		if (e.getKeyCode() == KeyEvent.VK_D) {rightPressed = true;}
		//use weapon
		if (e.getKeyCode() == KeyEvent.VK_D) {firePressed = true;}
		
		if (e.getKeyCode() == KeyEvent.VK_M) {mutePressed = true;}
		if (e.getKeyCode() == KeyEvent.VK_N) {playPressed = true;}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W) upPressed = false;
		if (e.getKeyCode() == KeyEvent.VK_S) downPressed = false;
		if (e.getKeyCode() == KeyEvent.VK_A) leftPressed = false;
		if (e.getKeyCode() == KeyEvent.VK_D) {rightPressed = false;}
		
		if (e.getKeyCode() == KeyEvent.VK_M) {mutePressed = false;}
		if (e.getKeyCode() == KeyEvent.VK_N) {playPressed = false;}
		
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


	@Override
	public void mouseDragged(MouseEvent e) {
		x = (e.getX())/Platform.SCALE + 1;
		y = (e.getY())/Platform.SCALE + 1;
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		x = (e.getX())/Platform.SCALE + 1;
		y = (e.getY())/Platform.SCALE + 1;
		//System.out.printf("(%d, %d)\n", x, y);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mousePress = true;
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mousePress = false;
		
	}
	
}
