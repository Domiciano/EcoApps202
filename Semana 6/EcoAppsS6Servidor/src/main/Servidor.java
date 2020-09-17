package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import processing.core.PApplet;

public class Servidor extends PApplet{
	
	private BufferedWriter writer;
	private boolean isAlive = true;
	
	public static void main(String[] args) {
		PApplet.main("main.Servidor");

	}
	
	public void settings() {
		size(500,500);
	}
	
	public void setup() {
		
				new Thread(
				
				()->{
					try {
						
						
						
						ServerSocket server = new ServerSocket(5000);
						System.out.println("Esperando cliente...");
						Socket socket = server.accept();
						System.out.println("Cliente conectado");
						
						
						InputStream is = socket.getInputStream();
						OutputStream out = socket.getOutputStream();
						
						
						writer = new BufferedWriter(new OutputStreamWriter(out));
						BufferedReader reader = new BufferedReader(new InputStreamReader(is));
						
						
						while(isAlive) {
							String line = reader.readLine();
							System.out.println(line);
						}
						
						
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				).start();
		
		
	}
	
	
	
	public void draw() {
		background(0);
		
		
	}
	
	public void mousePressed() {
		
		String coords = "Pressed:"+mouseX + ":" + mouseY;
		
		try {
			writer.write(coords + "\n");
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	public void mouseReleased() {
		String coords = "Released:"+mouseX + ":" + mouseY;
		
		try {
			writer.write(coords + "\n");
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
