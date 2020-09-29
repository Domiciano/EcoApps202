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

import com.google.gson.Gson;

import processing.core.PApplet;

public class Cliente extends PApplet{
	
	
	
	
	public int x = 0,y = 0;
	
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
		
	}
	
	//Metodo de notificacion
	//Esto correo en el worker thread de TCP
	public void cuandoLlegueElmensaje(String mensaje) {
		
	}
	
	
	public void keyPressed() {
		
		if(key == 'a' || key == 'A') {
			//izquierda
			x-=5;
		}
		if(key == 'w' || key == 'W') {
			//Arriba
			y-=5;
		}
		if(key == 's' || key == 'S') {
			//Abajo
			y+=5;
		}
		if(key == 'd' || key == 'D') {
			//Derecha
			x+=5;
		}
		
		Coordenada coor = new Coordenada(x, y);
		Gson gson = new Gson();
		String json = gson.toJson(coor);
		tcp.enviar(json);
		
		
	}
	
	
	
	
	

}
