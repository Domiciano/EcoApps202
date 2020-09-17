package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import processing.core.PApplet;

public class Cliente extends PApplet{
	
	
	private BufferedWriter writer;
	
	private int x,y;
	
	
	public static void main(String[] args) {
		PApplet.main("main.Cliente");
		

	}
	
	public void settings() {
		size(500,500);
	}
	
	public void setup() {
		
		
				new Thread(
				
				()->{
					try {
						//Conexion
						System.out.println("Enviando solicitud de conexion...");
						Socket socket = new Socket("192.168.1.3", 5000);
						System.out.println("Conectamos");
						
						
						//Declaraciones
						InputStream is = socket.getInputStream();
						OutputStream out = socket.getOutputStream();
						
						writer = new BufferedWriter(new OutputStreamWriter(out));
						BufferedReader reader = new BufferedReader(new InputStreamReader(is));
						
						
						//Recepcion
						while(true) {
							System.out.println("Esperando mensaje...");
							String line = reader.readLine();
							
							
							String[] parts = line.split(":");
							
							String tipo = parts[0];
							if(tipo.equals("Pressed")) {
								System.out.println("Mensaje recibido: " + line);
								x = Integer.parseInt(parts[1]);
								y = Integer.parseInt(parts[2]);
							}else {
								System.out.println("No me interesa");
							}
							
						}
						
						
						
					} catch (UnknownHostException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				).start();
		
	}
	

	
	public void draw() {
		background(0);
		
		fill(255);
		ellipse(x,y,50,50);
		
		
	}
	
	public void mousePressed() {
		String coords = mouseX + ":" + mouseY;
		try {
			writer.write(coords + "\n");
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	

}
