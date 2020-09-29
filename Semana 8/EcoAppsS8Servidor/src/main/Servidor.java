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
import java.util.ArrayList;

import com.google.gson.Gson;

import processing.core.PApplet;

public class Servidor extends PApplet{
	
	
	private TCPLauncher launcher;
	
	
	
	public static void main(String[] args) {
		PApplet.main("main.Servidor");

	}
	
	public void settings() {
		size(500,500);
	}
	
	public void setup() {
		launcher = TCPLauncher.getInstance();
		launcher.setServidor(this);
		launcher.start();
		
	}
	
	
	
	public void draw() {
		background(0);
		
	}
	
	public void cuandoLlegueElmensaje(Session s, String mensaje) {
		System.out.println("Mensaje recibido de " + s.getID() + ": " + mensaje);
		Gson gson = new Gson();
		Coordenada coordenaRecibida = gson.fromJson(mensaje, Coordenada.class);
		
		
	}
	
	
	

}
