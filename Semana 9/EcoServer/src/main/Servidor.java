package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import com.google.gson.Gson;

import model.Generic;
import model.Movimiento;
import model.Nombre;
import processing.core.PApplet;

public class Servidor extends PApplet{
	
	private BufferedWriter writer;
	private Socket socket;

	public static void main(String[] args) {
		PApplet.main("main.Servidor");

	}
	public void settings() {
		size(500, 500);
	}

	public void setup() {
		initConnection();
	}

	public void draw() {
		background(255, 0, 0);
	}

	public void initConnection() {
		new Thread(

				() -> {
					try {

						System.out.println("Esperando conexion ...");
						ServerSocket server = new ServerSocket(6000);
						socket = server.accept();
						System.out.println("Conectado!");

						InputStream is = socket.getInputStream();
						BufferedReader reader = new BufferedReader(new InputStreamReader(is));

						OutputStream os = socket.getOutputStream();
						writer = new BufferedWriter(new OutputStreamWriter(os));

						while (true) {
							String line = reader.readLine();
							System.out.println("Recibido: " + line);
							
							
							
							
							
							Gson gson = new Gson();
							Generic generic = gson.fromJson(line, Generic.class);
							
							System.out.println(generic.getType());
							
							switch(generic.getType()) {
							
								case "Movimiento":
									Movimiento mov = gson.fromJson(line, Movimiento.class);
									System.out.println("Coordenada: " + mov.getX() + ", " + mov.getY());
									break;
								case "Nombre":
									Nombre nom = gson.fromJson(line, Nombre.class);
									System.out.println("Nombre: " + nom.getNombre());
									break;
							
							}
							
						}

					} catch (UnknownHostException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}).start();
	}
	
	
	
	public void enviar(String mensaje) {
		new Thread(
				()->{
					try {
						writer.write("Alfa");
						writer.flush();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
		).start();
	}
	
	
}
