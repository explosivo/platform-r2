package com.zachnickell.platform.server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.ArrayList;

import com.zachnickell.platform.entity.Entity;
import com.zachnickell.platform.entity.tile.Tile;
import com.zachnickell.platform.level.creator.*;

import javax.swing.*;

public class Server extends JFrame implements Runnable{

	private static final long serialVersionUID = 1L;
	static int port;
	static ServerSocket serverSocket;
	static Socket socket;
	static LevelCreator levelCreator;
	static boolean running = false;

	static JTextArea log;
	static JPanel bottomPane;
	static JButton start;
	static JButton stop;
	static JTextField portField;
	static JLabel portLabel;
	
	
	public Server(){
		new Thread(this).start();
		
		bottomPane = new JPanel();
		portLabel = new JLabel("Port:");
		portField = new JTextField(5);
		start = new JButton("start");
		start.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				port = Integer.valueOf(portField.getText());
				startServer();
				start.setEnabled(false);
				portField.setEnabled(false);
			}
			
		});
		stop = new JButton("stop");
		stop.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				stopServer();
				start.setEnabled(true);
				portField.setEnabled(true);
			}
			
		});
		bottomPane.add(portLabel);
		bottomPane.add(portField);
		bottomPane.add(start);
		bottomPane.add(stop);
		log = new JTextArea();
		log.setBackground(Color.GRAY);
		log.setEditable(false);
		add(log, BorderLayout.CENTER);
		add(bottomPane, BorderLayout.SOUTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(640, 480);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void run(){
		System.out.println("thread running");
		while(running){
			log.append("m\n");
		}
	}
	
	public void startServer(){
		try {
			serverSocket = new ServerSocket(port);
			log.append("Server connected to port: " + port + "!\n");
			running = true;
		} catch (IOException e) {
			e.printStackTrace();
			log.append(e.toString() + "\n");
		}
	}
	
	public void stopServer(){
		if (running){
			try {
				serverSocket.close();
				log.append("Server Disconnected.\n");
			} catch (IOException e) {
				e.printStackTrace();
				log.append(e.toString() + "\n");
			}
		}
	}
	
	public static void main(String[] args){
		try{
		new Server();
		log.append("Starting Level Creator...\n");
		levelCreator = new LevelCreator();
		log.append("Started Level Creator!\n");
		} catch (Exception e){
			e.printStackTrace();
			log.append(e.toString() + "\n");
		}
		/*try {
			ArrayList<Tile> t = levelCreator.getTiles();
			log.append("Attempting to start server on port " + port + "...\n");
			serverSocket = new ServerSocket(port);
			log.append("Server connected to port!\n");
			log.append("Accepting connections...\n");
			socket = serverSocket.accept();
			log.append("Connection Accepted!\n");
			log.append("Starting output stream...\n");
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			log.append("Output stream started!\n");
			log.append("Attempting sending Entity List...\n");
			oos.writeObject(t);
			log.append("Send Success!\n");
			*/
	}
	
}
