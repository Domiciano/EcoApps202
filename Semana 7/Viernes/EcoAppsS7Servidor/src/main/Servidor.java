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
	
	
	private TCPSingletonJ1 tcpJ1;
	private TCPSingletonJ2 tcpJ2;
	
	
	public static void main(String[] args) {
		PApplet.main("main.Servidor");

	}
	
	public void settings() {
		size(500,500);
	}
	
	public void setup() {
		tcpJ1 = TCPSingletonJ1.getInstance();
		tcpJ1.setServidor(this);
		tcpJ1.start();
		
		tcpJ2 = TCPSingletonJ2.getInstance();
		tcpJ2.setServidor(this);
		tcpJ2.start();
		
		
	}
	
	
	
	public void draw() {
		background(0);
		
		
	}
	
	public void cuandoLlegueElmensaje(String jugador, String mensaje) {
		System.out.println("Mensaje recibido de " + jugador + ": " + mensaje);
	}
	
	
	

}
