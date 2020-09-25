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
	
	public static void main(String[] args) {
		PApplet.main("main.Servidor");

	}
	
	public void settings() {
		size(500,500);
	}
	
	public void setup() {
		
		
		initServer();
		
		
	}
	
	public void initServer() {
		
		new Thread(
				
				
				()->{
					try {
						ServerSocket server = new ServerSocket(5000);
						System.out.println("Esperando...");
						Socket socket = server.accept();
						System.out.println("Conectado");
						InputStream is = socket.getInputStream();
						OutputStream out = socket.getOutputStream();
						
						
						BufferedReader reader = new BufferedReader(new InputStreamReader(is));
						writer = new BufferedWriter(new OutputStreamWriter(out));
						
						
						while(true) {
							
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
		try {
			writer.write(mouseX+","+mouseY+"\n");
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
