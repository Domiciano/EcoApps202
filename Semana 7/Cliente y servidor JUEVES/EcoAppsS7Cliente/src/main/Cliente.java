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
	
	
	
	
	public int x,y;
	
	private TCPSingleton tcp;
	
	public static void main(String[] args) {
		PApplet.main("main.Cliente");
		

	}
	
	public void settings() {
		size(500,500);			
	}
	
	public void setup() {
		
		tcp = TCPSingleton.getInstance();
		tcp.setCliente(this);
		tcp.start();
		
				
		
	}
	

	
	public void draw() {
		background(0);
		
		fill(255);
		ellipse(x,y,50,50);
		
	}
	
	public void mousePressed() {
		String coords = mouseX + ":" + mouseY;
		tcp.enviar("Hola mundo");
	}
	
	//Metodo de notificacion
	//Esto correo en el worker thread de TCP
	public void cuandoLlegueElmensaje(String mensaje) {
		System.out.println("Coordenada recibida" + mensaje);
		
		
		String[] partes = mensaje.split(",");
		x = Integer.parseInt(partes[0]);
		y = Integer.parseInt(partes[1]);
		
		
	}
	
	
	
	
	
	

}
